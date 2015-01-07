package com.example.nosqldemo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.nosqldemo.domain.Monitor;

public interface MonitorRepository extends CrudRepository<Monitor, ObjectId>{
	
	<S extends Monitor> S save(S entity);
	
	Monitor findById(ObjectId id);
	
	List<Monitor> findByWaga(int waga);
	
	List<Monitor> findAll();
	
	@Query(value = "{ 'przekatna' : ?0, 'waga' : ?1 }" )
	List<Monitor> znajdzMonitor(int przekatna, int waga);
	

}

