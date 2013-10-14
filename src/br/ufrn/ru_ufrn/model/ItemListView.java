package br.ufrn.ru_ufrn.model;

public class ItemListView {
	private String texto;
    private String image ;
    private String usuario;

    public ItemListView() {
	this("", "", "");
    }

    public ItemListView(String texto, String image, String usurio) {
        this.texto = texto;
        this.image = image;
        this.usuario = usurio;
    }
    
    

    public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	



    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}

