package com.retooling.egg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retooling.egg.entity.Egg;
import com.retooling.egg.exception.EggNotFoundException;
import com.retooling.egg.exception.EggValidationErrorException;
import com.retooling.egg.service.EggService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class EggController {

	private static final Logger logger = LoggerFactory.getLogger(EggController.class);
	
	@Autowired
	EggService service;
	
	//Obtener todas los huevos
	@GetMapping("eggs")
	public ResponseEntity<List<Egg>> getAllEggs() {
		logger.info("Controller - Calling method getAllEggs...");
		return new ResponseEntity<>(service.getAllEggs(), HttpStatus.OK);
	}

	//Obtener un huevo por id
	@GetMapping("eggs/{id}")
	public ResponseEntity<Egg> getEggById(@PathVariable("id") String id) throws EggNotFoundException {
		logger.info("Controller - Calling method getEggById con id=" + id);
		return new ResponseEntity<>(service.getEggById(id), HttpStatus.OK);				
	}
	
	//Guardar un huevo
	@PostMapping("eggs")
	public ResponseEntity<Egg> createEgg(@Valid @RequestBody Egg egg, BindingResult bindingResult)
			throws EggValidationErrorException {
		logger.info("Controller - Calling method createEgg...");
		if (bindingResult.hasErrors()) {
			String message = new String();
			for(FieldError error : bindingResult.getFieldErrors()) {
				if (message.isEmpty()) {
					message = message + error.getField() + " : " + error.getDefaultMessage();
				} else {
					message = message + ", " + error.getField() + " : " + error.getDefaultMessage();
				}
			}
			throw new EggValidationErrorException(message);
		}
		return new ResponseEntity<>(service.saveEgg(egg), HttpStatus.CREATED);				
	}

	//Actualizar datos de un huevo
	@PutMapping("eggs")
	public ResponseEntity<Egg> updateEgg(@Valid @RequestBody Egg eggUpdated, BindingResult bindingResult)
			throws EggNotFoundException, EggValidationErrorException {
		logger.info("Controller - Calling method updateEgg...");
		if (bindingResult.hasErrors()) {
			String message = new String();
			for(FieldError error : bindingResult.getFieldErrors()) {
				if (message.isEmpty()) {
					message = message + error.getField() + " : " + error.getDefaultMessage();
				} else {
					message = message + ", " + error.getField() + " : " + error.getDefaultMessage();
				}
			}
			throw new EggValidationErrorException(message);
		}
		Egg egg = service.getEggById(eggUpdated.getEggId());
		BeanUtils.copyProperties(eggUpdated, egg);
		return new ResponseEntity<>(service.saveEgg(egg), HttpStatus.OK);
	}
	
	//Borrar un huevo
	@DeleteMapping("eggs/{id}")
	public ResponseEntity<Egg> deleteEgg(@PathVariable("id") String id) throws EggNotFoundException{
		logger.info("Controller - Calling method deleteEgg...");
		Egg egg = service.getEggById(id);
		service.deleteEgg(egg);
		return new ResponseEntity<>(egg, HttpStatus.OK);
	}
	
	//Obtener huevos por id de granja
	@GetMapping("eggs/farms/{id}")
	public ResponseEntity<List<Egg>> getEggsByFarmId(@PathVariable("id") String id) throws EggNotFoundException {
		logger.info("Controller - Calling method getEggsByFarmId..."); 
		return new ResponseEntity<>(service.getEggsByFarmId(id), HttpStatus.OK);				
	}
	
}