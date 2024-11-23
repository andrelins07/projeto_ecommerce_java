package ecommerce.repository;

import java.util.List;

import ecommerce.model.produto.DadosAtualizacaoProduto;
import ecommerce.model.produto.Produto;

public interface ProdutoRepository {
	
	public Produto buscarProdutoPorCodigo(int codigo);
	
	public List<Produto> filtrarProdutos(String nome);
	
	public List<Produto> listarTodosProdutos();
	
	public void cadastrarProduto(Produto produto);
	
	public void atualizarProduto(Produto produto, DadosAtualizacaoProduto dadosAtualizado);
	
	public void deletarProduto(Produto produto);
	
}
