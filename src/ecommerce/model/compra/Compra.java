package ecommerce.model.compra;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Compra {
	
	private static int totalCompras = 1;
	
	private int idCompra;
	private String cpfCliente;
	private String nomeProduto;
	private float preco;
	private int quantidade;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataCompra;
	private String formaPagamento;
	
	public Compra() {
		
	}
	
	public Compra(String nomeProduto, float preco, int quantidade, LocalDate dataCompra, String pagamento, String cpfCliente) {
		this.nomeProduto = nomeProduto;
		this.preco = preco;
		this.dataCompra = dataCompra;
		this.idCompra = totalCompras++;
		this.quantidade = quantidade;
		this.formaPagamento = pagamento;
		this.cpfCliente = cpfCliente;
	}
	
	public static void setTotalCompras(int total) {
		totalCompras = total;
	}
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getCpfCliente() {
		return cpfCliente;
	}
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public LocalDate getDataCompra() {
		return this.dataCompra;
	}
	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	@Override
	public String toString() {
		return "ID: " + idCompra + ", Produto = " + nomeProduto + ", Preco = " + preco + ", Data Compra = " + dataCompra + ", Quantidade = " + quantidade + ", Pagamento: " + formaPagamento;
	}
}
