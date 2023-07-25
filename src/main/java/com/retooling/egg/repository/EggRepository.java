package com.retooling.egg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.retooling.egg.entity.Egg;

public interface EggRepository extends MongoRepository<Egg, String>{

	//Necesita el atributo eggId en el modelo para funcionar
	Egg findByEggId(String id);
	
}
