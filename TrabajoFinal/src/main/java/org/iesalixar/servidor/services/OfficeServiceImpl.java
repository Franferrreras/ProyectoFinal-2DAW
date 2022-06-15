package org.iesalixar.servidor.services;

import java.util.List;

import org.iesalixar.servidor.models.Office;
import org.iesalixar.servidor.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeServiceImpl implements OfficeService {

	@Autowired
	OfficeRepository officeRepo;
	
	@Override
	public List<Office> getAllOffices() {
		// TODO Auto-generated method stub
		return officeRepo.findAll();
	}

	@Override
	public Office insertOffice(Office office) {
		// TODO Auto-generated method stub
		return officeRepo.save(office);
	}

}
