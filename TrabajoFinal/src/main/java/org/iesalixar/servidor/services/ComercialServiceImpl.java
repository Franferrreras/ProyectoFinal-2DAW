package org.iesalixar.servidor.services;

import java.util.List;

import org.iesalixar.servidor.models.Comercial;
import org.iesalixar.servidor.repository.ComercialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComercialServiceImpl implements ComercialService {

	@Autowired
	ComercialRepository comercialRepo;

	@Override
	public List<Comercial> getAllComercial() {
		// TODO Auto-generated method stub
		return comercialRepo.findAll();
	}

	@Override
	public Comercial insertComercial(Comercial comercial) {
		// TODO Auto-generated method stub
		if (comercial != null) {
			Comercial cmrcialDB = comercialRepo.save(comercial);
			return cmrcialDB;
		}
		return null;
	}

	@Override
	public Comercial updateComercial(Comercial comercial) {
		// TODO Auto-generated method stub
		return comercialRepo.save(comercial);
	}

	@Override
	public Comercial removeComercial(Comercial comercial) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comercial getComercialById(Long id) {
		// TODO Auto-generated method stub
		return comercialRepo.findById(id).get();
	}
	
	
}
