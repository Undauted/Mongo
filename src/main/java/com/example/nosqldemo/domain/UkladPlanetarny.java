package com.example.nosqldemo.domain;

import java.util.List;
import org.bson.types.ObjectId;

public class UkladPlanetarny {
	
	private ObjectId id;	
	private String nazwaUkladu;
    private Integer liczbaObiektow;
    private List<Planeta> planety;

	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getNazwaUkladu() {
		return nazwaUkladu;
	}
	public void setNazwaUkladu(String nazwaUkladu) {
		this.nazwaUkladu = nazwaUkladu;
	}
	
	public int getLiczbaObiektow() {
		return liczbaObiektow;
	}
	public void setLiczbaObiektow(int liczbaObiektow) {
		this.liczbaObiektow = liczbaObiektow;
	}
	
	public List<Planeta> getPlanety() {
		return planety;
	}
	public void setPlanety(List<Planeta> planety) {
		this.planety = planety;
	}

}










