package br.ufrn.ru_ufrn.model;

import java.util.Date;

import org.apache.http.impl.cookie.DateUtils;

public class Avaliacao {

	private boolean cardapioCumprido;
	private Date data;
	private NivelSatisfacao nivelSatisfacao;
	private String refeicao;
	private long idUsuario;
	private long idAvaliacao;

	public static final String ALMOCO_CARNIVORO = "almoço carnívoro",
			ALMOCO_VEGETARIANO = "almoço vegetarioano", CAFE = "café da manhã",
			JANTAR_CARNIVORO = "jantar carnivoro",
			JANTAR_VEGETARIANO = "jantar vegetariano";

	public String getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(String refeicao) {
		this.refeicao = refeicao;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

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
	
	public void setNivelSatisfacao(String nivelSatisfacao) {
		NivelSatisfacao ns = null;
		if(nivelSatisfacao.equals(ns.GOSTEI.toString())){
			ns = ns.GOSTEI;
		}else if(nivelSatisfacao.equals(ns.DESGOSTEI.toString())){
			ns = ns.DESGOSTEI;
		}else{
			ns = ns.INDIFERENTE;
		}
		
		this.nivelSatisfacao = ns;
	}


	public String getDataFormatoAmericano() {
		System.out.println(data.getYear() + "-" + data.getMonth() + "-" + data.getDay());
		return data.getYear() + "-" + data.getMonth() + "-" + data.getDay();
	}

	public long getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(long idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}
	
	

}
