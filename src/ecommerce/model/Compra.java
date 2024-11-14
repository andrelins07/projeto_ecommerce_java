package ecommerce.model;

import java.time.LocalDate;

public class Compra {
	
	private int idCompra;
	private String nomeProduto;
	private float preco;
	private int quantidade;
	private LocalDate dataCompra;
	
	public Compra(int id, String nomeProduto, float preco, int quantidade, LocalDate dataCompra) {
		this.nomeProduto = nomeProduto;
		this.preco = preco;
		this.dataCompra = dataCompra;
		this.idCompra = id;
		this.quantidade = quantidade;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public LocalDate getDataCompra() {
		return dataCompra;
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
	@Override
	public String toString() {
		return "ID: " + idCompra + ", Produto = " + nomeProduto + ", Preco = " + preco + ", Data Compra = " + dataCompra + ", Quantidade = " + quantidade;
	}
	
	
	
	

}
