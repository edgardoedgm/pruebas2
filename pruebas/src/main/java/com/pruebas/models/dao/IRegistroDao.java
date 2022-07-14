package com.pruebas.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.pruebas.models.entity.Registros;

public interface IRegistroDao extends CrudRepository <Registros, Long>{
	
	

}
