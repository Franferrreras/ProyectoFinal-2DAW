package org.iesalixar.servidor.services;

import org.iesalixar.servidor.models.ImgVehicle;
import org.iesalixar.servidor.repository.ImgVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImgVehicleServiceImpl implements ImgVehicleService {

	@Autowired
	ImgVehicleRepository imgVRepo;
	
	@Override
	public ImgVehicle insertImg(ImgVehicle img) {
		// TODO Auto-generated method stub
		return imgVRepo.save(img);
	}

	@Override
	public ImgVehicle findImgByImg(String img) {
		// TODO Auto-generated method stub
		return imgVRepo.findImgVehicleByImagen(img);
	}

}
