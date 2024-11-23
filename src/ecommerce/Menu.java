package ecommerce;

import ecommerce.model.usuario.Usuario;
import ecommerce.util.Cores;
import ecommerce.util.Leitura;

public class Menu {

	private static Usuario usuario;

	public static void main(String[] args) {
		
		AutenticarUsuario autenticacao = new AutenticarUsuario();
		
		while (true) {
			System.out.println(Cores.TEXT_YELLOW + """
					***************************************************************
							     MERCADO DOS SONHOS
					***************************************************************
					1 - Acessar Conta
					2 - Cadastrar Conta
					0 - SAIR
					***************************************************************
					""" + Cores.TEXT_RESET);
			try {
				int opcao = Leitura.lerInteiro("Digite a opcao Desejada: ");
				System.out.println();
				if (opcao == 0) {
					System.out.println("Finalizando...");
					sobre();
					break;
				}
				switch (opcao) {

				case 1 -> {
					usuario = autenticacao.buscarUsuario();
					usuario.visualizar();
					System.out.println("SEJA BEM VINDO!");
					new MenuView(usuario).menuOpcoes();
				}

				case 2 -> {
					autenticacao.cadastrarUsuario();
					System.out.println("Acesse sua conta");
				}

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
