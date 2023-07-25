package com.retooling.egg.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.retooling.egg.entity.Egg;
import com.retooling.egg.exception.EggNotFoundException;
import com.retooling.egg.repository.EggRepository;

@Service
public class EggServiceImpl implements EggService {

	private static final Logger logger = LoggerFactory.getLogger(EggServiceImpl.class);
	
	@Autowired
	EggRepository repository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public List<Egg> getAllEggs() {
		logger.info("Service - Calling method getAllEggs...");
		return repository.findAll();
	}

	@Override
	public Egg getEggById(String id) throws EggNotFoundException{
		logger.info("Service - Calling method getChickenById...");
		Egg egg = repository.findByEggId(id);
		if (egg != null) {
			return egg;
		} else {
			throw new EggNotFoundException("Egg not found with id=" + id); 
		}	
	}
	
	@Override
	public Egg saveEgg(Egg egg) {
		logger.info("Service - Calling method saveEgg...");
		return repository.save(egg);
	}
	
	@Override
	public void deleteEgg(Egg egg) {
		logger.info("Service - Calling method deleteEgg...");
		repository.delete(egg);
	}

	@Override
	public List<Egg> getEggsByFarmId(String idFarm) throws EggNotFoundException {
		logger.info("Service - Calling method getEggsByFarmId...");
		Query query = new Query();
		query.addCriteria(Criteria.where("farmId").is(idFarm));
		List<Egg> eggs = mongoTemplate.find(query, Egg.class);
		if (!eggs.isEmpty()) {
			return eggs;	
		} else {
			throw new EggNotFoundException("Eggs not found");
		}
	}
	
}
