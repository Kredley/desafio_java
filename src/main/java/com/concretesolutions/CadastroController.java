package com.concretesolutions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import java.util.Date;



@RestController
public class CadastroController {
    CadastroRepository repository;

    @Autowired
    public CadastroController(CadastroRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> cadastro(@RequestBody Cadastro cad) {

        // validação simples dos principais campos do cadastro
        if (cad.getName() == null || cad.getEmail() == null || cad.getPassword() == null) {
            return new ResponseEntity<String>(new MensagemRetorno("Campos name, email e password não podem ser nulos").toString(), HttpStatus.BAD_REQUEST);
        }

        if (repository.countByEmail(cad.getEmail()) == 0) {
            // caso o email não exista, retornar status 200 - OK
            Date data_atual = new Date();
            cad.setCreated(data_atual);
            cad.setModified(data_atual);
            cad.setLast_login(data_atual);
            cad.setToken(Token.generateToken(cad.getEmail()));

            //referencia o objeto cad para cada um dos objetos de CadastroPhone do cad
            for (CadastroPhone ph: cad.getPhones()) {
                ph.setCadastro(cad);
            }
            return new ResponseEntity<String>(repository.save(cad).toString(), HttpStatus.OK);
        }
        else {
            // se o email já existir na base, retornar status 409 - Conflict
            return new ResponseEntity<String>(new MensagemRetorno("E-mail já existente").toString(), HttpStatus.CONFLICT);
        }
    }



    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> login(@RequestBody Cadastro cad) {
        Cadastro cadBD;

        // verifica se o email e a senha não são nulos
        if (cad.getEmail() != null && cad.getPassword() != null) {
            // no caso de não serem nulos, procura o email no BD e compara as senhas
            cadBD = repository.findByEmail(cad.getEmail());
            if (cadBD.getEmail().equals(cad.getEmail()) && cadBD.getPassword().equals(cad.getPassword())) {
                // caso sejam iguais, gera um token e retorna o usuário
                cadBD.setToken(Token.generateToken(cadBD.getEmail()));
                cadBD.setLast_login(new Date());
                repository.save(cadBD);
                return new ResponseEntity<String>(cadBD.toString(), HttpStatus.OK);
            }
            else {
                // se o email e senha não estiverem corretos, retornar o erro apropriado
                return new ResponseEntity<String>(new MensagemRetorno("Usuário e/ou senha inválidos").toString(), HttpStatus.UNAUTHORIZED);
            }
        } else {
            // se o email ou a senha estiverem em branco, retornar o erro apropriado
            return new ResponseEntity<String>(new MensagemRetorno("Usuário e/ou senha inválidos").toString(), HttpStatus.UNAUTHORIZED);
        }
    }


    @RequestMapping(value = "/perfil/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> perfil(@RequestHeader(value="Authorization", required=false) String auth, @PathVariable Long id) {
        if (auth != null) {
            String auth_parts[] = auth.trim().split("\\s+");
            if (auth_parts.length > 0) {
                String token = auth_parts[auth_parts.length - 1];
                Cadastro cadBD = repository.findOne(id);
                if (cadBD != null) {

                    // o usuário existe, então verificar se o token está correto
                    if (cadBD.getToken().equals(token)) {
                        // verifica se o token expirou
                        // aqui poderia ter sido verificada a data do último login,
                        // mas faz mais sentido olhar a validade do token
                        if (Token.isNotExpired(token)) {
                            // aqui, caso seja feita alguma alteração no perfil do usuário
                            // basta descomentar as seguintes linhas:
                            // cadBD.setModified(new Date());
                            // repository.save(cadBD);
                            return new ResponseEntity<String>(cadBD.toString(), HttpStatus.OK);
                        } else {
                            return new ResponseEntity<String>(new MensagemRetorno("Sessão inválida").toString(), HttpStatus.UNAUTHORIZED);
                        }

                    } else {
                        // token inválido
                        return new ResponseEntity<String>(new MensagemRetorno("Não autorizado").toString(), HttpStatus.UNAUTHORIZED);
                    }
                } else {
                    // id de usuário inexistente
                    return new ResponseEntity<String>(new MensagemRetorno("Não autorizado").toString(), HttpStatus.UNAUTHORIZED);
                }
            } else {
                // header presente, mas em branco
                return new ResponseEntity<String>(new MensagemRetorno("Não autorizado").toString(), HttpStatus.UNAUTHORIZED);
            }
        }
        else {
            // se o token não estiver presente no header, retornar o erro apropriado
            return new ResponseEntity<String>(new MensagemRetorno("Não autorizado").toString(), HttpStatus.UNAUTHORIZED);
        }
    }
}
