package br.ufrn.ru_ufrn.model;



import java.util.Date;
import java.util.List;

public class Cardapio {
	
	public static final int CAFE_DA_MANHA = 0;
	public static final int ALMOCO_VEGETARIANO = 1;
	public static final int ALMOCO_CARNIVORO= 2;
	public static final int JANTA_VEGETARIANA= 3;
	public static final int JANTA_CARNIVORA= 4;
	
	
	
	private Date data;
	private List<Alimento> cafeDaManha;
	private List<Alimento> almocoVegetariano;
	private List<Alimento> almocoCarnivoro;
	private List<Alimento> jantaVegetariana;
	private List<Alimento> jantaCarnivora;
	
	public Cardapio() {
	
	}

	public Cardapio(List<Alimento> cafeDaManha,
			List<Alimento> almocoVegetariano, List<Alimento> almocoCarnivoro,
			List<Alimento> jantaVegetariana, List<Alimento> jantaCarnivora) {
		super();
		this.cafeDaManha = cafeDaManha;
		this.almocoVegetariano = almocoVegetariano;
		this.almocoCarnivoro = almocoCarnivoro;
		this.jantaVegetariana = jantaVegetariana;
		this.jantaCarnivora = jantaCarnivora;
	}

	public List<Alimento> getCafeDaManha() {
		return cafeDaManha;
	}

	public void setCafeDaManha(List<Alimento> cafeDaManha) {
		this.cafeDaManha = cafeDaManha;
	}

	public List<Alimento> getAlmocoVegetariano() {
		return almocoVegetariano;
	}

	public void setAlmocoVegetariano(List<Alimento> almocoVegetariano) {
		this.almocoVegetariano = almocoVegetariano;
	}

	public List<Alimento> getAlmocoCarnivoro() {
		return almocoCarnivoro;
	}

	public void setAlmocoCarnivoro(List<Alimento> almocoCarnivoro) {
		this.almocoCarnivoro = almocoCarnivoro;
	}

	public List<Alimento> getJantaVegetariana() {
		return jantaVegetariana;
	}

	public void setJantaVegetariana(List<Alimento> jantaVegetariana) {
		this.jantaVegetariana = jantaVegetariana;
	}

	public List<Alimento> getJantaCarnivora() {
		return jantaCarnivora;
	}

	public void setJantaCarnivora(List<Alimento> jantaCarnivora) {
		this.jantaCarnivora = jantaCarnivora;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Cardapio clone = new Cardapio();
		clone.setData(this.data);
		clone.setCafeDaManha(this.cafeDaManha);
		clone.setAlmocoVegetariano(this.almocoVegetariano);
		clone.setAlmocoCarnivoro(this.almocoCarnivoro);
		clone.setJantaVegetariana(this.jantaVegetariana);
		clone.setJantaCarnivora(this.jantaCarnivora);
		return clone;
	}
	
	
}
