package org.iesalixar.servidor.services;

import org.iesalixar.servidor.models.Usuario;
import org.iesalixar.servidor.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	UsuarioRepository userRepo;
	
	@Override
	public Usuario insertUsuario(Usuario usuario) {
		
		if (usuario!=null) {
			
			return userRepo.save(usuario);
		}
		
		return null;
		
	}

	@Override
	public Usuario findUsuarioByUserName(String name) {
		// TODO Auto-generated method stub
		return userRepo.findByUserName(name);
	}

	
}
