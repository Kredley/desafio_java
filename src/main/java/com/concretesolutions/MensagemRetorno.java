package com.concretesolutions;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MensagemRetorno {
    private String mensagem;

    public MensagemRetorno(String mensagem) {
        this.mensagem = mensagem;
    }

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
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
