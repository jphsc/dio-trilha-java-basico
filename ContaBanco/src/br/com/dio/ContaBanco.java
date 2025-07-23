package br.com.dio;

import java.util.Scanner;

public class ContaBanco {

	public static void main(String[] args) {
		
		final String MENSAGEM = "Olá %s, obrigado por criar uma conta no nosso banco, "
				+ "sua agência é %s, conta %s e seu saldo %s já está disponível para saque";
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Por favor, digite o número da agência!");
		String agencia = sc.next();
		
		System.out.println("Por favor, digite o número da conta!");
		int numero = sc.nextInt();
		
		System.out.println("Por favor, digite o nome do cliente!");
		String nomeCliente = sc.next();
		
		System.out.println("Por favor, digite o saldo!");
		float saldo = sc.nextFloat();
		
		System.out.println(String.format(MENSAGEM, nomeCliente, agencia, numero, saldo));
		
	}

}
