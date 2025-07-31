package br.com.dio;

import java.util.Scanner;

import br.com.dio.exception.ParametrosInvalidosException;

public class Contador {
	
	private static final Scanner terminal = new Scanner(System.in);

	public static void main(String[] args) throws ParametrosInvalidosException {
		
		System.out.println("Digite o primeiro parâmetro");
		int parametroUm = terminal.nextInt();
		
		System.out.println("Digite o segundo parâmetro");
		int parametroDois = terminal.nextInt();
		
		try {
			//chamando o método contendo a lógica de contagem
			contar(parametroUm, parametroDois);
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	static void contar(int parametroUm, int parametroDois ) throws ParametrosInvalidosException {
		
		int contagem = parametroDois - parametroUm;
		
		if(parametroUm > parametroDois) {
			throw new ParametrosInvalidosException();
		}
		
		for(int i=1; i<=contagem; i++) {
			System.out.println(String.format("Imprimindo o número %s", Integer.toString(i)));
		}
	}
}