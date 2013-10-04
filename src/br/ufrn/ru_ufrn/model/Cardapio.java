package br.ufrn.ru_ufrn.model;



import java.util.List;

public class Cardapio {
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
	
	
	
	
}
