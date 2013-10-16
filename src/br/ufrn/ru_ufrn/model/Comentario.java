package br.ufrn.ru_ufrn.model;

public class Comentario {
	
	private Integer idComentario;
	private Integer idUsuario;
	private String comentario;
	private Integer imagem;
	private String usuario;
	
	
	public Comentario(){
		
	}
	
	public Comentario(String cometario, int imagem, String usuario){
		this.comentario = cometario;
		this.imagem = imagem;
		this.usuario = usuario;
	}
	
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Integer getIdComentario() {
		return idComentario;
	}
	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getImagem() {
		return imagem;
	}
	public void setImagem(int imagem) {
		this.imagem = imagem;
	}
	
	

}
