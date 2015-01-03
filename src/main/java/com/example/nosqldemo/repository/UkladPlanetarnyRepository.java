package com.example.nosqldemo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.nosqldemo.domain.UkladPlanetarny;


public interface UkladPlanetarnyRepository extends CrudRepository<UkladPlanetarny, ObjectId> {
	
	<S extends UkladPlanetarny> S save(S entity);
	
	UkladPlanetarny findById(ObjectId id);
	
	List<UkladPlanetarny> findByLiczbaObiektow(int liczbaObiektow);
	
	List<UkladPlanetarny> findAll();
	
	@Query(value = "{ 'nazwaUkladu' : ?0, 'liczbaObiektow' : ?1 }" )
	List<UkladPlanetarny> znajdzUkladPlanetarny(String nazwaUkladu, int liczbaObiektow);
	
}