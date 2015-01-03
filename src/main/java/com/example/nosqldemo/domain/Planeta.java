package com.example.nosqldemo.domain;

import org.bson.types.ObjectId;

public class Planeta {
	
	
	private ObjectId id;		
	private String nazwa;
	private int srednica;
	private int il_ks;
	
	
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
	
	public int getSrednica() {
		return srednica;
	}
	
	public void setSrednica(int srednica) {
		this.srednica = srednica;
	}
	
	public int getIl_ks() {
		return il_ks;
	}
	public void setIl_ks(int il_ks) {
		this.il_ks = il_ks;
	}
	
}