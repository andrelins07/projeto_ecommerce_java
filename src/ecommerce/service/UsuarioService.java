package ecommerce.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ecommerce.exception.RegraDeNegocioException;
import ecommerce.model.compra.Compra;
import ecommerce.model.usuario.Usuario;
import ecommerce.model.usuario.cliente.Cliente;
import ecommerce.model.usuario.cliente.DadosAtualizacaoCliente;
import ecommerce.model.usuario.funcionario.DadosAtualizacaoFuncionario;
import ecommerce.model.usuario.funcionario.Funcionario;
import ecommerce.repository.UsuarioRepository;
import ecommerce.util.Arquivos;
import ecommerce.util.ManipularJson;

public class UsuarioService implements UsuarioRepository {

	private static final ArrayList<Usuario> usuarios = ManipularJson.carregarJson(Arquivos.USUARIOS, Usuario.class);

	@Override
	public Usuario buscarUsuario(String login, int senha) {
		
		Usuario usuario = null;
		
		for (Usuario user : usuarios) {
			if (user.getLogin().equals(login) && user.getSenha() == senha) {
				usuario = user;
				break;
			}
		}
		if (usuario == null)
			throw new RegraDeNegocioException("Usuario não encontrado. Verifique seu login e senha");

		return usuario;

	}
	
	@Override
	public List<Usuario> listarTodosUsuarios(){
		return Collections.unmodifiableList(usuarios);
	}
	
	@Override
	public void cadastrarUsuario(Usuario usuario) {

		if (usuarios.contains(usuario))
			throw new RegraDeNegocioException("Usuario já cadastrado");

		usuarios.add(usuario);
	}

	@Override
	public void deletarUsuario(Usuario usuario) {
		usuarios.remove(usuario);
	}
	
	@Override
	public void efetivarCompra(Compra compra, Cliente cliente) {
		
		cliente.realizarCompra(compra);
	}

	@Override
	public void atualizarFuncionario(Funcionario funcionario, DadosAtualizacaoFuncionario dadosAtualizados) {
		
		if(!dadosAtualizados.login().equals(funcionario.getLogin())) {
			funcionario.setLogin(dadosAtualizados.login());
		}
		if(dadosAtualizados.senha() != funcionario.getSenha()) {
			funcionario.setSenha(dadosAtualizados.senha());
		}
		if(!dadosAtualizados.cargo().equals(funcionario.getCargo())) {
			funcionario.setCargo(dadosAtualizados.cargo());
		}
		if(dadosAtualizados.salario() != funcionario.getSalario()) {
			funcionario.setSalario(dadosAtualizados.salario());
		}
	}

	@Override
	public void atualizarCliente(Cliente cliente, DadosAtualizacaoCliente dadosAtualizados) {
		
		if(!dadosAtualizados.login().equals(cliente.getLogin())) {
			cliente.setLogin(dadosAtualizados.login());
		}
		if(dadosAtualizados.senha() != cliente.getSenha()) {
			cliente.setSenha(dadosAtualizados.senha());
		}
		if(!dadosAtualizados.endereco().equals(cliente.getEnderecoEntrega())) {
			cliente.setEnderecoEntrega(dadosAtualizados.endereco());
		}
	}
}
