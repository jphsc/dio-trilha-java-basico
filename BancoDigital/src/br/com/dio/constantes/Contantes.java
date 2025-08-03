package br.com.dio.constantes;

public class Contantes {

	public static final String SALDO_DISP_INFERIOR_SAQUE = "Saldo disponível %d inferior ao valor do saque";
	
	public enum TipoConta{

		CONTA_CORRENTE(1, "Conta Corrente"),
		CONTA_POUPANCA(2, "Conta Poupança");
		
		private int codigo;
		private String descricao;
		
		private TipoConta(int codigo, String descricao) {
			this.codigo = codigo;
			this.descricao = descricao;
		}

		public int getCodigo() {
			return codigo;
		}

		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
	}
}
