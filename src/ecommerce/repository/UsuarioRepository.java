package ecommerce.repository;

import ecommerce.model.produto.Produto;
import ecommerce.model.usuario.Usuario;

public interface UsuarioRepository {
	
	
	public Usuario procurarUsuarioPorLogin(String agencia);
	
	public void cadastrarUsuario(Usuario usuario);
	
	public void atualizarUsuario(Usuario usuario);
	
	public void deletarUsuario(Usuario usuario);
	
	public void comprar(Produto produto, Usuario usuario);
	
	public void visualizarCompras(Usuario usuario);
}
