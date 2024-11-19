package ecommerce.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ecommerce.exception.RegraDeNegocioException;
import ecommerce.model.pagamento.*;
import ecommerce.model.produto.Produto;
import ecommerce.model.usuario.Cliente;
import ecommerce.model.usuario.Funcionario;
import ecommerce.model.usuario.Role;
import ecommerce.model.usuario.Usuario;
import ecommerce.repository.UsuarioRepository;
import ecommerce.util.*;

public class UsuarioController implements UsuarioRepository {

	private static ArrayList<Usuario> usuarios;
	private ProdutoController produtoController;
	private Usuario usuario;
	
	public UsuarioController() {
		usuarios = DadosEcommerce.carregarTodosUsuarios();
	}
	
	@Override
	public Usuario buscarUsuario() {
		
		String login = Leitura.lerString("Digite o seu login: ");
		int senha = Leitura.lerInteiro("Informe sua senha: ");
		
		for (Usuario user : usuarios) {
			if (user.getLogin().equals(login) && user.getSenha() == senha) {
				usuario = user;
				break;
			}
		}
		if(usuario == null)
			throw new RegraDeNegocioException("Usuario não encontrado. Verifique seu login e senha");
		
		
		if(usuario.getRole().equals(Role.CLIENTE.getValue())) {
			
			Cliente cliente = (Cliente) usuario;
			
			cliente.setHistoricoCompra(DadosEcommerce.carregarComprasCliente(usuario));
			
			usuario = cliente;
		}
		return usuario;
	}
	
	@Override
	public void visualizarUsuario() {
		usuario.visualizar();
	}

