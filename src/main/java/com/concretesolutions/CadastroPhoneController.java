package com.concretesolutions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;


@RestController
public class CadastroPhoneController {
    CadastroPhoneRepository repository;

    @Autowired
    public CadastroPhoneController(CadastroPhoneRepository repository) {
        this.repository = repository;
    }
}
