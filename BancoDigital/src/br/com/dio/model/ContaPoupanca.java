package br.com.dio.model;

import br.com.dio.constantes.Contantes.TipoConta;

public class ContaPoupanca extends Conta {

	private Double juros;
	
	public ContaPoupanca() {
		
	}

	public ContaPoupanca(Integer numero, Integer agencia, String nomeCliente, Integer codBanco, TipoConta tipoConta,
			Double juros) {
		super(numero, agencia, nomeCliente, codBanco, tipoConta);
		this.juros = juros;
	}

	public Double getJuros() {
		return juros;
	}

	public void setJuros(Double juros) {
		this.juros = juros;
	}

	@Override
	public String toString() {
		return "ContaPoupanca [juros=" + juros + "]";
	}
	
}
