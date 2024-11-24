package ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import ecommerce.exception.RegraDeNegocioException;
import ecommerce.model.produto.DadosAtualizacaoProduto;
import ecommerce.model.produto.Produto;
import ecommerce.repository.ProdutoRepository;
import ecommerce.util.Arquivos;
import ecommerce.util.ManipularJson;

public class ProdutoService implements ProdutoRepository {

	private ArrayList<Produto> produtos = ManipularJson.carregarJson(Arquivos.PRODUTOS, Produto.class);
	private List<Produto> produtosFiltrados;

	public ProdutoService() {
		Produto.setTotalProdutos(produtos.size() + 1);
	}
	
	@Override
	public Produto buscarProdutoPorCodigo(int codigo) {

		for (Produto produto : produtos) {
			if (produto.getCodigo() == codigo) {
				return produto;
			}
		}
		throw new RegraDeNegocioException("Produto não encontrado");
	}

	@Override
	public List<Produto> filtrarProdutos(String nomeProduto) {

		produtosFiltrados = produtos.stream().filter(p -> p.getNome().toLowerCase().contains(nomeProduto.toLowerCase()))
				.toList();

		if (produtosFiltrados.isEmpty()) {
			throw new RegraDeNegocioException("Nenhum produto encontrado!");
		}

		return produtosFiltrados;
	}

	public Produto selecionarProdutoDoFiltro(int codigo) {

		for (Produto produto : produtosFiltrados) {
			if (produto.getCodigo() == codigo) {
				return produto;
			}
		}
		throw new RegraDeNegocioException("Opcao nao localizada no filtro!");

	}

	@Override
	public List<Produto> listarTodosProdutos() {
		
		if(produtos.isEmpty()) {
			throw new RegraDeNegocioException("Nenhum produto cadastrado");	
		}
		return produtos;
	}

	@Override
	public void cadastrarProduto(Produto produto) {

		if(produtos.contains(produto)) {
			throw new RegraDeNegocioException("Produto já cadastrado");
		}
		
		produtos.add(produto);
	}

	@Override
	public void atualizarProduto(Produto produto, DadosAtualizacaoProduto dadosAtualizado) {

		if(!dadosAtualizado.nome().equals(produto.getNome())) {
			produto.setNome(dadosAtualizado.nome());
		}
		if(dadosAtualizado.preco() != produto.getPreco()) {
			produto.setPreco(dadosAtualizado.preco());
		}
		if(dadosAtualizado.estoque() != produto.getPreco()) {
			produto.setPreco(dadosAtualizado.preco());
		}
	}

	@Override
	public void deletarProduto(Produto produto) {
		produtos.remove(produto);
	}

	public void validarEstoque(Produto produtoEscolhido, int quantidade) {

		if (produtoEscolhido.getEstoque() < 0 || quantidade > produtoEscolhido.getEstoque())
			throw new RegraDeNegocioException("Estoque insuficiente!");
	}

}
