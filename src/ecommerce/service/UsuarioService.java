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
		salvarAtualizacoes();
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
		
		funcionario.atualizar(dadosAtualizados);

	}

	@Override
	public void atualizarCliente(Cliente cliente, DadosAtualizacaoCliente dadosAtualizados) {
		cliente.atualizar(dadosAtualizados);
	}
	public void salvarAtualizacoes() {
		ManipularJson.salvarAtualizacoes(Arquivos.USUARIOS, usuarios);
	}
}
