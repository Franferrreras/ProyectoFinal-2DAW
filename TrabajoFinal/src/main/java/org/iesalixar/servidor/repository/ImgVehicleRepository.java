package org.iesalixar.servidor.repository;

import org.iesalixar.servidor.models.ImgVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgVehicleRepository extends JpaRepository<ImgVehicle, Long> {
	
	public ImgVehicle findImgVehicleByImagen(String img);
}
