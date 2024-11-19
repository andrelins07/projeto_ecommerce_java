package ecommerce.model.usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ecommerce.model.compra.Compra;
import ecommerce.model.pagamento.Pagamento;

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
		System.out.println("ENTREI");
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

	public void realizarCompra(String nomeProduto, int quantidade, LocalDate dataCompra, Pagamento pagamento, String cpf) {
	
		historicoCompra.add(new Compra(nomeProduto, pagamento.getValor(), quantidade, dataCompra, pagamento.getPagamento(), cpf));
		
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
		System.out.printf("Endereco Entrega: %s | Saldo na Carteira: %.2f\n", this.enderecoEntrega, this.creditoCarteira);
	}

	@Override
	public String toString() {
		return "Nome = " + getNome() + " | Idade = " + getIdade() + " | Login = " + getLogin() + " | Senha = " + getSenha() + 
				"\nEndereco de Entrega: " + this.enderecoEntrega + "  Saldo na Carteira: " + this.creditoCarteira + "\n";
	}
	
}
