package br.ufrn.ru_ufrn.model;



import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class TesteSalvarJSONCardapio {
	public static void main(String[] args){
		Gson gson = new Gson();
		Cardapio c = new Cardapio();
		List<Alimento> listCafe = new ArrayList<Alimento>();
		
		listCafe.add(new Alimento("Café","Café preto feito na máquina","cafe.jpg"));
		listCafe.add(new Alimento("Leite","Leite de vaca pasteurizado","leite.jpg"));
		listCafe.add(new Alimento("Bolo de ovos","Bolo de ovos","bolo_ovos.jpg"));
		
		//c.setCafeDaManha(listCafe);

		List<Alimento> listAlmocoVeg = new ArrayList<Alimento>();
		
		listAlmocoVeg.add(new Alimento("Feijão","Feijão preto","feijao.jpg"));
		listAlmocoVeg.add(new Alimento("Arroz","Arroz Branco","arroz_branco.jpg"));
		listAlmocoVeg.add(new Alimento("Salada","Batatinha, cenoura e rúcula","salada.jpg"));

		//c.setAlmocoVegetariano(listAlmocoVeg);

		System.out.println(gson.toJson(c));
		
	}
}
