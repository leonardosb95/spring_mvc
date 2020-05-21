package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import br.com.casadocodigo.loja.enumm.TipoPreco;

@Embeddable//Para que o spring possa portar e relacionar elementos de preço
public class Preco {
	
	private BigDecimal valor;
	private TipoPreco tipo;
	
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoPreco getTipo() {
		return tipo;
	}
	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}
	
	

}
