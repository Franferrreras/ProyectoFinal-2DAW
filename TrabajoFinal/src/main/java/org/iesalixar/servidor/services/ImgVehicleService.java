package org.iesalixar.servidor.services;

import org.iesalixar.servidor.models.ImgVehicle;

public interface ImgVehicleService {

	public ImgVehicle insertImg(ImgVehicle img);
	public ImgVehicle findImgByImg(String img);
}
