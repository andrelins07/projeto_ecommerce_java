package ecommerce;

import ecommerce.controller.ProdutoController;
import ecommerce.controller.UsuarioController;
import ecommerce.model.usuario.Usuario;
import ecommerce.util.Arquivos;
import ecommerce.util.Cores;
import ecommerce.util.Leitura;
import ecommerce.util.ManipularJson;

public class Menu {

	private static Usuario usuario;
	private static UsuarioController usuarioController = new UsuarioController();

	public static void main(String[] args) {

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
					ManipularJson.salvarAtualizacoes(Arquivos.USUARIOS, UsuarioController.getUsuarios());
					System.out.println("Finalizando...");
					sobre();
					break;
				}

				switch (opcao) {

				case 1 -> {
					usuario = usuarioController.buscarUsuario();
					System.out.println("SEJA BEM VINDO!");
					menuOpcoes();
				}

				case 2 -> {
					usuarioController.cadastrarUsuario();
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

	public static void menuOpcoes() {

		ProdutoController produtoController = new ProdutoController(usuario);
		usuarioController.criarProdutoControler(produtoController);

		while (true) {
			System.out.print(Cores.TEXT_YELLOW + """
					***************************************************************
							     MERCADO DOS SONHOS
					***************************************************************
					1 - Visualizar Perfil            | 2 - Atualizar Perfil
					3 - Excluir Perfil               | 4 - Visualizar todos perfis**
					---------------------------------------------------------------
					5 - Cadastrar produto**          | 6 - Listar todos os produtos
					7 - Bucar produto por código     | 8 - Atualizar produto**
					9 - Excluir produto**            |
					---------------------------------------------------------------
					10 - Comprar*                    | 11 - Visualizar Compras*
					12 - Adicionar Saldo na Carteira |
					---------------------------------------------------------------
					*Apenas Clientes **Apenas funcionarios
					---------------------------------------------------------------
					0 - Sair
					***************************************************************
					""" + Cores.TEXT_RESET);
			try {
				int opcao = Leitura.lerInteiro("Digite a opcao Desejada: ");
				System.out.println();
				if (opcao == 0) {
					System.out.println("Obrigado por comprar com a gente. Volte sempre!\n");
					ManipularJson.salvarAtualizacoes(Arquivos.PRODUTOS, ProdutoController.getProdutos());
					break;
				}

				switch (opcao) {

				case 1 -> usuarioController.visualizarUsuario();

				case 2 -> usuarioController.atualizarUsuario();

				case 3 -> usuarioController.deletarUsuario();

				case 4 -> usuarioController.imprimirUsuarios();

				case 5 -> produtoController.cadastrarProduto();

				case 6 -> produtoController.listarTodosProdutos();

				case 7 -> produtoController.buscarProdutos();

				case 8 -> produtoController.atualizarProduto();

				case 9 -> produtoController.deletarProduto();

				case 10 -> usuarioController.comprar();

				case 11 -> usuarioController.visualizarCompras();

				case 12 -> usuarioController.adicionarSaldoCarteira();

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
