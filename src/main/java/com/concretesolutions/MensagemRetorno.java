package com.concretesolutions;

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
}
