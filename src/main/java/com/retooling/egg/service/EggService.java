package com.retooling.egg.service;

import java.util.List;

import com.retooling.egg.entity.Egg;
import com.retooling.egg.exception.EggNotFoundException;

public interface EggService {
	
	public List<Egg> getAllEggs();

	public Egg getEggById(String id) throws EggNotFoundException;
	
	public Egg saveEgg(Egg egg);
	
	public void deleteEgg(Egg egg);
	
	public List<Egg> getEggsByFarmId(String idFarm) throws EggNotFoundException;
	
}
