package br.ufrn.ru_ufrn.model;

import java.sql.Date;

import org.apache.http.impl.cookie.DateUtils;

public class Avaliacao {
	
	private boolean cardapioCumprido;
	private Date data;
	private NivelSatisfacao nivelSatisfacao;
	
	
	public boolean isCardapioCumprido() {
		return cardapioCumprido;
	}
	public void setCardapioCumprido(boolean cardapioCumprido) {
		this.cardapioCumprido = cardapioCumprido;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public NivelSatisfacao getNivelSatisfacao() {
		return nivelSatisfacao;
	}
	public void setNivelSatisfacao(NivelSatisfacao nivelSatisfacao) {
		this.nivelSatisfacao = nivelSatisfacao;
	}
	

}
