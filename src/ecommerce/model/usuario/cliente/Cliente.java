package ecommerce.model.usuario.cliente;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ecommerce.model.compra.Compra;
import ecommerce.model.usuario.Role;
import ecommerce.model.usuario.Usuario;

public class Cliente extends Usuario {
	
	private String enderecoEntrega;
	private float creditoCarteira;
	@JsonIgnore(value = true)
	private ArrayList<Compra> historicoCompra;
	
	public Cliente() {
		
	}

	public Cliente(String nome, int idade, String cpf, String login, int senha, String endereco) {
		super(nome, idade, cpf, login, senha, Role.CLIENTE.getValue());
		this.enderecoEntrega = endereco;
		historicoCompra = new ArrayList<>();
	}
	public float getCreditoCarteira() {
		return creditoCarteira;
	}

	public void setCreditoCarteira(float creditoCarteira) {
		this.creditoCarteira = creditoCarteira;
	}

	public void adicionarSaldoCarteira(float creditoCarteira) {
		this.creditoCarteira += creditoCarteira;
	}
	
	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
	
	public ArrayList<Compra> visualizarCompras() {
		
		return historicoCompra;
	}
	
	public ArrayList<Compra> getHistoricoCompra() {
		return historicoCompra;
	}
	
	public void setHistoricoCompra(ArrayList<Compra> historicoCompra) {
		this.historicoCompra = historicoCompra;
	}

	public void realizarCompra(Compra novaCompra) {
	
		historicoCompra.add(novaCompra);
		
		if(novaCompra.getFormaPagamento().equals("CREDITO")) {
			this.creditoCarteira -= novaCompra.getPreco();
		}
	}
	
	@Override
	public void visualizar() {
		System.out.printf("""
				\n****************************************************************
				Cliente:
				****************************************************************\n""");
		super.visualizar();
		System.out.printf("Saldo na Carteira: %.2f | Endereco Entrega: %s \n", this.creditoCarteira, this.enderecoEntrega);
	}

	@Override
	public String toString() {
		return "Nome = " + getNome() + " | Idade = " + getIdade() + " | Login = " + getLogin() + " | Senha = " + getSenha() + 
				"\nEndereco de Entrega: " + this.enderecoEntrega + "  Saldo na Carteira: " + this.creditoCarteira + "\n";
	}

	public void atualizar(DadosAtualizacaoCliente dadosAtualizados) {
		
		this.setLogin(dadosAtualizados.login());
		this.setSenha(dadosAtualizados.senha());
		this.enderecoEntrega = dadosAtualizados.endereco();		
	}
}
