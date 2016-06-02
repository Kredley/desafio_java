package com.concretesolutions;

import javax.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CadastroPhone {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="cadastro_id")
    private Cadastro cadastro;

    private String ddd;
    private String number;


    public CadastroPhone() {
    }

    public CadastroPhone(String ddd, String number) {
        this.setDdd(ddd);
        this.setNumber(number);
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Cadastro getCadastro() {
        return cadastro;
    }


    public void setCadastro(Cadastro cadastro) {
        this.cadastro = cadastro;
    }


    public String getDdd() {
        return ddd;
    }


    public void setDdd(String ddd) {
        this.ddd = ddd;
    }


    public String getNumber() {
        return number;
    }


    public void setNumber(String number) {
        this.number = number;
    }

    public String toString() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(this);
        }
        catch (JsonProcessingException e) {
            return new String().format("{'mensagem': '%s'}", e.getMessage());
        }
    }
}
