package br.ufrn.ru_ufrn.model;






public class Cardapio extends Model{
	
	public static final int CAFE_DA_MANHA = 0;
	public static final int ALMOCO_VEGETARIANO = 1;
	public static final int ALMOCO_CARNIVORO= 2;
	public static final int JANTA_VEGETARIANA= 3;
	public static final int JANTA_CARNIVORA= 4;
	
	
	
	private String data;
	private Refeicao cafeDaManha;
	private Refeicao almocoVegetariano;
	private Refeicao almocoCarnivoro;
	private Refeicao jantaVegetariana;
	private Refeicao jantaCarnivora;
	
	public Cardapio() {
	
	}
	
	public Cardapio(String data, Refeicao cafeDaManha,
			Refeicao almocoVegetariano, Refeicao almocoCarnivoro,
			Refeicao jantaVegetariana, Refeicao jantaCarnivora) {
		super();
		this.data = data;
		this.cafeDaManha = cafeDaManha;
		this.almocoVegetariano = almocoVegetariano;
		this.almocoCarnivoro = almocoCarnivoro;
		this.jantaVegetariana = jantaVegetariana;
		this.jantaCarnivora = jantaCarnivora;
	}

	

	public Refeicao getCafeDaManha() {
		return cafeDaManha;
	}

	public void setCafeDaManha(Refeicao cafeDaManha) {
		this.cafeDaManha = cafeDaManha;
	}

	public Refeicao getAlmocoVegetariano() {
		return almocoVegetariano;
	}

	public void setAlmocoVegetariano(Refeicao almocoVegetariano) {
		this.almocoVegetariano = almocoVegetariano;
	}

	public Refeicao getAlmocoCarnivoro() {
		return almocoCarnivoro;
	}

	public void setAlmocoCarnivoro(Refeicao almocoCarnivoro) {
		this.almocoCarnivoro = almocoCarnivoro;
	}

	public Refeicao getJantaVegetariana() {
		return jantaVegetariana;
	}

	public void setJantaVegetariana(Refeicao jantaVegetariana) {
		this.jantaVegetariana = jantaVegetariana;
	}

	public Refeicao getJantaCarnivora() {
		return jantaCarnivora;
	}

	public void setJantaCarnivora(Refeicao jantaCarnivora) {
		this.jantaCarnivora = jantaCarnivora;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
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
