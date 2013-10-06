package br.ufrn.ru_ufrn.model;

import java.util.ArrayList;
import java.util.List;

public class Refeicao {
		public String nome;
		private int tipo;
		public final List<Alimento> itens = new ArrayList<Alimento>();
		
		public Refeicao(String nome, int tipo) {
			super();
			this.nome = nome;
			this.tipo = tipo;
		}
		
		public Refeicao(String nome) {
			super();
			this.nome = nome;
		}
		
		public void adicionarAlimento(Alimento item){
			this.itens.add(item);
		}
		
		public void removerAlimento(String nome){
			//TODO remover alimento;
		}
		
}
