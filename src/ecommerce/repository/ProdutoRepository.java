package ecommerce.repository;

public interface ProdutoRepository {
	
	public void procurarProdutoPorCodigo();
	
	public void listarTodosProdutos();
	
	public void cadastrarProduto();
	
	public void atualizarProduto();
	
	public void deletarProduto();
	
}
