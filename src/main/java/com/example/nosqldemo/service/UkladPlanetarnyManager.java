package com.example.nosqldemo.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nosqldemo.domain.Planeta;
import com.example.nosqldemo.domain.UkladPlanetarny;
import com.example.nosqldemo.repository.PlanetaRepository;
import com.example.nosqldemo.repository.UkladPlanetarnyRepository;

@Component
public class UkladPlanetarnyManager {

	@Autowired
	private  UkladPlanetarnyRepository ukladplanetarnyRepository;
	
	@Autowired
	private  PlanetaRepository planetaRepository;
	
	public void addNewUkladPlanetarny(UkladPlanetarny uklad){
		ukladplanetarnyRepository.save(uklad);
	}
	
	public void deleteUkladPlanetarny(UkladPlanetarny uklad){
		for (Planeta planeta : uklad.getPlanety()) {
			planetaRepository.delete(planeta);
		}
		ukladplanetarnyRepository.delete(uklad);
	}
	
	public void deleteAllUkladyPlanetarne(){
		ukladplanetarnyRepository.deleteAll();
	}
	
	public void updateUkladPlanetarny(UkladPlanetarny uklad){
		ukladplanetarnyRepository.save(uklad);
	}
	
	public List<UkladPlanetarny> getAllUkladyPlanetarne(){
		return ukladplanetarnyRepository.findAll();
	}
	
	public List<UkladPlanetarny> getUkladyPlanetarneByLiczbaObiektow(int liczbaObiektow){
		return ukladplanetarnyRepository.findByLiczbaObiektow(liczbaObiektow);
	}
	
	public List<UkladPlanetarny> getUkladPlanetarnyByNazwaLiczbaObiektow(String nazwaUkladu, int liczbaObiektow){
		return ukladplanetarnyRepository.znajdzUkladPlanetarny(nazwaUkladu, liczbaObiektow);
	}
	
	public UkladPlanetarny getUkladPlanetarnyById(ObjectId id){
		return ukladplanetarnyRepository.findById(id);
	}
	
	public List<Planeta> getPlanetyInUklad(UkladPlanetarny uklad){
		List<Planeta> planety = new ArrayList<Planeta>(uklad.getPlanety());
		return planety;
	}
	
	
}
