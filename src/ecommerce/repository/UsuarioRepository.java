package ecommerce.repository;

import ecommerce.model.usuario.Usuario;

public interface UsuarioRepository {
	
	public Usuario buscarUsuario();
	
	public void visualizarUsuario();
	
	public void cadastrarUsuario( );
	
	public void atualizarUsuario();
	
	public void deletarUsuario();
	
	public void comprar();
	
	public void visualizarCompras();
}
