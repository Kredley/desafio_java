package com.concretesolutions;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Cadastro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(unique = true, nullable=false)
    private String email;

    @Column(nullable=false)
    private String password;

    @OneToMany(mappedBy = "cadastro", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CadastroPhone> phones = new ArrayList<CadastroPhone>();

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/YYY HH:mm:ss", timezone="America/Sao_Paulo", locale="br")
    private Date created;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/YYY HH:mm:ss", timezone="America/Sao_Paulo", locale="br")
    private Date modified;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/YYY HH:mm:ss", timezone="America/Sao_Paulo", locale="br")
    private Date last_login;

    private String token;




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


    public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}





	public List<CadastroPhone> getPhones() {
		return phones;
	}


	public void setPhones(List<CadastroPhone> phones) {
		this.phones = phones;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public Date getModified() {
		return modified;
	}


	public void setModified(Date modified) {
		this.modified = modified;
	}


	public Date getLast_login() {
		return last_login;
	}


	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}

    public void addToCadastroPhone(CadastroPhone phone) {
        phone.setCadastro(this);
        this.phones.add(phone);
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
