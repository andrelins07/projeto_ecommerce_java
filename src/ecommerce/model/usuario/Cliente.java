package ecommerce.model.usuario;

import java.time.LocalDate;

public class Cliente extends Usuario {
	
	private String historicoCompra;
	private String enderecoEntrega;
	private final String ROLE = "cliente";
	
	public Cliente(String nome, LocalDate dataNascimento, String login, int senha, String endereco) {
		super(nome, dataNascimento, login, senha);
		this.enderecoEntrega = endereco;
	}

	public String getHistoricoCompra() {
		return historicoCompra;
	}

	public void setHistoricoCompra(String historicoCompra) {
		this.historicoCompra = historicoCompra;
	}

	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public String getROLE() {
		return ROLE;
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
	
}
