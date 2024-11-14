package ecommerce.util;

import java.util.Scanner;

import ecommerce.exception.RegraDeNegocioException;

public class Leitura {

	private static Scanner scan = new Scanner(System.in);

	private static String leitura(String mensagem) {
		System.out.printf(Cores.ANSI_BLACK_BACKGROUND + Cores.TEXT_YELLOW + mensagem + Cores.TEXT_RESET);
		return scan.nextLine();
	}
	
	
	public static int lerInteiro(String mensagem) {

		int numero;
		
		try {
			numero = Integer.parseInt(leitura(mensagem));
		} catch (NumberFormatException e) {
			throw new RegraDeNegocioException("Entrada Inválida!");
		}
		
		if(numero < 0) {
			throw new RegraDeNegocioException("Entrada inválida! Numero negativo");
		}
		return numero;
	}

	public static float lerFloat(String mensagem) {

		float numero;
		
		try {
			numero = Float.parseFloat(leitura(mensagem));
		} catch (NumberFormatException e) {
			throw new RegraDeNegocioException("Entrada Inválida!");		}
		
		if(numero < 0.0f) {
			throw new RegraDeNegocioException("Entrada Inválida!");		}
		
		return numero;

	}

	public static String lerString(String mensagem) {
		
		String variavel = leitura(mensagem);
		
		if (variavel.isEmpty()) {
			throw new RuntimeException("Campos não preenchidos!");
		}
		return variavel;	
	}
	public static void voltarMenu() {
		scan.nextLine();
	}

}
