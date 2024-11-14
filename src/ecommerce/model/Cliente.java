package ecommerce.model;

import java.time.LocalDate;
import java.util.ArrayList;

import ecommerce.model.pagamento.Pagamento;

public class Cliente extends Usuario {
	
	private String enderecoEntrega;
	private float creditoCarteira;
	private ArrayList<Compra> historicoCompra;
	
	public Cliente(String nome, int idade, String login, int senha, String endereco) {
		super(nome, idade, login, senha);
		this.enderecoEntrega = endereco;
		this.setRole(Role.CLIENTE);
		historicoCompra = new ArrayList<>();
	}

	public float getCreditoCarteira() {
		return creditoCarteira;
	}

	public void adicionarSaldoCarteira(float creditoCarteira) {
		this.creditoCarteira = creditoCarteira;
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

	public void realizarCompra(String nomeProduto, int quantidade, LocalDate dataCompra, Pagamento pagamento) {
	
		int id = historicoCompra.size() + 1;
		historicoCompra.add(new Compra(id, nomeProduto, pagamento.getValor(), quantidade, dataCompra, pagamento.getPagamento()));
		
		if(pagamento.getPagamento().equals("CREDITO")) {
			this.creditoCarteira -= pagamento.getValor();
			System.out.println("Saldo na carteira: " + this.creditoCarteira);
		}
	}
	
	public void atualizar(String login, int senha, String endereco) {
		
		if(!getLogin().equals(login)) {
			setLogin(login);
		}
		if(getSenha() != senha) {
			setSenha(senha);
		}
		if(!this.enderecoEntrega.equals(endereco)) {
			this.enderecoEntrega = endereco;
		}
	}
	
	@Override
	public void visualizar() {
		System.out.printf("""
				\n****************************************************************
				Cliente:
				****************************************************************\n""");
		super.visualizar();
		System.out.printf("Endereco Entrega: %s\n\n", this.enderecoEntrega);
	}

	@Override
	public String toString() {
		return "Nome = " + getNome() + " | Idade = " + getIdade() + " | Login = " + getLogin() + " | Senha = " + getSenha() + 
				"\nEndereco de Entrega: " + this.enderecoEntrega + "  Saldo na Carteira: " + this.creditoCarteira;
	}
	
}
