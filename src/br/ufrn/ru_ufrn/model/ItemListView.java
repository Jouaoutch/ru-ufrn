package br.ufrn.ru_ufrn.model;

public class ItemListView {
	private String texto;
    private int image ;
    private String usuario;

    public ItemListView() {
	this("", -1, "");
    }

    public ItemListView(String texto, int image, String usurio) {
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

	



    public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}

