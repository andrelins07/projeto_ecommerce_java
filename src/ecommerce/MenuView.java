package ecommerce;

import ecommerce.controller.ProdutoController;
import ecommerce.controller.UsuarioController;
import ecommerce.model.usuario.Usuario;
import ecommerce.util.Cores;
import ecommerce.util.Leitura;

public class MenuView {

	private UsuarioController usuarioController;
	private ProdutoController produtoController;

	public MenuView(Usuario usuario) {
		usuarioController = new UsuarioController(usuario);
		produtoController = new ProdutoController(usuario);
	}

	public void menuOpcoes() {

		while (true) {
				exibirMenuOpcoes();
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

	private static void exibirMenuOpcoes() {

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
	}

}
