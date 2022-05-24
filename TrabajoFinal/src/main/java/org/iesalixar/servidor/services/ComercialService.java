package org.iesalixar.servidor.services;

import java.util.List;

import org.iesalixar.servidor.models.Comercial;

public interface ComercialService {

	public List<Comercial> getAllComercial();
	public Comercial insertComercial(Comercial comercial);
	public Comercial updateComercial(Comercial comercial);
	public Comercial removeComercial(Comercial comercial);
	public Comercial getComercialById(Long id);
}
