package ecommerce.repository;

import ecommerce.model.produto.Produto;

public interface ProdutoRepository {
	
	public Produto procurarProdutoPorCodigo(int codigo);
	
	public void listarTodosProdutos();
	
	public void cadastrarProduto(Produto produto);
	
	public void atualizarProduto(Produto produto);
	
	public void deletarProduto(Produto produto);
	
}
