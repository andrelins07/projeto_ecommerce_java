package ecommerce.controller;

import java.util.ArrayList;

import ecommerce.exception.RegraDeNegocioException;
import ecommerce.model.Produto;
import ecommerce.model.Role;
import ecommerce.model.Usuario;
import ecommerce.repository.ProdutoRepository;
import ecommerce.util.Cores;
import ecommerce.util.Leitura;

public class ProdutoController  implements ProdutoRepository{
	
	
	private static ArrayList<Produto> produtos = new ArrayList<Produto>();
	Produto produto;
	
	@Override
	public void procurarProdutoPorCodigo() {
		
		produto = buscarProduto();
		produto.visualizarProduto();
	}

	@Override
	public void listarTodosProdutos() {		
		
		if(produtos.isEmpty()) {
			throw new RegraDeNegocioException("Nenhum produto cadastrado");	
		}
		
		Usuario usuario = UsuarioController.buscarUsuario();
		
		if(!usuario.getRole().equals(Role.FUNCIONARIO)) {
			produtos.forEach(produto->produto.visualizarProduto());
		}else {
			produtos.forEach(System.out::println);		
		}
		
	}

	@Override
	public void cadastrarProduto() {
		
		validaPermissao();
		
		System.out.println("CADASTRANDO PRODUTO: ");
		
		String nomeProduto = Leitura.lerString("Digite o nome do produto: ");
		float preco = Leitura.lerFloat("Digite o preco do produto: ");
		int estoque = Leitura.lerInteiro("Digite a quantidade do estoque: ");
		int restricao = Leitura.lerInteiro("Produto é para +18 ?\n1 - SIM | 0 - NAO: ");
		
		boolean restricaoIdade;
		
		switch(restricao) {
			case 1-> restricaoIdade = true;
			case 0-> restricaoIdade = false;
			default -> throw new RegraDeNegocioException("Opcao invalida");
		}

		Produto novoProduto = new Produto(nomeProduto, preco, estoque, restricaoIdade);
		
		if(produtos.contains(produto)) {
			throw new RegraDeNegocioException("Produto já cadastrado");
		}
		produtos.add(novoProduto);

	}
	@Override
	public void atualizarProduto() {
		
		validaPermissao();
		
		produto = buscarProduto();
		
		System.out.println(produto);
		
		System.out.println("Atualizando produto: ");
		
		int codigo = Leitura.lerInteiro("Alterando o codigo: ");
		float preco = Leitura.lerFloat("Alterando o preco do produto: ");
		int estoque = Leitura.lerInteiro("Alterando a quantidade do estoque: ");
		
		produto.atualizar(codigo, preco, estoque);
		
		System.out.println(Cores.TEXT_GREEN + "Dados atualizados com sucesso!");
		
	}

	@Override
	public void deletarProduto() {
		
		validaPermissao();
		
		produto = buscarProduto();
		
		System.out.println(produto);

		System.out.println(Cores.TEXT_RED + "\nTem certeza que deseja excluir o produto?\n" + Cores.TEXT_RESET);

		int opcao = Leitura.lerInteiro("Digite '1 - SIM' ou '2 - NAO': ");

		switch (opcao) {
		case 1 -> {
			produtos.remove(produto);
			System.out.println(Cores.TEXT_GREEN + "\nProduto excluido com sucesso!");
		}
		case 2 -> System.out.println("Operacao cancelada!");

		default -> System.out.println(Cores.TEXT_RED + "Opcao invalida! Operacao cancelada");
		}
		
	}
	public static Produto buscarProduto() {
		
		int codigo = Leitura.lerInteiro("Digite o codigo do produto: ");
		
		for (Produto produto : produtos) {
			if(produto.getCodigo() == codigo) {
				return produto;
			}
		}
		throw new RegraDeNegocioException("Produto não encontrado");	
	}
	private void validaPermissao() {
	
		Usuario usuario = UsuarioController.buscarUsuario();
		
		if(!usuario.getRole().equals(Role.FUNCIONARIO)) {
			throw new RegraDeNegocioException("Usuario não tem permissão!");
		}	
	}
}
