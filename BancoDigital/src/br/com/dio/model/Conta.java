package br.com.dio.model;

import static br.com.dio.constantes.Contantes.SALDO_DISP_INFERIOR_SAQUE;

import br.com.dio.constantes.Contantes.TipoConta;

public abstract class Conta {

	private Integer numero;
	private Integer agencia;
	private Double saldo;
	private String nomeCliente;
	private Integer codBanco;
	private TipoConta tipoConta;
	
	public Conta() {
		
	}

	public Conta(Integer numero, Integer agencia, String nomeCliente, Integer codBanco, TipoConta tipoConta) {
		this.numero = numero;
		this.agencia = agencia;
		this.saldo = 0d;
		this.nomeCliente = nomeCliente;
		this.codBanco = codBanco;
		this.tipoConta = tipoConta;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Integer getCodBanco() {
		return codBanco;
	}

	public void setCodBanco(Integer codBanco) {
		this.codBanco = codBanco;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	@Override
	public String toString() {
		return "Conta [numero=" + numero + ", agencia=" + agencia + ", saldo=" + saldo + ", nomeCliente=" + nomeCliente
				+ ", tipoConta=" + tipoConta + "]";
	}
	
	public void depositar(Double valor) {
		if (!valor.isNaN() && valor > 0) {
			this.setSaldo(Double.sum(this.getSaldo(), valor));
		} else {
			System.out.println("Valor inválido");
		}
	}
	
	public void sacar(Double valor) {
		if (valor <= 0 || valor.isNaN()) {
			System.out.println("Valor inválido");
		} else if (valor > 0 && this.getSaldo() <= 0) {
			System.out.println(String.format(SALDO_DISP_INFERIOR_SAQUE, this.getSaldo()));
		} 
		
		this.setSaldo(this.getSaldo() - valor);
		System.out.println(String.format("Novo saldo %d", this.getSaldo()));
	}
	
}
