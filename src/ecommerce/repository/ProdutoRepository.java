package ecommerce.repository;

import java.util.List;

import ecommerce.model.produto.Produto;

public interface ProdutoRepository {
	
	public Produto buscarProdutoPorCodigo();
	
	public List<Produto> buscarProdutos();
	
	public void listarTodosProdutos();
	
	public void cadastrarProduto();
	
	public void atualizarProduto();
	
	public void deletarProduto();
	
}
