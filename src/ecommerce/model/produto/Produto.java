package ecommerce.model.produto;

public class Produto {

	private int codigo;
	private String nome;
	private float preco;
	private int estoque;
	private boolean restricaoIdade;
		
	public Produto(int codigo, String nome, float preco, int estoque, boolean restricaoIdade) {
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
		this.estoque = estoque;
		this.restricaoIdade = restricaoIdade;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public int getEstoque() {
		return estoque;
	}
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	public boolean isRestricaoIdade() {
		return restricaoIdade;
	}
	public void setRestricaoIdade(boolean restricaoIdade) {
		this.restricaoIdade = restricaoIdade;
	}
	
	
}
