package com.example.nosqldemo.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.nosqldemo.domain.Car;
import com.example.nosqldemo.domain.Person;
import com.example.nosqldemo.service.PlanetaManager;
import com.example.nosqldemo.service.UkladPlanetarnyManager;
import com.example.nosqldemo.domain.Planeta;
import com.example.nosqldemo.domain.UkladPlanetarny;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class UkladPlanetarnyManagerTest {
	
	@Autowired
	PlanetaManager managerPlanety;
	@Autowired
	UkladPlanetarnyManager managerUkladu;
	
	//Planeta
	private final String nazwa1 = "Mars";
	private final String nazwa2 = "Jowisz";
	
	private final int srednica1 = 5433;
	private final int srednica2 = 1234;
	
	private final int il_ks1 = 2;
	private final int il_ks2 = 43;
	
	private int SrednicaUnique = 565656;
	
	//Uklad
	private final String nazwaU1 = "Uklad Sloneczny";
	private final String nazwaU2 = "Uklad Kepler";
	
	private final int liczbaObiektow1 = 5;
	private final int liczbaObiektow2 = 7; 
	private final int liczbaObiektowUnique = 10;
	
	private final String nazwaUkladuUnique = "Uklad Unikalny";
	private final int liczbaObiektowUnique2 = 666;
	
	@Test
	public void checkAdding(){

		Planeta planeta1 = new Planeta();
		planeta1.setNazwa(nazwa2);
		planeta1.setSrednica(srednica2);
		planeta1.setIl_ks(il_ks2);
		managerPlanety.addPlaneta(planeta1);
		List<Planeta> planetyTab = new ArrayList<Planeta>();
		planetyTab.add(planeta1);
		
		UkladPlanetarny uklad = new UkladPlanetarny();
		uklad.setNazwaUkladu(nazwaU1);
		uklad.setLiczbaObiektow(liczbaObiektow1);
		uklad.setPlanety(planetyTab);
		
		List<UkladPlanetarny> listaUkladow = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowprzedDodaniem = listaUkladow.size();
		//System.out.println(liczbaUkladowprzedDodaniem);
		
		managerUkladu.addNewUkladPlanetarny(uklad);
		
		List<UkladPlanetarny> listaUkladow2 = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowpoDodaniu = listaUkladow2.size();
		//System.out.println(liczbaUkladowpoDodaniu);
		
		ObjectId id = uklad.getId();
		UkladPlanetarny uk = managerUkladu.getUkladPlanetarnyById(id);
		
		assertEquals(uk.getNazwaUkladu(), uklad.getNazwaUkladu());
		assertEquals(uk.getLiczbaObiektow(), uklad.getLiczbaObiektow());
		assertEquals(uk.getPlanety().size(), uklad.getPlanety().size());
		
		assertEquals(uk.getPlanety().get(0).getNazwa(), uklad.getPlanety().get(0).getNazwa());
		assertEquals(uk.getPlanety().get(0).getSrednica(), uklad.getPlanety().get(0).getSrednica());
		assertEquals(uk.getPlanety().get(0).getIl_ks(), uklad.getPlanety().get(0).getIl_ks());
		
		assertEquals(liczbaUkladowprzedDodaniem+1, liczbaUkladowpoDodaniu);
		
		managerUkladu.deleteAllUkladyPlanetarne();
	}
	
	
	@Test
	public void deleteUkladCheck() {
		
		Planeta planeta1 = new Planeta();
		planeta1.setNazwa(nazwa1);
		planeta1.setSrednica(srednica1);
		planeta1.setIl_ks(il_ks1);
		managerPlanety.addPlaneta(planeta1);
		
		Planeta planeta2 = new Planeta();
		planeta2.setNazwa(nazwa1);
		planeta2.setSrednica(srednica1);
		planeta2.setIl_ks(il_ks1);
		managerPlanety.addPlaneta(planeta2);
		
		List<Planeta> planetyTab = new ArrayList<Planeta>();
		planetyTab.add(planeta1);
		planetyTab.add(planeta2);
		
		UkladPlanetarny uklad = new UkladPlanetarny();
		uklad.setNazwaUkladu(nazwaU1);
		uklad.setLiczbaObiektow(liczbaObiektow1);
		uklad.setPlanety(planetyTab);
		managerUkladu.addNewUkladPlanetarny(uklad);
		
		List<UkladPlanetarny> listaUkladow = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowprzedUsunieciem = listaUkladow.size();
		
		ObjectId idUkladu = uklad.getId();
		managerUkladu.deleteUkladPlanetarny(uklad);
		
		List<UkladPlanetarny> listaUkladow2 = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowpoUsunieciu = listaUkladow2.size();
		
		assertEquals(liczbaUkladowprzedUsunieciem, liczbaUkladowpoUsunieciu+1);
		assertEquals(null, managerUkladu.getUkladPlanetarnyById(idUkladu));
		
		assertEquals(null, managerPlanety.getPlanetaById(planeta1.getId()));
		assertEquals(null, managerPlanety.getPlanetaById(planeta2.getId()));
		
		managerUkladu.deleteAllUkladyPlanetarne();
	}
	
	
	@Test
	public void deleteAllUkladyCheck() {
		List<UkladPlanetarny> listaUkladow = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowPrzedDodaniem = listaUkladow.size();
		
		Planeta planeta1 = new Planeta();
		planeta1.setNazwa(nazwa2);
		planeta1.setSrednica(srednica2);
		planeta1.setIl_ks(il_ks2);
	
		Planeta planeta2 = new Planeta();
		planeta2.setNazwa(nazwa2);
		planeta2.setSrednica(srednica2);
		planeta2.setIl_ks(il_ks2);
	
		managerPlanety.addPlaneta(planeta1);
		managerPlanety.addPlaneta(planeta2);
		
		List<Planeta> planetyTab1 = new ArrayList<Planeta>();
		planetyTab1.add(planeta1);
		
		List<Planeta> planetyTab2 = new ArrayList<Planeta>();
		planetyTab1.add(planeta2);
		
		UkladPlanetarny uklad1 = new UkladPlanetarny();
		uklad1.setNazwaUkladu(nazwaU1);
		uklad1.setLiczbaObiektow(liczbaObiektow1);
		uklad1.setPlanety(planetyTab1);
		managerUkladu.addNewUkladPlanetarny(uklad1);
		
		UkladPlanetarny uklad2 = new UkladPlanetarny();
		uklad2.setNazwaUkladu(nazwaU1);
		uklad2.setLiczbaObiektow(liczbaObiektow1);
		uklad2.setPlanety(planetyTab2);
		managerUkladu.addNewUkladPlanetarny(uklad2);
	
		assertNotNull(listaUkladow);
		
		List<UkladPlanetarny> listaUkladow2 = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowPoDodaniu = listaUkladow2.size();
	
		assertEquals(liczbaUkladowPoDodaniu, liczbaUkladowPrzedDodaniem+2);
		
		managerUkladu.deleteAllUkladyPlanetarne();
		
		List<UkladPlanetarny> listaUkladow3 = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowpoUsunieciu = listaUkladow3.size();
		assertEquals(0, liczbaUkladowpoUsunieciu);
	}
	
	
	@Test
	public void findUkladCheck() {
		
		List<UkladPlanetarny> listaUkladow = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowprzedDodaniem = listaUkladow.size();
		
		Planeta planeta = new Planeta();
		planeta.setNazwa(nazwa1);
		planeta.setSrednica(srednica1);
		planeta.setIl_ks(il_ks1);
		managerPlanety.addPlaneta(planeta);
		
		List<Planeta> planetyTab = new ArrayList<Planeta>();
		planetyTab.add(planeta);
		
		UkladPlanetarny uklad = new UkladPlanetarny();
		uklad.setNazwaUkladu(nazwaU1);
		uklad.setLiczbaObiektow(liczbaObiektow1);
		uklad.setPlanety(planetyTab);
		managerUkladu.addNewUkladPlanetarny(uklad);
		
		assertNotNull(managerUkladu.getUkladPlanetarnyById(uklad.getId()));
		assertNotNull(managerPlanety.getPlanetaById(planeta.getId()));
		
		managerUkladu.deleteUkladPlanetarny(uklad);
		assertNull(managerPlanety.getPlanetaById(planeta.getId()));
		assertNull(managerUkladu.getUkladPlanetarnyById(uklad.getId()));
		
		List<UkladPlanetarny> listaUkladow2 = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowPoUsunieciu = listaUkladow2.size();
		
		assertEquals(liczbaUkladowprzedDodaniem, liczbaUkladowPoUsunieciu);
		
		managerUkladu.deleteAllUkladyPlanetarne();
	}
	
	
	@Test
	public void findAllUkladyCheck() {
	
		Planeta planeta1 = new Planeta();
		planeta1.setNazwa(nazwa2);
		planeta1.setSrednica(srednica2);
		planeta1.setIl_ks(il_ks2);
	
		Planeta planeta2 = new Planeta();
		planeta2.setNazwa(nazwa2);
		planeta2.setSrednica(srednica2);
		planeta2.setIl_ks(il_ks2);
	
		managerPlanety.addPlaneta(planeta1);
		managerPlanety.addPlaneta(planeta2);
	
		List<Planeta> planetyTab = new ArrayList<Planeta>();
		planetyTab.add(planeta1);
		planetyTab.add(planeta2);
	
		UkladPlanetarny uklad = new UkladPlanetarny();
		uklad.setNazwaUkladu(nazwaU2);
		uklad.setLiczbaObiektow(liczbaObiektow2);
		uklad.setPlanety(planetyTab);

		List<UkladPlanetarny> listaUkladow = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowprzedDodaniem = listaUkladow.size();
	
		managerUkladu.addNewUkladPlanetarny(uklad);
	
		List<UkladPlanetarny> listaUkladow2 = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowpoDodaniu = listaUkladow2.size();
		//assertEquals(liczbaPlanetprzedDodaniem, liczbaPlanetpoDodaniu-2);
	
		assertNotNull(listaUkladow);
	
		//int liczbaPlanet = listaPlanet.size();
	
		assertEquals(liczbaUkladowpoDodaniu, liczbaUkladowprzedDodaniem+1);
		
		managerUkladu.deleteAllUkladyPlanetarne();
	}
	
	@Test
	public void editUkladCheck() {
		
		List<UkladPlanetarny> listaUkladow1 = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowprzedDodaniem = listaUkladow1.size();
		
		Planeta planeta1 = new Planeta();
		planeta1.setNazwa(nazwa2);
		planeta1.setSrednica(srednica2);
		planeta1.setIl_ks(il_ks2);
		
		Planeta planeta2 = new Planeta();
		planeta2.setNazwa(nazwa2);
		planeta2.setSrednica(srednica2);
		planeta2.setIl_ks(il_ks2);
				
		managerPlanety.addPlaneta(planeta1);
		managerPlanety.addPlaneta(planeta2);
		
		List<Planeta> planetyTab1 = new ArrayList<Planeta>();
		planetyTab1.add(planeta1);
		
		UkladPlanetarny uklad1 = new UkladPlanetarny();
		uklad1.setNazwaUkladu(nazwaU1);
		uklad1.setLiczbaObiektow(liczbaObiektow1);
		uklad1.setPlanety(planetyTab1);
		managerUkladu.addNewUkladPlanetarny(uklad1);
		
		List<Planeta> planetyTab2 = new ArrayList<Planeta>();
		planetyTab2.add(planeta2);
		
		UkladPlanetarny uklad2 = new UkladPlanetarny();
		uklad2.setNazwaUkladu(nazwaU2);
		uklad2.setLiczbaObiektow(liczbaObiektow2);
		uklad2.setPlanety(planetyTab2);
		managerUkladu.addNewUkladPlanetarny(uklad2);

		
		List<UkladPlanetarny> listaUkladow2 = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowpoDodaniu = listaUkladow2.size();
		assertEquals(liczbaUkladowprzedDodaniem+2, liczbaUkladowpoDodaniu);
		
		
		uklad2.setNazwaUkladu(nazwa1);
		uklad2.setLiczbaObiektow(liczbaObiektow1);
		
		managerUkladu.updateUkladPlanetarny(uklad2);
		
		ObjectId idUkladu = uklad2.getId();
		
		UkladPlanetarny uk = managerUkladu.getUkladPlanetarnyById(idUkladu);
		
		assertEquals(nazwa1, uk.getNazwaUkladu());
		assertEquals(liczbaObiektow1, uk.getLiczbaObiektow());
		
		managerUkladu.deleteAllUkladyPlanetarne();
		
	}
	
	
	@Test
	public void findUkladByLiczbaObiektow() {
		
		List<UkladPlanetarny> listaUkladow1 = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowprzedDodaniem = listaUkladow1.size();
		
		Planeta planeta = new Planeta();
		planeta.setNazwa(nazwa1);
		planeta.setSrednica(SrednicaUnique);
		planeta.setIl_ks(il_ks1);		
		managerPlanety.addPlaneta(planeta);
		
		List<Planeta> planetyTab1 = new ArrayList<Planeta>();
		planetyTab1.add(planeta);
		
		UkladPlanetarny uklad1 = new UkladPlanetarny();
		uklad1.setNazwaUkladu(nazwaU1);
		uklad1.setLiczbaObiektow(liczbaObiektowUnique);
		uklad1.setPlanety(planetyTab1);
		managerUkladu.addNewUkladPlanetarny(uklad1);
		
		
		assertNotNull(managerPlanety.getPlanetaById(planeta.getId()));
		assertNotNull(managerUkladu.getUkladPlanetarnyById(uklad1.getId()));
		
		List<UkladPlanetarny> listaUkladow2 = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowpoDodaniu = listaUkladow2.size();
		
		assertEquals(liczbaUkladowpoDodaniu, liczbaUkladowprzedDodaniem+1);
		
		assertNotNull(managerUkladu.getUkladyPlanetarneByLiczbaObiektow(uklad1.getLiczbaObiektow()));
		List<UkladPlanetarny> uk = managerUkladu.getUkladyPlanetarneByLiczbaObiektow(uklad1.getLiczbaObiektow());
		UkladPlanetarny uk2 = uk.get(0);
		assertEquals(liczbaObiektowUnique, uk2.getLiczbaObiektow());
		
		managerUkladu.deleteAllUkladyPlanetarne();
	}
	
	@Test
	public void checkPobranieWszystkichObiektowZUkladu() {
		Planeta planeta1 = new Planeta();
		planeta1.setNazwa(nazwa1);
		planeta1.setSrednica(srednica1);
		planeta1.setIl_ks(il_ks1);
		
		Planeta planeta2 = new Planeta();
		planeta2.setNazwa(nazwa2);
		planeta2.setSrednica(srednica2);
		planeta2.setIl_ks(il_ks2);
				
		managerPlanety.addPlaneta(planeta1);
		managerPlanety.addPlaneta(planeta2);
		
		List<Planeta> planetyTab1 = new ArrayList<Planeta>();
		planetyTab1.add(planeta1);
		planetyTab1.add(planeta2);
		
		UkladPlanetarny uklad1 = new UkladPlanetarny();
		uklad1.setNazwaUkladu(nazwaU1);
		uklad1.setLiczbaObiektow(liczbaObiektow1);
		uklad1.setPlanety(planetyTab1);
		managerUkladu.addNewUkladPlanetarny(uklad1);
		
		List<Planeta> listaPlanetUkladu = managerUkladu.getPlanetyInUklad(uklad1);
		assertNotNull(listaPlanetUkladu);
		
		assertEquals(listaPlanetUkladu.size(), 2);
		
		Planeta pl1 = listaPlanetUkladu.get(0);
		Planeta pl2 = listaPlanetUkladu.get(1);
		
		assertEquals(pl1.getNazwa(), nazwa1);
		assertEquals(pl1.getSrednica(), srednica1);
		assertEquals(pl1.getIl_ks(), il_ks1);
		
		assertEquals(pl2.getNazwa(), nazwa2);
		assertEquals(pl2.getSrednica(), srednica2);
		assertEquals(pl2.getIl_ks(), il_ks2);
		
		managerUkladu.deleteAllUkladyPlanetarne();
	}

	
	@Test
	public void getUkladPlanetarnyByNazwaLiczbaObiektowCheck() {
		List<UkladPlanetarny> listaUkladow = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowPrzedDodaniem = listaUkladow.size();
		
		Planeta planeta1 = new Planeta();
		planeta1.setNazwa(nazwa1);
		planeta1.setSrednica(srednica1);
		planeta1.setIl_ks(il_ks1);
	
		Planeta planeta2 = new Planeta();
		planeta2.setNazwa(nazwa2);
		planeta2.setSrednica(srednica2);
		planeta2.setIl_ks(il_ks2);
	
		managerPlanety.addPlaneta(planeta1);
		managerPlanety.addPlaneta(planeta2);
		
		List<Planeta> planetyTab1 = new ArrayList<Planeta>();
		planetyTab1.add(planeta1);
		
		List<Planeta> planetyTab2 = new ArrayList<Planeta>();
		planetyTab1.add(planeta2);
		
		UkladPlanetarny uklad1 = new UkladPlanetarny();
		uklad1.setNazwaUkladu(nazwaU1);
		uklad1.setLiczbaObiektow(liczbaObiektow1);
		uklad1.setPlanety(planetyTab1);
		managerUkladu.addNewUkladPlanetarny(uklad1);
		
		UkladPlanetarny uklad2 = new UkladPlanetarny();
		uklad2.setNazwaUkladu(nazwaUkladuUnique);
		uklad2.setLiczbaObiektow(liczbaObiektowUnique);
		uklad2.setPlanety(planetyTab2);
		managerUkladu.addNewUkladPlanetarny(uklad2);
	
		List<UkladPlanetarny> listaUkladow2 = managerUkladu.getAllUkladyPlanetarne();
		int liczbaUkladowPoDodaniu = listaUkladow2.size();
	
		assertEquals(liczbaUkladowPoDodaniu, liczbaUkladowPrzedDodaniem+2);
		
		assertNotNull(managerUkladu.getUkladPlanetarnyByNazwaLiczbaObiektow(nazwaUkladuUnique, liczbaObiektowUnique));
		List<UkladPlanetarny> pl1 = managerUkladu.getUkladPlanetarnyByNazwaLiczbaObiektow(nazwaUkladuUnique, liczbaObiektowUnique);
		int length1 = pl1.size();
		assertEquals(1, length1);
		
		UkladPlanetarny uklad3 = new UkladPlanetarny();
		uklad3.setNazwaUkladu(nazwaUkladuUnique);
		uklad3.setLiczbaObiektow(liczbaObiektowUnique);
		uklad3.setPlanety(planetyTab2);
		managerUkladu.addNewUkladPlanetarny(uklad3);
		
		assertNotNull(managerUkladu.getUkladPlanetarnyByNazwaLiczbaObiektow(nazwaUkladuUnique, liczbaObiektowUnique));
		List<UkladPlanetarny> pl2 = managerUkladu.getUkladPlanetarnyByNazwaLiczbaObiektow(nazwaUkladuUnique, liczbaObiektowUnique);
		int length2 = pl2.size();
		assertEquals(2, length2);
		
		List<UkladPlanetarny> pl3 = managerUkladu.getUkladPlanetarnyByNazwaLiczbaObiektow(nazwaU1, liczbaObiektowUnique);
		int length3 = pl3.size();
		assertEquals(0, length3);
		
		managerUkladu.deleteAllUkladyPlanetarne();
	}
	
}
