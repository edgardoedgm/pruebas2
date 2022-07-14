package com.pruebas.models.services;

import java.util.List;

import com.pruebas.models.entity.Registros;

public interface IRegistroServices {
	
	public List<Registros> finsAll();
	
	public Registros findById(Long id);
	
	public Registros Save(Registros registros);
	
	public void delete(Long id);

}
