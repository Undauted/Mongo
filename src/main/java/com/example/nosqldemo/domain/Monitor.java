package com.example.nosqldemo.domain;

import org.bson.types.ObjectId;

public class Monitor {
	
	
	private ObjectId id;		
	private String nazwa;
	private String rodzaj;
	private int przekatna;
	private int waga;
	
	
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getNazwa() {
		return nazwa;
	}
	
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public String getRodzaj() {
		return rodzaj;
	}
	
	public void setRodzaj(String rodzaj) {
		this.rodzaj = rodzaj;
	}
	
	public int getPrzekatna() {
		return przekatna;
	}
	
	public void setPrzekatna(int przekatna) {
		this.przekatna = przekatna;
	}
	
	public int getWaga() {
		return waga;
	}
	public void setWaga(int waga) {
		this.waga = waga;
	}
	
}