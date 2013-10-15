package br.ufrn.ru_ufrn.model;

import java.util.Date;

import org.apache.http.impl.cookie.DateUtils;

public class Avaliacao {

	private Boolean cardapioCumprido;
	private Date data;
	private Integer idAvaliacao;
	private Integer idRefeicao;
	private Long idUsuario;


	public static final String ALMOCO_CARNIVORO = "almoço carnívoro",
			ALMOCO_VEGETARIANO = "almoço vegetarioano", CAFE = "café da manhã",
			JANTAR_CARNIVORO = "jantar carnivoro",
			JANTAR_VEGETARIANO = "jantar vegetariano";

	

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
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
	
	


	/*
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
*/

	public Boolean getCardapioCumprido() {
		return cardapioCumprido;
	}

	public void setCardapioCumprido(Boolean cardapioCumprido) {
		this.cardapioCumprido = cardapioCumprido;
	}

	public Integer getIdRefeicao() {
		return idRefeicao;
	}

	public void setIdRefeicao(Integer idRefeicao) {
		this.idRefeicao = idRefeicao;
	}

	public void setIdAvaliacao(Integer idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public String getDataFormatoAmericano() {
		return data.getYear() + "-" + data.getMonth() + "-" + data.getDay();
	}

	public Integer getIdAvaliacao() {
		return idAvaliacao;
	}

	
	

}
