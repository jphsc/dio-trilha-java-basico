package br.com.dio;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {
	private static final String MSG_VALIDO = "ip valido";
	private static final String MSG_INVALIDO = "ip invalido";

	public static void main(String[] args) {

//		Conta conta = new ContaCorrente();
//
////		String x = "256.100.50.25";
//		String x = "256 100 50 25";
//		
//		String[] sep = x.split(" ");
//		List<String> x1 = Arrays.asList(sep);
//		
//
//		
////		for(String s : x1) {
////			System.out.println(s);
////		}
//		x1.forEach(p -> ehNumeroValido(p));
//
//	}
//	
//	private static boolean ehNumeroValido(String str) {
//        try {
//            int num = Integer.parseInt(str); 
//            return num >= 0 && num <= 255; 
//        } catch (NumberFormatException e) {
//            return false; 
//        }

		// TODO: Verifique se o IP é válido e imprima o resultado
		// TODO: Chame o método validarIP passando o IP como parâmetro e imprimir "ip
		// valido" ou "ip invalido"
		// DICA: Use um if-else para verificar o retorno do método validarIP
		System.out.println("Informe o ip:");
		Scanner scanner = new Scanner(System.in);
		String ip = scanner.nextLine();
		Boolean situacaoIp = validarIP(ip);

		if (situacaoIp) {
			System.out.println(MSG_VALIDO);
		} else {
			System.out.println(MSG_INVALIDO);
		}

		scanner.close();

	}

	// Método para validar um endereço IP
	public static boolean validarIP(String ip) {
		// TODO: Divida a string IP pelo caractere "." e armazenar as partes em um array
		// DICA: Use o método split(".")
		String[] ipSeparado = ip.split("\\.");
		List<String> partesIp = Arrays.asList(ipSeparado);

		// TODO: Verificque se o array contém exatamente 4 partes
		// DICA: Se não tiver 4 partes, o IP é inválido (retorne false)
		if (partesIp.size() != 4) {
			return false;
		}

		for (String parte : partesIp) {
			if (!ehNumeroValido(parte))
				return false;
		}

		// TODO: Percorra cada parte do array e verificar se é um número válido
		// DICA: Use um loop for para percorrer todas as partes do IP
		// DICA: Para verificar se a parte é válida, chame o método
		// ehNumeroValido(parte)

		return true;
	}

	// Método auxiliar para verificar se uma string representa um número válido
	// entre 0 e 255
	private static boolean ehNumeroValido(String str) {
		try {
			int num = Integer.parseInt(str);
			return num >= 0 && num <= 255;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
