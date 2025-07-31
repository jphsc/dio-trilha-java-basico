package br.com.dio.exception;

public class ParametrosInvalidosException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM = "O segundo parâmetro deve ser maior que o primeiro";
	public ParametrosInvalidosException() {
		super(MENSAGEM);
	}

}
