package com.example.nosqldemo.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nosqldemo.domain.Monitor;
import com.example.nosqldemo.domain.Firma;
import com.example.nosqldemo.repository.MonitorRepository;
import com.example.nosqldemo.repository.FirmaRepository;

@Component
public class FirmaManager {

	@Autowired
	private  FirmaRepository firmaRepository;
	
	@Autowired
	private  MonitorRepository monitorRepository;
	
	public void addFirma(Firma firma){
		firmaRepository.save(firma);
	}
	
	public void deleteFirma(Firma firma){
		for (Monitor monitor : firma.getMonitory()) {
			monitorRepository.delete(monitor);
		}
		firmaRepository.delete(firma);
	}
	
	public void deleteAllFirma(){
		firmaRepository.deleteAll();
	}
	
	public void updateFirma(Firma firma){
		firmaRepository.save(firma);
	}
	
	public List<Firma> getAllFirma(){
		return firmaRepository.findAll();
	}
	
	public List<Firma> getFirmaByRegon(int regon){
		return firmaRepository.findByRegon(regon);
	}
	
	public List<Firma> getFirmaByMarkaRegon(String marka, int regon){
		return firmaRepository.znajdzFirme(marka, regon);
	}
	
	public Firma getFirmaById(ObjectId id){
		return firmaRepository.findById(id);
	}
	
	public List<Monitor> getMonitorFirmy(Firma firma){
		List<Monitor> monitor = new ArrayList<Monitor>(firma.getMonitory());
		return monitor;
	}
	
	public void deleteMonitorZfirmyBywaga(Firma firma, int temp){
		for (Monitor monitor : firma.getMonitory()) 
		{
			if(monitor.getWaga() == temp)
			{
				monitorRepository.delete(monitor);
			}
		}
		}
	
	
}
