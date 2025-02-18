package ecommerce.menu;

import ecommerce.controller.ProdutoController;
import ecommerce.controller.UsuarioController;
import ecommerce.model.usuario.Usuario;
import ecommerce.service.CompraService;
import ecommerce.service.ProdutoService;
import ecommerce.service.UsuarioService;

public abstract class Menu implements InterfaceMenu {

	private final UsuarioController usuarioController;
	private final ProdutoController produtoController;
	private final UsuarioService usuarioService = new UsuarioService();
	private final ProdutoService produtoService = new ProdutoService();
	private final CompraService compraService = new CompraService();

	public Menu(Usuario usuario) {
		usuarioController = new UsuarioController(usuario, usuarioService, produtoService, compraService);
		produtoController = new ProdutoController(produtoService);
	}

	public UsuarioController getUsuarioController() {
		return usuarioController;
	}

	public ProdutoController getProdutoController() {
		return produtoController;
	}
}
