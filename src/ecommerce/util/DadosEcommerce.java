package ecommerce.util;

import java.util.ArrayList;
import ecommerce.model.usuario.Usuario;
import ecommerce.model.produto.Produto;
import ecommerce.exception.RegraDeNegocioException;
import ecommerce.model.compra.Compra;

public class DadosEcommerce {

	private static ArrayList<Usuario> usuarios = ManipularJson.carregarJson(Arquivos.USUARIOS, Usuario.class);
	private static ArrayList<Produto> produtos = ManipularJson.carregarJson(Arquivos.PRODUTOS, Produto.class);
	private static ArrayList<Compra> compras = ManipularJson.carregarJson(Arquivos.COMPRAS, Compra.class);

	public static ArrayList<Usuario> carregarTodosUsuarios() {
		return usuarios;
	}

	public static ArrayList<Produto> carregarTodosProdutos() {
		Produto.setTotalProdutos(produtos.size() + 1);
		return produtos;
	}

	public static ArrayList<Compra> carregarTodasCompras() {
		return compras;
	}

	public static Usuario carregarUsuario(String login, int senha) {

		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(login) && usuario.getSenha() == senha) {
				return usuario;
			}
		}
		throw new RegraDeNegocioException("Usuario não encontrado. Verifique seu login e senha");
	}
	

	public static ArrayList<Compra> carregarComprasCliente(Usuario cliente) {

		ArrayList<Compra> comprasCliente = new ArrayList<Compra>();

		compras.forEach(compra -> {

			if (compra.getCpfCliente().equals(cliente.getCpf())) {
				comprasCliente.add(compra);
			}
		});

		Compra.setTotalCompras(compras.size() + 1);

		return comprasCliente;
	}

	public static void registrarCompra(Compra compra) {

		compras.add(compra);
	}

}
