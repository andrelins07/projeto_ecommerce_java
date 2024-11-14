package ecommerce.model;

public class Produto {

	private static int totalProduto = 1;
	private int codigo;
	private String nome;
	private float preco;
	private int estoque;
	private boolean restricaoIdade;
		
	public Produto(String nome, float preco, int estoque, boolean restricaoIdade) {
		this.nome = nome;
		this.preco = preco;
		this.estoque = estoque;
		this.restricaoIdade = restricaoIdade;
		this.codigo = totalProduto++;
	}
	
	public void atualizar(int codigo, float preco, int estoque) {
		if(this.codigo != codigo) {
			this.codigo = codigo;
		}
		if(this.preco != preco) {
			this.preco = preco;
		}
		if(this.estoque != estoque) {
			this.estoque = estoque;
		}
	}
	
	public int getCodigo() {
		return codigo;
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
	public void setEstoque(int quantidade) {
		this.estoque += quantidade;
	}
	public boolean isRestricaoIdade() {
		return restricaoIdade;
	}
	public void setRestricaoIdade(boolean restricaoIdade) {
		this.restricaoIdade = restricaoIdade;
	}
	public void visualizarProduto() {
		System.out.printf("Codigo: %d | Produto: %s | Preco: %.2f\n", this.codigo, this.nome, this.preco);
	}
	
	@Override
	public String toString() {
		return "Codigo = " + codigo + " | Produto = " + nome + " | Preco = " + preco + " | Estoque = " + estoque
				+ " | Restricao de Idade = " + restricaoIdade;
	}
	
	
}
