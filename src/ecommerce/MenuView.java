package ecommerce;

import ecommerce.controller.ProdutoController;
import ecommerce.controller.UsuarioController;
import ecommerce.model.usuario.Role;
import ecommerce.model.usuario.Usuario;
import ecommerce.model.usuario.cliente.Cliente;
import ecommerce.util.Cores;
import ecommerce.util.Leitura;

public class MenuView {

	private UsuarioController usuarioController;
	private ProdutoController produtoController;

	public MenuView(Usuario usuario) {
		usuarioController = new UsuarioController(usuario);
		produtoController = new ProdutoController();
		definirRoleUsuario(usuario);
	}

	public void definirRoleUsuario(Usuario usuario) {

		if(usuario.getRole().equals(Role.CLIENTE.getValue())) {
			usuarioController.carregarComprasCliente((Cliente) usuario);
			menuOpcoesCliente();
		}
		if(usuario.getRole().equals(Role.FUNCIONARIO.getValue())) {
			menuOpcoesFuncionario();
		}
	}

	private void menuOpcoesCliente() {
	
		while (true) {
			System.out.print(Cores.TEXT_YELLOW + """
					***************************************************************
							     MERCADO DOS SONHOS
					***************************************************************
					1 - Visualizar Perfil           | 2 - Atualizar Perfil
					3 - Excluir Perfil              |
					---------------------------------------------------------------
					4 - Listar todos os produtos    | 5 - Bucar produto por nome
					---------------------------------------------------------------
					6 - Comprar                     | 7 - Visualizar Compras
					8 - Adicionar Saldo na Carteira |
					---------------------------------------------------------------
					0 - Sair
					***************************************************************
					""" + Cores.TEXT_RESET);
			try {
				int opcao = Leitura.lerInteiro("Digite a opcao Desejada: ");
				System.out.println();
				if (opcao == 0) {
					System.out.println("Obrigado por comprar com a gente. Volte sempre!\n");
					break;
				}

				switch (opcao) {

				case 1 -> usuarioController.visualizarUsuario();

				case 2 -> usuarioController.atualizarUsuario();

				case 3 -> usuarioController.deletarUsuario();

				case 4 -> produtoController.listarTodosProdutos();

				case 5 -> produtoController.buscarProdutos();

				case 6 -> usuarioController.comprar();

				case 7 -> usuarioController.visualizarCompras();

				case 8 -> usuarioController.adicionarSaldoCarteira();

				default -> System.out.println("\nOpção Inválida!\n");

				}
			} catch (RuntimeException exception) {
				System.out.println(Cores.TEXT_RED + exception.getMessage().toUpperCase());
				System.out.println("Pressione ENTER para voltar ao menu");
				Leitura.voltarMenu();
			}
		}

	}

	private void menuOpcoesFuncionario() {

		while (true) {
			System.out.print(Cores.TEXT_YELLOW + """
					***************************************************************
							     MERCADO DOS SONHOS
					***************************************************************
					1 - Visualizar Perfil          | 2 - Atualizar Perfil
					3 - Excluir Perfil             | 4 - Visualizar todos perfis
					---------------------------------------------------------------
					5 - Cadastrar produto          | 6 - Listar todos os produtos
					7 - Bucar produto por código   | 8 - Atualizar produto
					9 - Excluir produto            |
					---------------------------------------------------------------
					0 - Sair
					***************************************************************
					""" + Cores.TEXT_RESET);
			try {
				int opcao = Leitura.lerInteiro("Digite a opcao Desejada: ");
				System.out.println();
				if (opcao == 0) {
					System.out.println("Obrigado por comprar com a gente. Volte sempre!\n");
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

				default -> System.out.println("\nOpção Inválida!\n");

				}
			} catch (RuntimeException exception) {
				System.out.println(Cores.TEXT_RED + exception.getMessage().toUpperCase());
				System.out.println("Pressione ENTER para voltar ao menu");
				Leitura.voltarMenu();
			}
		}
	}
}
