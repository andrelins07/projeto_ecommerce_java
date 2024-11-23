package ecommerce.model.produto;

public class Produto {

	private static int totalProdutos = 1;
	private int codigo;
	private String nome;
	private float preco;
	private int estoque;
	private boolean restricaoIdade;

	public Produto() {

	}

	public Produto(String nome, float preco, int estoque, boolean restricaoIdade) {
		this.nome = nome;
		this.preco = preco;
		this.estoque = estoque;
		this.restricaoIdade = restricaoIdade;
		this.codigo = totalProdutos++;
	}

	public static void setTotalProdutos(int total) {
		totalProdutos = total;
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

	public static int getTotalProdutos() {
		return totalProdutos;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void visualizarProduto() {
		System.out.printf("Codigo: %d | Produto: %s | Preco: %.2f | Estoque: %d | Restricao de Idade: %s\n",
				this.codigo, this.nome, this.preco, this.estoque, this.restricaoIdade);
	}

}
