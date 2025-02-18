package ecommerce.menu;

import ecommerce.model.usuario.Usuario;
import ecommerce.model.usuario.cliente.Cliente;
import ecommerce.util.Cores;
import ecommerce.util.Leitura;

public class MenuCliente extends Menu {

	public MenuCliente(Usuario usuario) {
		super(usuario);
		getUsuarioController().carregarComprasCliente((Cliente) usuario);
	}

	@Override
	public void exibirMenu() {
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

				case 3 -> {
					getUsuarioController().deletarUsuario();
					return;
				}

				case 4 -> getProdutoController().listarTodosProdutos();

				case 5 -> getProdutoController().buscarProdutos();

				case 6 -> getUsuarioController().comprar();

				case 7 -> getUsuarioController().visualizarCompras();

				case 8 -> getUsuarioController().adicionarSaldoCarteira();

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