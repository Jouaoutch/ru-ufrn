package br.ufrn.ru_ufrn;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.ufrn.ru_ufrn.exceptions.DAOException;
import br.ufrn.ru_ufrn.model.Alimento;
import br.ufrn.ru_ufrn.model.Cardapio;
import br.ufrn.ru_ufrn.model.Refeicao;
import br.ufrn.ru_ufrn.model.dao.CardapioDAO;

public class MockCardapio {
	public static void mock(CardapioDAO cmd){
		Refeicao refeicao;
		Cardapio c = new Cardapio();
		List<Alimento> listCafe = new ArrayList<Alimento>();
		
		listCafe.add(new Alimento("Café","Café preto feito na máquina","http://icons.iconarchive.com/icons/babasse/old-school/64/coffee-icon.png"));
		listCafe.add(new Alimento("Leite","Leite de vaca pasteurizado","leite.jpg"));
		listCafe.add(new Alimento("Bolo de ovos","Bolo de ovos","bolo_ovos.jpg"));
		
		refeicao = new Refeicao("Café da manhã");
		
		setRefeicao(refeicao, listCafe);
		
		c.setCafeDaManha(refeicao);

		List<Alimento> listAlmocoVeg = new ArrayList<Alimento>();
		
		listAlmocoVeg.add(new Alimento("Feijão","Feijão preto","feijao.jpg"));
		listAlmocoVeg.add(new Alimento("Arroz","Arroz integral","arroz_integral.jpg"));
		listAlmocoVeg.add(new Alimento("Salada","Batatinha, cenoura e rúcula","salada.jpg"));
		listAlmocoVeg.add(new Alimento("Torta de soja","Torta de soja","torta_soja.jpg"));

		refeicao = new Refeicao("Almoço vegetariano");
		
		setRefeicao(refeicao, listAlmocoVeg);
		
		c.setAlmocoVegetariano(refeicao);;


		List<Alimento> listAlmocoCar = new ArrayList<Alimento>();
		
		listAlmocoCar.add(new Alimento("Feijão","Feijão preto","feijao.jpg"));
		listAlmocoCar.add(new Alimento("Arroz","Arroz Branco","arroz_branco.jpg"));
		listAlmocoCar.add(new Alimento("Salada","Batatinha, cenoura e rúcula","salada.jpg"));
		listAlmocoCar.add(new Alimento("Frango","Frango assado","frango.jpg"));

		refeicao = new Refeicao("Almoço Carnívoro");
		
		setRefeicao(refeicao, listAlmocoCar);
		
		c.setAlmocoCarnivoro(refeicao);
		

		List<Alimento> listJantaVeg = new ArrayList<Alimento>();
		
		listJantaVeg.add(new Alimento("Arroz","Arroz integral","arroz_integral.jpg"));
		listJantaVeg.add(new Alimento("Pão","Pão integral","pao_integral.jpg"));
		listJantaVeg.add(new Alimento("Sopa","Sopa de legumes","torta_soja.jpg"));

		refeicao = new Refeicao("janta Vegetariana");
		
		setRefeicao(refeicao, listJantaVeg);
		
		c.setJantaVegetariana(refeicao);


		List<Alimento> listJantaCar = new ArrayList<Alimento>();
		
		listJantaCar.add(new Alimento("Sopa","Sopa de carne","sopa_carne.jpg"));
		listJantaCar.add(new Alimento("Pão","Pão","pao.jpg"));
		listJantaCar.add(new Alimento("Macarronada","Macarronada","macarronada.jpg"));
		
		refeicao = new Refeicao("janta Carnívora");
		
		setRefeicao(refeicao, listJantaCar);		
		
		c.setJantaCarnivora(refeicao);
		
		c.setData(new Date());
		
		try {
			cmd.save(c);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}

	private static void setRefeicao(Refeicao refeicao, List<Alimento> list) {
		for (Iterator<Alimento> iterator = list.iterator(); iterator.hasNext();) {
			Alimento alimento = iterator.next();
			refeicao.itens.add(alimento);
		}
	}
}
