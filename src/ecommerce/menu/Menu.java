package ecommerce.menu;

import ecommerce.controller.ProdutoController;
import ecommerce.controller.UsuarioController;
import ecommerce.model.usuario.Usuario;

public abstract class Menu {

	private UsuarioController usuarioController;
	private ProdutoController produtoController;
	
	public Menu(Usuario usuario) {
		usuarioController = new UsuarioController(usuario);
		produtoController = new ProdutoController();
	}
	protected void imprimirMenu() {
		System.out.println("Imprimindo menu");
	}
	
	public void executar() {
		System.out.println("Executando aplicação");
	}

	public UsuarioController getUsuarioController() {
		return usuarioController;
	}

	public ProdutoController getProdutoController() {
		return produtoController;
	}
}
