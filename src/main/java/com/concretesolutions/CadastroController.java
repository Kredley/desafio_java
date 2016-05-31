package com.concretesolutions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;


import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;


@RestController
public class CadastroController {
    CadastroRepository repository;

    @Autowired
    public CadastroController(CadastroRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    ResponseEntity<String> cadastrar(@RequestBody Cadastro cad) {

        if (repository.countByEmail(cad.getEmail()) == 0) {
            // caso o email não exista, retornar status 200 - OK
            Date data_atual = new Date();
            cad.setCreated(data_atual);
            cad.setModified(data_atual);
            cad.setLast_login(data_atual);
            cad.setToken(Jwts.builder().setSubject("Joe").setExpiration(new Date((new Date()).getTime() + 30 * DesafioApplication.UM_MINUTO_EM_MILISEGUNDOS)).signWith(SignatureAlgorithm.HS512, DesafioApplication.SECRET_KEY).compact());

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



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseEntity<String> autenticar(@RequestBody Cadastro cad) {
        Cadastro cadBD;

        if (cad.getEmail() != null && cad.getPassword() != null) {
            cadBD = repository.findByEmail(cad.getEmail());
            if (cadBD.getEmail() == cad.getEmail() && cadBD.getPassword() != cad.getPassword()) {
                cadBD.setToken(Jwts.builder().setSubject(cadBD.getEmail()).setExpiration(new Date((new Date()).getTime() + 30 * DesafioApplication.UM_MINUTO_EM_MILISEGUNDOS)).signWith(SignatureAlgorithm.HS512, DesafioApplication.SECRET_KEY).compact());
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



    @RequestMapping(value = "/list", method = RequestMethod.GET)
    ResponseEntity<String> listar() {
        return new ResponseEntity<String>(repository.findAll().toString(), HttpStatus.OK);
    }
}
