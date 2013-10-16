package br.ufrn.ru_ufrn.model;

import java.util.ArrayList;
import java.util.List;

public class Refeicao extends Model{
		private String nome;
		private int tipo;
		public final List<Alimento> itens = new ArrayList<Alimento>();
		
		public Refeicao(String nome, int tipo) {
			super();
			this.setNome(nome);
			this.setTipo(tipo);
		}
		
		public Refeicao(String nome) {
			super();
			this.setNome(nome);
		}
		
		public void adicionarAlimento(Alimento item){
			this.itens.add(item);
		}
		
		public void removerAlimento(String nome){
			//TODO remover alimento;
		}
		
		public List<Alimento> getItens(){
			return this.itens;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public int getTipo() {
			return tipo;
		}

		public void setTipo(int tipo) {
			this.tipo = tipo;
		}
		
}
