package ecommerce.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import ecommerce.exception.RegraDeNegocioException;
import ecommerce.model.*;
import ecommerce.repository.UsuarioRepository;
import ecommerce.util.Cores;
import ecommerce.util.Leitura;

public class UsuarioController implements UsuarioRepository{

	private Usuario usuario;
	private static ArrayList<Usuario> usuarios = new ArrayList<>();

	
	@Override
	public void procurarUsuarioPorLogin() {
	
		usuario = buscarUsuario();
		usuario.visualizar();
	}

	@Override
	public void cadastrarUsuario() {

		String nome = Leitura.lerString("Digite o seu nome: ");
		int idade = Leitura.lerInteiro("Digite a sua idade: ");
		String login = Leitura.lerString("Crie um login: ");
		int senha = Leitura.lerInteiro("Crie uma senha: ");
		int role = Leitura.lerInteiro("Informe seu perfil: \n1 - Cliente | 2 - Funcionario: ");

		switch (role) {
			case 1 ->{
				String endereco = Leitura.lerString("Informe seu endereco: ");
				usuario = (Cliente) new Cliente(nome, idade, login, senha, endereco);
			}
			case 2 -> {
				String cargo = Leitura.lerString("Informe o seu cargo: ");
				float salario = Leitura.lerFloat("Informe o seu salario: ");
				usuario = (Funcionario) new Funcionario(nome, idade, login, senha, cargo, salario);
			}
			default -> throw new RegraDeNegocioException("Opcao invalida");
		}
		
		if(usuarios.contains(usuario))
			throw new RegraDeNegocioException("Usuario já cadastrado");
		
		usuarios.add(usuario);
		
		System.out.println(Cores.TEXT_GREEN + "Usuario cadastrado com sucesso!");
	}

	@Override
	public void atualizarUsuario() {

		usuario = buscarUsuario();

		System.out.println("Atualizando Login e Senha: ");
		
		if(usuario.getRole().equals(Role.CLIENTE)) {
			atualizarCliente((Cliente) usuario);
		}else {
			atualizarFuncionario((Funcionario) usuario);
		}
					
		System.out.println(Cores.TEXT_GREEN + "Dados atualizados com sucesso!");

	}
	
	@Override
	public void deletarUsuario() {

		usuario = buscarUsuario();

		usuario.visualizar();

		System.out.println(Cores.TEXT_RED + "\nTem certeza que deseja excluir o usuario?\n" + Cores.TEXT_RESET);

		int opcao = Leitura.lerInteiro("Digite '1 - SIM' ou '2 - NAO': ");

		switch (opcao) {
			case 1 -> {
				usuarios.remove(usuario);
				System.out.println(Cores.TEXT_GREEN + "\nUsuario excluido com sucesso!");
			}
			case 2 -> System.out.println("Operacao cancelada!");
	
			default -> System.out.println(Cores.TEXT_RED + "Opcao invalida! Operacao cancelada");
		}

	}
	@Override
	public void comprar() {
		
		usuario = buscarUsuario();
		
		if(!usuario.getRole().equals(Role.CLIENTE)) {
			throw new RegraDeNegocioException("Acesso restrito para usuario Funcionario!");
		}
		
		Produto produto = ProdutoController.buscarProduto();
		
		produto.visualizarProduto();
		
		if(produto.isRestricaoIdade() && usuario.getIdade() < 18) {
			throw new RegraDeNegocioException("Esse produto é para maiores de 18!");	
		}
		
		int quantidade = Leitura.lerInteiro("Digite a quantidade desejada: ");
		
		if(produto.getEstoque() < 0 || quantidade > produto.getEstoque()) {
			throw new RegraDeNegocioException("Estoque insuficiente!");
		}
		
		Cliente cliente = (Cliente) usuario;
		
		cliente.realizarCompra(produto.getNome(), produto.getPreco(), quantidade, LocalDate.now());
		
		produto.setEstoque(quantidade*-1);
		
		System.out.println(Cores.TEXT_GREEN + "Compra realizada com sucesso!");
	}
	@Override
	public void visualizarCompras() {

		usuario = buscarUsuario();
		
		if(!usuario.getRole().equals(Role.CLIENTE)) {
			throw new RegraDeNegocioException("Acesso restrito para usuario Funcionario!");
		}
	
		Cliente cliente = (Cliente) usuario;
		System.out.println("Minhas Compras: ");
		
		cliente.visualizarCompras().forEach(System.out::println);
	}
	public static Usuario buscarUsuario() {
		
		String login = Leitura.lerString("Digite o seu login: ");
		int senha = Leitura.lerInteiro("Informe sua senha: ");
		
		for (Usuario usuario : usuarios) {
			if (usuario.getLogin().equals(login) && usuario.getSenha() == senha) {
				return usuario;
			}
		}
		throw new RegraDeNegocioException("Usuario não encontrado. Verifique seu login e senha");
	}
	
	private void atualizarCliente(Cliente cliente) {
		
		System.out.println(cliente);
		
		String login = Leitura.lerString("Alterando o login: ");
		int senha = Leitura.lerInteiro("Alterando a senha: ");
		String endereco = Leitura.lerString("Alterando o endereco: ");
		cliente.atualizar(login, senha, endereco);
		
	}
	
	private void atualizarFuncionario(Funcionario funcionario) {

		System.out.println(funcionario);
		
		String login = Leitura.lerString("Alterando o login: ");
		int senha = Leitura.lerInteiro("Alterando a senha: ");
		String cargo = Leitura.lerString("Alterando cargo: ");
		float salario = Leitura.lerFloat("Alterando salario: ");
		funcionario.atualizar(login, senha, cargo, salario);
	}
	

}
