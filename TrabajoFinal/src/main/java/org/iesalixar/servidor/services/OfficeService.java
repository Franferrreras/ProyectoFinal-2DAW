package org.iesalixar.servidor.services;

import java.util.List;

import org.iesalixar.servidor.models.Office;

public interface OfficeService {

	public List<Office> getAllOffices();
	public Office insertOffice(Office office);
}