	@Override
	public void cadastrarUsuario() {

		String nome = Leitura.lerString("Digite o seu nome: ");
		int idade = Leitura.lerInteiro("Digite a sua idade: ");
		String login = Leitura.lerString("Crie um login: ");
		String cpf = Leitura.lerString("Informe o seu cpf: ");
		int senha = Leitura.lerInteiro("Crie uma senha: ");
		int role = Leitura.lerInteiro("Informe seu perfil: \n1 - Cliente | 2 - Funcionario: ");

		switch (role) {
			case 1 ->{
				String endereco = Leitura.lerString("Informe seu endereco: ");
				usuario = (Cliente) new Cliente(nome, idade, cpf, login, senha, endereco);
			}
			case 2 -> {
				String cargo = Leitura.lerString("Informe o seu cargo: ");
				float salario = Leitura.lerFloat("Informe o seu salario: ");
				usuario = (Funcionario) new Funcionario(nome, idade, cpf, login, senha, cargo, salario);
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
		
		System.out.println("Atualizando Login e Senha: ");
		
		if(usuario.getRole().equals(Role.CLIENTE.getValue())) {
			atualizarCliente((Cliente) usuario);
		}else {
			atualizarFuncionario((Funcionario) usuario);
		}
					
		System.out.println(Cores.TEXT_GREEN + "Dados atualizados com sucesso!");

	}
	
	@Override
	public void deletarUsuario() {

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
		
		if(!usuario.getRole().equals(Role.CLIENTE.getValue())) {
			throw new RegraDeNegocioException("Acesso apenas para clientes!");
		}
		
		Cliente cliente = (Cliente) usuario;
		
		List<Produto> opcoesProdutos = produtoController.buscarProdutos();
		
		Produto produtoEscolhido = null;
		
		int codigo = Leitura.lerInteiro("Digite o código do produto desejado: ");
		
		for (Produto produto : opcoesProdutos) {
			if(produto.getCodigo() == codigo) {
				produtoEscolhido = produto;
				break;
			}
		}
		
		if(produtoEscolhido == null) {
			throw new RegraDeNegocioException("Opcao nao localizada no filtro!");	
		}
	
		
		if(produtoEscolhido.isRestricaoIdade() && usuario.getIdade() < 18) {
			throw new RegraDeNegocioException("Esse produto é para maiores de 18!");	
		}
		
		int quantidade = Leitura.lerInteiro("Digite a quantidade desejada: ");
		
		if(produtoEscolhido.getEstoque() < 0 || quantidade > produtoEscolhido.getEstoque()) {
			throw new RegraDeNegocioException("Estoque insuficiente!");
		}
		
		pagar(cliente, produtoEscolhido, quantidade);
		
		produtoEscolhido.setEstoque(quantidade*-1);
		
		System.out.println(Cores.TEXT_GREEN + "Compra realizada com sucesso!");
		
	}
	
	@Override
	public void visualizarCompras() {
				
		if(!usuario.getRole().equals(Role.CLIENTE.getValue())) {
			throw new RegraDeNegocioException("Acesso apenas para clientes!");
		}
	
		Cliente cliente = (Cliente) usuario;
		
		if(cliente.visualizarCompras().isEmpty()) {
			throw new RegraDeNegocioException("Nenhuma compra realizada!");
		}
		
		System.out.println("Minhas Compras: ");
		
		cliente.visualizarCompras().forEach(System.out::println);
	}
	
	public void criarProdutoControler(ProdutoController produtoController) {
		this.produtoController = produtoController;
	}
	
	public void imprimirUsuarios() {
		
		if(!usuario.getRole().equals(Role.FUNCIONARIO.getValue())) {
			throw new RegraDeNegocioException("Acesso apenas para funcionarios!");
		}
		getUsuarios().forEach(user->user.visualizar());
	}
	
	public static List<Usuario> getUsuarios() {
		
		if(usuarios.isEmpty()) {
			throw new RegraDeNegocioException("Nenhum usuario cadastrado!");
		}
		
		return Collections.unmodifiableList(usuarios);
	}
		
	private void atualizarCliente(Cliente cliente) {
		
		System.out.println(cliente);
		
		String login = Leitura.lerString("Alterando o login: ");
		int senha = Leitura.lerInteiro("Alterando a senha: ");
		String endereco = Leitura.lerString("Alterando o endereco: ");
		
		cliente.atualizar(login, senha, endereco);
		
		cliente.visualizar();
		
	}
	
	private void atualizarFuncionario(Funcionario funcionario) {

		System.out.println(funcionario);
		
		String login = Leitura.lerString("Alterando o login: ");
		int senha = Leitura.lerInteiro("Alterando a senha: ");
		String cargo = Leitura.lerString("Alterando cargo: ");
		float salario = Leitura.lerFloat("Alterando salario: ");
		funcionario.atualizar(login, senha, cargo, salario);
	}
	
	public void adicionarSaldoCarteira() {
				
		if(!usuario.getRole().equals(Role.CLIENTE.getValue())) {
			throw new RegraDeNegocioException("Acesso apenas para clientes!");
		}
	
		Cliente cliente = (Cliente) usuario;
		
		System.out.println("Saldo atual: " + cliente.getCreditoCarteira());
		
		cliente.adicionarSaldoCarteira(Leitura.lerFloat("Digite o valor que deseja adicionar: "));
		
		System.out.println(Cores.TEXT_GREEN + "Saldo adicionado com sucesso!" + Cores.TEXT_RESET);
				
		cliente.visualizar();
		
	}
	
	public void pagar(Cliente cliente, Produto produto, int quantidade) {
		
		int formaPagamento = Leitura.lerInteiro("Qual a forma de pagamento ?\n1 - Credito | 2 - Débito");
		
		Pagamento pagamento;
		
		switch(formaPagamento) {

			case 1 -> pagamento = new PagamentoCredito(produto.getPreco() * quantidade, cliente);
			case 2 -> pagamento = new PagamentoDebito(produto.getPreco() * quantidade);
			default -> throw new RegraDeNegocioException("Opcao invalida!");
		}
		
		pagamento.processarPagamento();
	
		cliente.realizarCompra(produto.getNome(), quantidade, LocalDate.now(), pagamento, cliente.getCpf());
		
	}

}
