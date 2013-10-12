package br.ufrn.ru_ufrn.model;

import java.util.Date;

public class ResultadoAvaliacoes {

	private String refeicao;
	private int gostaram;
	private int desgostaram;
	private int indiferente;
	private int totaVotos;
	private Date data;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(String refeicao) {
		this.refeicao = refeicao;
	}

	public int getGostaram() {
		return gostaram;
	}

	public void setGostaram(int gostaram) {
		this.gostaram = gostaram;
	}

	public int getDesgostaram() {
		return desgostaram;
	}

	public void setDesgostaram(int desgostaram) {
		this.desgostaram = desgostaram;
	}

	public int getIndiferente() {
		return indiferente;
	}

	public void setIndiferente(int indiferente) {
		this.indiferente = indiferente;
	}

	public int getTotaVotos() {
		return totaVotos;
	}

	public void setTotaVotos(int totaVotos) {
		this.totaVotos = totaVotos;
	}

}
