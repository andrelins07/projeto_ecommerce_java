package ecommerce;

import ecommerce.exception.RegraDeNegocioException;
import ecommerce.menu.Menu;
import ecommerce.menu.MenuCliente;
import ecommerce.menu.MenuFuncionario;
import ecommerce.model.usuario.Role;
import ecommerce.model.usuario.Usuario;
import ecommerce.util.Cores;
import ecommerce.util.Leitura;

public class ApplicationEcommerce {

	private static Usuario usuario;

	public static void main(String[] args) {

		AutenticarUsuario autenticacao = new AutenticarUsuario();
		Menu menu;

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

				switch (opcao) {

				case 1 -> {
					usuario = autenticacao.buscarUsuario();
					if (usuario.getRole().equals(Role.CLIENTE.getValue())) {
						menu = new MenuCliente(usuario);
					} else if (usuario.getRole().equals(Role.FUNCIONARIO.getValue())) {
						menu = new MenuFuncionario(usuario);
					} else {
						throw new RegraDeNegocioException(
								"Não foi possível iniciar a aplicação. Perfil não identificado.");
					}
					menu.executar();
				}
				case 2 -> {
					usuario = autenticacao.cadastrarUsuario();
					System.out.printf(
							"Estamos felizes em te-lo conosco %s! Acesse sua conta para usar nossos servicos.\n",
							usuario.getNome());
				}

				}
				if (opcao == 0) {
					System.out.println("Finalizando...");
					sobre();
					break;
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
