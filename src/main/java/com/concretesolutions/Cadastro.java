package com.concretesolutions;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
public class Cadastro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    private String name;

    @Column(unique = true)
    private String email;
    private String phones; //precisa ser um vetor depois

    private String created; //precisa ser data
    private String modified; //precisa ser data
    private String last_login; //precisa ser data

    private String token; //precisa ser persistido???




	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
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


	public String getPhones() {
		return phones;
	}


	public void setPhones(String phones) {
		this.phones = phones;
	}


	public String getCreated() {
		return created;
	}


	public void setCreated(String created) {
		this.created = created;
	}


	public String getModified() {
		return modified;
	}


	public void setModified(String modified) {
		this.modified = modified;
	}


	public String getLast_login() {
		return last_login;
	}


	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}
}
