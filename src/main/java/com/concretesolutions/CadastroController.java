package com.concretesolutions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLIntegrityConstraintViolationException;
import com.google.gson.Gson;



@RestController
public class CadastroController {
    CadastroRepository repository;

    @Autowired
    public CadastroController(CadastroRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    ResponseEntity<String> cadastrar(@RequestBody Cadastro cad) {
        Gson gson = new Gson(); //usa o Gson para converter o objeto Cadastro para JSON

        if (repository.countByEmail(cad.getEmail()) == 1) {
            // se o email já existir na base, retornar status 409 - Conflict
            return new ResponseEntity<String>(gson.toJson(new MensagemRetorno("E-mail já existente")), HttpStatus.CONFLICT);
        }
        else {
            // caso não exista, retornar status 200 - OK
            return new ResponseEntity<String>(gson.toJson(repository.save(cad)), HttpStatus.OK);
        }
    }
}
