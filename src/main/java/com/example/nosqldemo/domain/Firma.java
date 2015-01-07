package com.example.nosqldemo.domain;

import java.util.List;
import org.bson.types.ObjectId;

public class Firma {
	
	private ObjectId id;	
	private String marka;
    private int regon;
    private String szef;
    
    private List<Monitor> monitory;

	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getMarka() {
		return marka;
	}
	public void setMarka(String marka) {
		this.marka = marka;
	}
	
	public int getRegon() {
		return regon;
	}
	public void setRegon(int regon) {
		this.regon = regon;
	}
	
	public String getSzef() {
		return szef;
	}
	public void setSzef(String szef) {
		this.szef = szef;
	}
	
	public List<Monitor> getMonitory() {
		return monitory;
	}
	public void setMonitory(List<Monitor> monitory) {
		this.monitory = monitory;
	}

}










