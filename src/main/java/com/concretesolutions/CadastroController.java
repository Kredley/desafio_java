package com.concretesolutions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;


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


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    ResponseEntity<String> listar() {
        return new ResponseEntity<String>(repository.findAll().toString(), HttpStatus.OK);
    }
}
