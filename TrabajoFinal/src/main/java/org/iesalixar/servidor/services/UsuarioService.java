package org.iesalixar.servidor.services;

import org.iesalixar.servidor.models.Usuario;

public interface UsuarioService {

	public Usuario insertUsuario(Usuario usuario);
	public Usuario findUsuarioByUserName(String name);
}
