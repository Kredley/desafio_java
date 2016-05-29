package com.concretesolutions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CadastroController {
    CadastroRepository repository;

    @Autowired
    public CadastroController(CadastroRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/teste", method = RequestMethod.GET)
    ResponseEntity<String> teste() {
        return new ResponseEntity<String>("Teste", HttpStatus.OK);
    }
}
