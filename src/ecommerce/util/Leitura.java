package ecommerce.util;

import java.util.Scanner;

public class Leitura {

	private static Scanner scan = new Scanner(System.in);

	private static String leitura(String mensagem) {
		System.out.printf(Cores.ANSI_BLACK_BACKGROUND + Cores.TEXT_YELLOW + mensagem + Cores.TEXT_RESET);
		return scan.nextLine();
	}
	
	
	public static int lerInteiro(String mensagem) {

		try {
			return Integer.parseInt(leitura(mensagem));
		} catch (NumberFormatException e) {
			throw new RuntimeException("Entrada Inválida!");
		}

	}

	public static float lerFloat(String mensagem) {

		float numero;
		
		try {
			numero = Float.parseFloat(leitura(mensagem));
		} catch (NumberFormatException e) {
			throw new RuntimeException("Entrada Inválida!");
		}
		
		if(numero < 0.0f) {
			throw new RuntimeException("Entrada Inválida!");
		}
		
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
