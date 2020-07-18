package br.com.casadocodigo.loja.models;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;


@Component// Para que o Spring possa reconhecer esse cara, não sendo um DAO, Entity e etc..
public class CarrinhoCompras {

	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<>();//Para busca um item de determinada lista

	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item)+1);//Quantidade daquela lista mais 1
	}

	private int getQuantidade(CarrinhoItem item) {
		if(!itens.containsKey(item)) {
			itens.put(item,0);
		}
		return itens.get(item);
	}
	
	public int getQuantidade() {
		return itens.values().stream()
				.reduce(0,(proximo,acumulador)->proximo+acumulador);//Expressão lambida, JAVA 8
	}
	

}
