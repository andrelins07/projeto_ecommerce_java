package ecommerce;

import ecommerce.util.Cores;
import ecommerce.util.Leitura;

public class Menu {

	public static void main(String[] args) {

		while (true) {

			System.out.print(Cores.TEXT_YELLOW + """
					***************************************************
							MERCADO DOS SONHOS
					***************************************************
					1 - Cadastrar Perfil
					2 - Atualizar Perfil
					3 - Excluir Perfil
					--------------------------------------------------
					4 - Cadastrar produto
					5 - Listar todos os produtos
					6 - Bucar produto por nome ou código
					7 - Atualizar produto
					8 - Excluir produto
					--------------------------------------------------
					9 - Comprar
					10 - Visualizar Compras
					--------------------------------------------------
					0 - Sair
					***************************************************
					""" + Cores.TEXT_RESET);
			try {
				int opcao = Leitura.lerInteiro(Cores.ANSI_BLACK_BACKGROUND + Cores.TEXT_YELLOW
						+ "Digite a opcao Desejada: " + Cores.TEXT_RESET);
				System.out.println();
				if (opcao == 0) {
					System.out.println("Finalizando...");
					sobre();
					break;
				}

				switch (opcao) {

				case 1 -> System.out.println("cadastrando perfil...");

				case 2 -> System.out.println("atualizando perfil...");

				case 3 -> System.out.println("excluindo perfil...");

				case 4 -> System.out.println("cadastrar produto...");

				case 5 -> System.out.println("listando todos os produtos...");

				case 6 -> System.out.println("buscando produto por nome ou código...");
				
				case 7 -> System.out.println("atualizando produto...");
				
				case 8 -> System.out.println("excluindo produto...");
				
				case 9 -> System.out.println("comprando...");
				
				case 10 -> System.out.println("visualizando compras");

				default -> System.out.println("\nOpção Inválida!\n");

				}
			} catch (RuntimeException exception) {
				System.out.println(Cores.TEXT_RED + exception.getMessage().toUpperCase());
				System.out.println("Pressione ENTER para voltar ao menu");
				Leitura.voltarMenu();
			}
		}
	}

	public static void sobre() {

		System.out.println(Cores.TEXT_BLUE + """
				\n***************************************************
				Projeto Desenvolvido por: Andre de Brito Lins
				Github: github.com/andrelins07/
				Email: andrelins2403@gmail.com
				****************************************************
				""");

	}
}
