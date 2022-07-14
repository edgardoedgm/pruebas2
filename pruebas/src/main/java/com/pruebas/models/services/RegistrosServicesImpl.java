package com.pruebas.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebas.models.dao.IRegistroDao;
import com.pruebas.models.entity.Registros;

@Service
public class RegistrosServicesImpl implements IRegistroServices {

	@Autowired
	private IRegistroDao registroDao;

	@Override
	@Transactional(readOnly = true)
	public List<Registros> finsAll() {

		return (List<Registros>) registroDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Registros findById(Long id) {
	
		
		return registroDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Registros Save(Registros registros) {
		
		
		return registroDao.save(registros);
	}

	@Override
	@Transactional
	public void delete(Long id) {

		registroDao.deleteById(id);

	}

}
