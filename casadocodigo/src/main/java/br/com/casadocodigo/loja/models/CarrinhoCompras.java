package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component // Para que o Spring possa reconhecer esse cara, não sendo um DAO, Entity e
			// etc..
@Scope(value = WebApplicationContext.SCOPE_SESSION, // Escopo de sessão, cria-se um novo carrinho de compras para cada
		proxyMode = ScopedProxyMode.TARGET_CLASS) // Proxy fazendo a ligação direta para o carrinho de compras
													// usuario
public class CarrinhoCompras implements Serializable {
	private static final long serialVersionUID = 1L;// Guarda o objeto em arquivo,

	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<>();// Para busca um item de determinada lista

	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);// Quantidade daquela lista mais 1
	}

	public Integer getQuantidade(CarrinhoItem item) {
		if (!itens.containsKey(item)) {
			itens.put(item, 0);
		}
		return itens.get(item);
	}

	public int getQuantidade() {
		return itens.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);// Expressão lambida,
																								// JAVA 8
	}

	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getTotal(getQuantidade(item));
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}

	public Collection<CarrinhoItem> getItens() {
		return itens.keySet();
	}

	public void remover(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = new Produto();
		produto.setId(produtoId);
		itens.remove(new CarrinhoItem(produto, tipoPreco));

	}

}
