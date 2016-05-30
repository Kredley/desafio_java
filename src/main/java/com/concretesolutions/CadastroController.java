package com.concretesolutions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLIntegrityConstraintViolationException;
import com.google.gson.Gson;
import java.util.Date;


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

        if (repository.countByEmail(cad.getEmail()) == 0) {
            // caso o email não exista, retornar status 200 - OK
            Date data_atual = new Date();
            cad.setCreated(data_atual);
            cad.setModified(data_atual);
            cad.setLast_login(data_atual);
            return new ResponseEntity<String>(gson.toJson(repository.save(cad)), HttpStatus.OK);
        }
        else {
            // se o email já existir na base, retornar status 409 - Conflict
            return new ResponseEntity<String>(gson.toJson(new MensagemRetorno("E-mail já existente")), HttpStatus.CONFLICT);
        }
    }
}
