package br.com.dio.model;

import java.util.List;

import br.com.dio.constantes.Contantes.TipoConta;

import static br.com.dio.constantes.Contantes.SALDO_DISP_INFERIOR_SAQUE;

public class ContaCorrente extends Conta {

	private Double tarifa;
	private Boolean cartaoCreditoVinculado;
	private Boolean chequeEspecialVinculado;
	private Double valorChequeEspecial;
	private List<String> movimentos;
	
	public ContaCorrente() {
		super();
	}

	public ContaCorrente(Integer numero, Integer agencia, String nomeCliente, Integer codBanco, 
			TipoConta tipoConta, Double tarifa, Boolean cartaoCreditoVinculado, 
			Boolean chequeEspecialVinculado, Double valorChequeEspecial, List<String> movimentos) {
		super(numero, agencia, nomeCliente, codBanco, tipoConta);
		this.tarifa = tarifa;
		this.cartaoCreditoVinculado = cartaoCreditoVinculado;
		this.chequeEspecialVinculado = chequeEspecialVinculado;
		this.valorChequeEspecial = valorChequeEspecial;
		this.movimentos = movimentos;
	}

	public Double getTarifa() {
		return tarifa;
	}

	public void setTarifa(Double tarifa) {
		this.tarifa = tarifa;
	}

	public Boolean isCartaoCreditoVinculado() {
		return cartaoCreditoVinculado;
	}

	public void setCartaoCreditoVinculado(Boolean cartaoCreditoVinculado) {
		this.cartaoCreditoVinculado = cartaoCreditoVinculado;
	}

	public Boolean isChequeEspecialVinculado() {
		return chequeEspecialVinculado;
	}

	public void setChequeEspecialVinculado(Boolean chequeEspecialVinculado) {
		this.chequeEspecialVinculado = chequeEspecialVinculado;
	}

	public Double getValorChequeEspecial() {
		return valorChequeEspecial;
	}

	public void setValorChequeEspecial(Double valorChequeEspecial) {
		this.valorChequeEspecial = valorChequeEspecial;
	}

	public List<String> getMovimentos() {
		return movimentos;
	}

	public void setMovimentos(List<String> movimentos) {
		this.movimentos = movimentos;
	}

	@Override
	public String toString() {
		return "ContaCorrente [tarifa=" + tarifa + ", cartaoCreditoVinculado=" + cartaoCreditoVinculado
				+ ", chequeEspecialVinculado=" + chequeEspecialVinculado + ", valorChequeEspecial="
				+ valorChequeEspecial + ", movimentos=" + movimentos + "]";
	}
	
	public String transferir(ContaCorrente contaOrigem, ContaCorrente contaDestino, Double valor) {
		String msg = null;
		if(contaOrigem.getCodBanco() != contaDestino.getCodBanco()) {
			msg = "Banco da conta destino é diferente deste banco";
			return null;
			
		} else if (contaOrigem.getSaldo() < valor){
			msg = String.format(SALDO_DISP_INFERIOR_SAQUE, this.getSaldo());
			return null;
			
		} else {
			contaOrigem.sacar(valor);
			msg = String.format("Saque realizado, novo saldo: %d", this.getSaldo());
		}
		
		System.out.println(msg);
		return msg;
	}
	
	
	public void depositar(Double valor) {}
	
}
