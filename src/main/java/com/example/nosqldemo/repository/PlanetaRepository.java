package com.example.nosqldemo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.nosqldemo.domain.Planeta;

public interface PlanetaRepository extends CrudRepository<Planeta, ObjectId>{
	
	<S extends Planeta> S save(S entity);
	
	Planeta findById(ObjectId id);
	
	List<Planeta> findBySrednica(int srednica);
	
	List<Planeta> findAll();
	
	@Query(value = "{ 'srednica' : ?0, 'il_ks' : ?1 }" )
	List<Planeta> znajdzPlaneta(int srednica, int il_ks);
	

}

