package com.example.nosqldemo.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nosqldemo.domain.Planeta;
import com.example.nosqldemo.repository.PlanetaRepository;

@Component
public class PlanetaManager {

	@Autowired
	private  PlanetaRepository planetaRepository;
	
	public void addPlaneta(Planeta planeta){
		planetaRepository.save(planeta);
	}
	
	public void deletePlaneta(Planeta planeta){
		planetaRepository.delete(planeta);
	}
	
	public void deleteAllPlanety(){
		planetaRepository.deleteAll();
	}
	
	public void updatePlaneta(Planeta planeta){
		planetaRepository.save(planeta);
	}
	
	public List<Planeta> getAllPlanety(){
		return planetaRepository.findAll();
	}
	
	public List<Planeta> getPlanetyBySrednica(int srednica){
		return planetaRepository.findBySrednica(srednica);
	}
	
	public List<Planeta> getPlanetyBySrednicaIl_ks(int srednica, int il_ks){
		return planetaRepository.znajdzPlaneta(srednica, il_ks);
	}
	
	public Planeta getPlanetaById(ObjectId id){
		return planetaRepository.findById(id);
	}
	
	
	
}
