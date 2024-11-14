package ecommerce;

import ecommerce.controller.ProdutoController;
import ecommerce.controller.UsuarioController;
import ecommerce.util.Cores;
import ecommerce.util.Leitura;

public class Menu {

	public static void main(String[] args) {

		UsuarioController usuario = new UsuarioController();
		ProdutoController produto = new ProdutoController();

		while (true) {

			System.out.print(Cores.TEXT_YELLOW + """
					***************************************************************
							     MERCADO DOS SONHOS
					***************************************************************
					1 - Cadastrar Perfil          | 2 - Atualizar Perfil
					3 - Excluir Perfil            | 4 - Visualizar Perfil
					---------------------------------------------------------------
					5 - Cadastrar produto**       | 6 - Listar todos os produtos
					7 - Bucar produto por código  | 8 - Atualizar produto**
					9 - Excluir produto**
					---------------------------------------------------------------
					10 - Comprar*                 | 11 - Visualizar Compras*
					12 - Adicionar Saldo na Carteira
					---------------------------------------------------------------
					*Apenas Clientes **Apenas funcionarios
					---------------------------------------------------------------
					0 - Sair
					***************************************************************
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

				case 1 -> usuario.cadastrarUsuario();

				case 2 -> usuario.atualizarUsuario();

				case 3 -> usuario.deletarUsuario();

				case 4 -> usuario.procurarUsuarioPorLogin();

				case 5 -> produto.cadastrarProduto();

				case 6 -> produto.listarTodosProdutos();

				case 7 -> produto.procurarProdutoPorCodigo();

				case 8 -> produto.atualizarProduto();

				case 9 -> produto.deletarProduto();

				case 10 -> usuario.comprar();

				case 11 -> usuario.visualizarCompras();
				
				case 12 -> usuario.adicionarSaldoCarteira();

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
