package com.example.nosqldemo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.nosqldemo.domain.Firma;


public interface FirmaRepository extends CrudRepository<Firma, ObjectId> {
	
	<S extends Firma> S save(S entity);
	
	Firma findById(ObjectId id);
	
	List<Firma> findByRegon(int regon);
	
	List<Firma> findAll();
	
	@Query(value = "{ 'marka' : ?0, 'regon' : ?1 }" )
	List<Firma> znajdzFirme(String marka, int regon);
	
}