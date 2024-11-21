package ecommerce.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ecommerce.exception.RegraDeNegocioException;
import ecommerce.model.produto.Produto;
import ecommerce.model.usuario.Role;
import ecommerce.model.usuario.Usuario;
import ecommerce.repository.ProdutoRepository;
import ecommerce.util.Cores;
import ecommerce.util.DadosEcommerce;
import ecommerce.util.Leitura;

public class ProdutoController  implements ProdutoRepository {
	
	private static ArrayList<Produto> produtos;
	private Produto produto;
	private Usuario usuario;
	
	public ProdutoController(Usuario usuario) {
		this.usuario = usuario;
		produtos = DadosEcommerce.carregarTodosProdutos();
	}
	
	@Override
	public Produto buscarProdutoPorCodigo() {
		
		int codigo = Leitura.lerInteiro("Digite o codigo do produto: ");
		
		for (Produto produto : produtos) {
			if(produto.getCodigo() == codigo) {
				return produto;
			}
		}
		throw new RegraDeNegocioException("Produto não encontrado");	
	}
	
	@Override
	public List<Produto> buscarProdutos() {
		
		String nome = Leitura.lerString("Digite o nome do produto que deseja buscar: ");
		
		List<Produto> produtosFiltrados =  produtos.stream()
        	.filter(p -> p.getNome().toLowerCase().contains(nome.toLowerCase()))
        	.toList();
		
		if(produtosFiltrados.isEmpty()) {
			throw new RegraDeNegocioException("Nenhum produto encontrado!");
		}
		
		produtosFiltrados.forEach(p->p.visualizarProduto());
		
		return produtosFiltrados;		
	}

	@Override
	public void listarTodosProdutos() {		
		
		if(produtos.isEmpty()) {
			throw new RegraDeNegocioException("Nenhum produto cadastrado");	
		}
		
		if(!usuario.getRole().equals(Role.FUNCIONARIO.getValue())) {
			produtos.forEach(produto->produto.visualizarProduto());
		}else {
			produtos.forEach(produto->produto.visualizarProduto());		
		}
		
	}

	@Override
	public void cadastrarProduto() {
		
		validaPermissao();
		
		System.out.println("Cadastrando Produto: \n");
		
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

		produto = new Produto(nomeProduto, preco, estoque, restricaoIdade);
		
		if(produtos.contains(produto)) {
			throw new RegraDeNegocioException("Produto já cadastrado");
		}
		produtos.add(this.produto);
		
		System.out.println(Cores.TEXT_GREEN + "Produto cadastrado com sucesso!");
		
		produto.visualizarProduto();

	}
	
	@Override
	public void atualizarProduto() {
		
		validaPermissao();
		
		produto = buscarProdutoPorCodigo();
		
		produto.visualizarProduto();
		
		System.out.println("\nAtualizando produto: \n");
		
		int codigo = Leitura.lerInteiro("Alterando o codigo: ");
		float preco = Leitura.lerFloat("Alterando o preco do produto: ");
		int estoque = Leitura.lerInteiro("Alterando a quantidade do estoque: ");
		
		produto.atualizar(codigo, preco, estoque);
		
		System.out.println(Cores.TEXT_GREEN + "Dados atualizados com sucesso!");
		
	}

	@Override
	public void deletarProduto() {
		
		validaPermissao();
		
		produto = buscarProdutoPorCodigo();
		
		produto.visualizarProduto();

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
	
	public static List<Produto> getProdutos() {
		return Collections.unmodifiableList(produtos);
	}
	
	private void validaPermissao() {
		
		if(!this.usuario.getRole().equals(Role.FUNCIONARIO.getValue())) {
			throw new RegraDeNegocioException("Acesso apenas para funcionarios!");
		}	
	}
}
