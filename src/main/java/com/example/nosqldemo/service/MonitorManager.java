package com.example.nosqldemo.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nosqldemo.domain.Monitor;
import com.example.nosqldemo.repository.MonitorRepository;

@Component
public class MonitorManager {

	@Autowired
	private  MonitorRepository monitorRepository;
	
	public void addMonitor(Monitor monitor){
		monitorRepository.save(monitor);
	}
	
	public void deleteMonitor(Monitor monitor){
		monitorRepository.delete(monitor);
	}
	
	public void deleteAllMonitory(){
		monitorRepository.deleteAll();
	}
	
	public void updateMonitor(Monitor monitor){
		monitorRepository.save(monitor);
	}
	
	public List<Monitor> getAllMonitory(){
		return monitorRepository.findAll();
	}
	
	public List<Monitor> getMonitorByWaga(int waga){
		return monitorRepository.findByWaga(waga);
	}
	
	public List<Monitor> getMonitorByPrzekatnaWaga(int przekatna, int waga){
		return monitorRepository.znajdzMonitor(przekatna, waga);
	}
	
	public Monitor getMonitorById(ObjectId id){
		return monitorRepository.findById(id);
	}
	
	
	
}
