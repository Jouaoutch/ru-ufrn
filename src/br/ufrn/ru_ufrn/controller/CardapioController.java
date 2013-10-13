package br.ufrn.ru_ufrn.controller;

import java.util.List;

import br.ufrn.ru_ufrn.model.Cardapio;

public class CardapioController {
	
	
	private static final int ON = 1;
	private static final int OFF = 2;
	private static final int LOW = 3;

	
	
	private int conexao_internet;
	
	public List<Cardapio>getCardapiosDaSemana(){
		
		testConexao();
		List<Cardapio> output = null;
		
		switch(conexao_internet){
			case ON:
				break;
			case OFF:
				output = null;
				break;
			case LOW:
				break;
			default:
				break;
		}
		
		return null;
	}

	private void testConexao() {
		this.conexao_internet = OFF;
		
	}
}
