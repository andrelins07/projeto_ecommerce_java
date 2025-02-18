package ecommerce.menu;

import ecommerce.model.usuario.Usuario;
import ecommerce.util.Cores;
import ecommerce.util.Leitura;

public class MenuFuncionario extends Menu {

	public MenuFuncionario(Usuario usuario) {
		super(usuario);
	}

	@Override
	public void exibirMenu() {
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
	}

	@Override
	public void executar() {

		while (true) {
			try {
				exibirMenu();

				int opcao = Leitura.lerInteiro("Digite a opcao Desejada: ");
				System.out.println();
				if (opcao == 0) {
					getUsuarioController().salvarAlteracoes();
					System.out.println("Obrigado por comprar com a gente. Volte sempre!\n");
					break;
				}

				switch (opcao) {

				case 1 -> getUsuarioController().visualizarUsuario();

				case 2 -> getUsuarioController().atualizarUsuario();

				case 3 -> getUsuarioController().deletarUsuario();

				case 4 -> getUsuarioController().imprimirUsuarios();

				case 5 -> getProdutoController().cadastrarProduto();

				case 6 -> getProdutoController().listarTodosProdutos();

				case 7 -> getProdutoController().buscarProdutos();

				case 8 -> getProdutoController().atualizarProduto();

				case 9 -> getProdutoController().deletarProduto();

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
