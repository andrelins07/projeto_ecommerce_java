package ecommerce;

import ecommerce.exception.RegraDeNegocioException;
import ecommerce.model.usuario.Usuario;
import ecommerce.model.usuario.cliente.Cliente;
import ecommerce.model.usuario.funcionario.Funcionario;
import ecommerce.service.UsuarioService;
import ecommerce.util.Cores;
import ecommerce.util.Leitura;

public class AutenticarUsuario {

	private UsuarioService usuarioService = new UsuarioService();
	
	public Usuario buscarUsuario() {

		String login = Leitura.lerString("Digite o seu login: ");
		int senha = Leitura.lerInteiro("Informe sua senha: ");

		Usuario usuario = usuarioService.buscarUsuario(login, senha);
		
		return usuario;
	}
	
	public Usuario cadastrarUsuario() {
		
		Usuario usuario;

		String nome = Leitura.lerString("Digite o seu nome: ");
		int idade = Leitura.lerInteiro("Digite a sua idade: ");
		
		if(idade < 15) {
			throw new RegraDeNegocioException("A idade minima para o cadastro é de 15 anos!");
		}
		
		String login = Leitura.lerString("Crie um login: ");
		String cpf = Leitura.lerString("Informe o seu cpf: ");
		int senha = Leitura.lerInteiro("Crie uma senha: ");
		int role = Leitura.lerInteiro("Informe seu perfil: \n1 - Cliente | 2 - Funcionario: ");

		switch (role) {
		case 1 -> {
			String endereco = Leitura.lerString("Informe seu endereco: ");
			usuario = new Cliente(nome, idade, cpf, login, senha, endereco);
			usuarioService.cadastrarUsuario(usuario);
		}
		case 2 -> {
			String cargo = Leitura.lerString("Informe o seu cargo: ");
			float salario = Leitura.lerFloat("Informe o seu salario: ");
			usuario = new Funcionario(nome, idade, cpf, login, senha, cargo, salario);
			usuarioService.cadastrarUsuario(usuario);
		}
		default -> throw new RegraDeNegocioException("Opcao invalida");
		}

		System.out.println(Cores.TEXT_GREEN + "Usuario cadastrado com sucesso!");
		return usuario;
	}
}
