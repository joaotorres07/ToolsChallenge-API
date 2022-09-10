package br.com.testejavachallenge.apipagamento.entities;

import br.com.testejavachallenge.apipagamento.enums.TipoEnum;

public class FormaPagamento {
	
	private TipoEnum tipo;
	private Integer parcelas;
	
	
	public TipoEnum getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoEnum tipo) {
		this.tipo = tipo;
	}
	
	public Integer getParcelas() {
		return parcelas;
	}
	
	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}
	
	
}
