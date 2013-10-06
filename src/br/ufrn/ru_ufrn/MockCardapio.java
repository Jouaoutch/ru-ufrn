package br.ufrn.ru_ufrn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.Alimento;
import br.ufrn.ru_ufrn.model.Cardapio;
import br.ufrn.ru_ufrn.model.dao.CardapioDAO;

public class MockCardapio {
	public static void mock(CardapioDAO cmd){
		
		Cardapio c = new Cardapio();
		List<Alimento> listCafe = new ArrayList<Alimento>();
		
		listCafe.add(new Alimento("Café","Café preto feito na máquina","cafe.jpg"));
		listCafe.add(new Alimento("Leite","Leite de vaca pasteurizado","leite.jpg"));
		listCafe.add(new Alimento("Bolo de ovos","Bolo de ovos","bolo_ovos.jpg"));
		
		c.setCafeDaManha(listCafe);

		List<Alimento> listAlmocoVeg = new ArrayList<Alimento>();
		
		listAlmocoVeg.add(new Alimento("Feijão","Feijão preto","feijao.jpg"));
		listAlmocoVeg.add(new Alimento("Arroz","Arroz integral","arroz_integral.jpg"));
		listAlmocoVeg.add(new Alimento("Salada","Batatinha, cenoura e rúcula","salada.jpg"));
		listAlmocoVeg.add(new Alimento("Torta de soja","Torta de soja","torta_soja.jpg"));

		c.setAlmocoVegetariano(listAlmocoVeg);


		List<Alimento> listAlmocoCar = new ArrayList<Alimento>();
		
		listAlmocoCar.add(new Alimento("Feijão","Feijão preto","feijao.jpg"));
		listAlmocoCar.add(new Alimento("Arroz","Arroz Branco","arroz_branco.jpg"));
		listAlmocoCar.add(new Alimento("Salada","Batatinha, cenoura e rúcula","salada.jpg"));
		listAlmocoCar.add(new Alimento("Frango","Frango assado","frango.jpg"));

		c.setAlmocoCarnivoro(listAlmocoCar);
		

		List<Alimento> listJantaVeg = new ArrayList<Alimento>();
		
		listJantaVeg.add(new Alimento("Arroz","Arroz integral","arroz_integral.jpg"));
		listJantaVeg.add(new Alimento("Pão","Pão integral","pao_integral.jpg"));
		listJantaVeg.add(new Alimento("Sopa","Sopa de legumes","torta_soja.jpg"));

		c.setJantaVegetariana(listJantaVeg);


		List<Alimento> listJantaCar = new ArrayList<Alimento>();
		
		listJantaCar.add(new Alimento("Sopa","Sopa de carne","sopa_carne.jpg"));
		listJantaCar.add(new Alimento("Pão","Pão","pao.jpg"));
		listJantaCar.add(new Alimento("Macarronada","Macarronada","macarronada.jpg"));

		c.setJantaCarnivora(listJantaCar);
		
		c.setData(new Date());
		
		try {
			cmd.save(c);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				
		
		
	}
}
