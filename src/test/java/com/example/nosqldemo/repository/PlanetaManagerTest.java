//package com.example.nosqldemo.repository;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//
//import java.util.List;
//
//import org.bson.types.ObjectId;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.example.nosqldemo.service.MonitorManager;
//import com.example.nosqldemo.domain.Monitor;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:/beans.xml" })
//public class PlanetaManagerTest {
//	
//	@Autowired
//	MonitorManager managerPlanety;
//	
//	private final String nazwa1 = "Mars";
//	private final String nazwa2 = "Jowisz";
//	
//	private final int srednica1 = 5433;
//	private final int srednica2 = 1234;
//	
//	private final int il_ks1 = 2;
//	private final int il_ks2 = 43;
//	
//	private int SrednicaUnique = 565656;
//	
//	private final int srednicaUnique1 = 4345;
//	private final int srednicaUnique2 = 9854;
//	
//	private final int il_ksUnique1 = 54;
//	private final int il_ksUnique2 = 23;
//	
//	
//	@Test
//	public void checkAdding(){
//		Monitor planeta = new Monitor();
//		planeta.setNazwa(nazwa1);
//		planeta.setSrednica(srednica1);
//		planeta.setIl_ks(il_ks1);
//		
//		List<Monitor> listaPlanet = managerPlanety.getAllPlanety();
//		int liczbaPlanetprzedDodaniem = listaPlanet.size();
//		//System.out.println(liczbaPlanetprzedDodaniem);
//		
//		managerPlanety.addPlaneta(planeta);
//		
//		List<Monitor> listaPlanet2 = managerPlanety.getAllPlanety();
//		int liczbaPlanetpoDodaniu = listaPlanet2.size();
//		
//		assertEquals(liczbaPlanetprzedDodaniem+1, liczbaPlanetpoDodaniu);
//		managerPlanety.deleteAllPlanety();
//	}
//	
//	
//	@Test
//	public void deletePlanetaCheck() {
//		
//		Monitor planeta = new Monitor();
//		planeta.setNazwa(nazwa1);
//		planeta.setSrednica(srednica1);
//		planeta.setIl_ks(il_ks1);
//		managerPlanety.addPlaneta(planeta);
//		List<Monitor> listaPlanet = managerPlanety.getAllPlanety();
//		int liczbaPlanetprzedUsunieciem = listaPlanet.size();
//		
//		managerPlanety.deletePlaneta(planeta);
//		
//		List<Monitor> listaPlanet2 = managerPlanety.getAllPlanety();
//		int liczbaPlanetpoUsunieciu = listaPlanet2.size();
//		
//		assertEquals(liczbaPlanetprzedUsunieciem, liczbaPlanetpoUsunieciu+1);
//		
//		
//		assertEquals(null, managerPlanety.getPlanetaById(planeta.getId()));
//		managerPlanety.deleteAllPlanety();
//	}
//	
//	
//	@Test
//	public void deleteAllPlanetyCheck() {
//		List<Monitor> listaPlanet = managerPlanety.getAllPlanety();
//		int liczbaPlanetprzedDodaniem = listaPlanet.size();
//		
//		Monitor planeta1 = new Monitor();
//		planeta1.setNazwa(nazwa2);
//		planeta1.setSrednica(srednica2);
//		planeta1.setIl_ks(il_ks2);
//	
//		Monitor planeta2 = new Monitor();
//		planeta2.setNazwa(nazwa2);
//		planeta2.setSrednica(srednica2);
//		planeta2.setIl_ks(il_ks2);
//	
//		managerPlanety.addPlaneta(planeta1);
//		managerPlanety.addPlaneta(planeta2);
//	
//		assertNotNull(listaPlanet);
//		
//		List<Monitor> listaPlanet2 = managerPlanety.getAllPlanety();
//		int liczbaPlanetpoDodaniu = listaPlanet2.size();
//	
//		assertEquals(liczbaPlanetpoDodaniu, liczbaPlanetprzedDodaniem+2);
//		
//		managerPlanety.deleteAllPlanety();
//		
//		List<Monitor> listaPlanet3 = managerPlanety.getAllPlanety();
//		int liczbaPlanetpoUsunieciu = listaPlanet3.size();
//		assertEquals(0, liczbaPlanetpoUsunieciu);
//	}
//	
//	
//	@Test
//	public void findPlanetaByIdCheck() {
//	
//		List<Monitor> listaPlanet = managerPlanety.getAllPlanety();
//		int liczbaPlanetprzedDodaniem = listaPlanet.size();
//		
//		Monitor planeta = new Monitor();
//		planeta.setNazwa(nazwa1);
//		planeta.setSrednica(srednica1);
//		planeta.setIl_ks(il_ks1);
//		
//		managerPlanety.addPlaneta(planeta);
//		assertNotNull(managerPlanety.getPlanetaById(planeta.getId()));
//		
//		managerPlanety.deletePlaneta(planeta);
//		assertNull(managerPlanety.getPlanetaById(planeta.getId()));
//		
//		List<Monitor> listaPlanet2 = managerPlanety.getAllPlanety();
//		int liczbaPlanetpoDodaniu = listaPlanet2.size();
//		
//		assertEquals(liczbaPlanetprzedDodaniem, liczbaPlanetpoDodaniu);
//		managerPlanety.deleteAllPlanety();
//	}
//	
//	@Test
//	public void findAllPlanetyCheck() {
//	
//		Monitor planeta1 = new Monitor();
//		planeta1.setNazwa(nazwa2);
//		planeta1.setSrednica(srednica2);
//		planeta1.setIl_ks(il_ks2);
//	
//		List<Monitor> listaPlanet = managerPlanety.getAllPlanety();
//		int liczbaPlanetprzedDodaniem = listaPlanet.size();
//	
//		Monitor planeta2 = new Monitor();
//		planeta2.setNazwa(nazwa2);
//		planeta2.setSrednica(srednica2);
//		planeta2.setIl_ks(il_ks2);
//	
//		managerPlanety.addPlaneta(planeta1);
//		managerPlanety.addPlaneta(planeta2);
//	
//		List<Monitor> listaPlanet2 = managerPlanety.getAllPlanety();
//		int liczbaPlanetpoDodaniu = listaPlanet2.size();
//		//assertEquals(liczbaPlanetprzedDodaniem, liczbaPlanetpoDodaniu-2);
//	
//		assertNotNull(listaPlanet);
//	
//		//int liczbaPlanet = listaPlanet.size();
//	
//		assertEquals(liczbaPlanetpoDodaniu, liczbaPlanetprzedDodaniem+2);
//		managerPlanety.deleteAllPlanety();
//	}
//
//	
//	@Test
//	public void editPlanetaCheck() {
//		
//		List<Monitor> listaPlanet = managerPlanety.getAllPlanety();
//		int liczbaPlanetprzedDodaniem = listaPlanet.size();
//		
//		Monitor planeta1 = new Monitor();
//		planeta1.setNazwa(nazwa2);
//		planeta1.setSrednica(srednica2);
//		planeta1.setIl_ks(il_ks2);
//		
//		Monitor planeta2 = new Monitor();
//		planeta2.setNazwa(nazwa2);
//		planeta2.setSrednica(srednica2);
//		planeta2.setIl_ks(il_ks2);
//		
//		Monitor planeta3 = new Monitor();
//		planeta3.setNazwa(nazwa2);
//		planeta3.setSrednica(srednica2);
//		planeta3.setIl_ks(il_ks2);
//		
//		managerPlanety.addPlaneta(planeta1);
//		managerPlanety.addPlaneta(planeta2);
//		managerPlanety.addPlaneta(planeta3);
//		
//		List<Monitor> listaPlanet2 = managerPlanety.getAllPlanety();
//		int liczbaPlanetpoDodaniu = listaPlanet2.size();
//		assertEquals(liczbaPlanetprzedDodaniem+3, liczbaPlanetpoDodaniu);
//		
//		
//		planeta2.setNazwa(nazwa2);
//		planeta2.setSrednica(srednica1);
//		planeta2.setIl_ks(il_ks1);
//		managerPlanety.updatePlaneta(planeta2);
//		
//		ObjectId idPlanety = planeta2.getId();
//		//Long indeks = (long)0;
//		
//		Monitor pl = managerPlanety.getPlanetaById(idPlanety);
//		
//		assertEquals(nazwa2, pl.getNazwa());
//		assertEquals(srednica1, pl.getSrednica());
//		assertEquals(il_ks1, pl.getIl_ks());
//		
//		assertEquals(nazwa2, planeta1.getNazwa());
//		assertEquals(srednica2, planeta1.getSrednica());
//		assertEquals(il_ks2, planeta1.getIl_ks());
//		
//		assertEquals(nazwa2, planeta3.getNazwa());
//		assertEquals(srednica2, planeta3.getSrednica());
//		assertEquals(il_ks2, planeta3.getIl_ks());
//		
//		managerPlanety.deleteAllPlanety();
//	}
//	
//	
//	@Test
//	public void findPlanetaBySrednicaCheck() {
//		
//		List<Monitor> listaPlanet = managerPlanety.getAllPlanety();
//		int liczbaPlanetprzedDodaniem = listaPlanet.size();
//		
//		Monitor planeta = new Monitor();
//		planeta.setNazwa(nazwa1);
//		planeta.setSrednica(SrednicaUnique);
//		planeta.setIl_ks(il_ks1);
//		
//		managerPlanety.addPlaneta(planeta);
//		assertNotNull(managerPlanety.getPlanetaById(planeta.getId()));
//		
//		List<Monitor> listaPlanet2 = managerPlanety.getAllPlanety();
//		int liczbaPlanetpoDodaniu = listaPlanet2.size();
//		
//		assertEquals(liczbaPlanetpoDodaniu, liczbaPlanetprzedDodaniem+1);
//		
//		assertNotNull(managerPlanety.getPlanetyBySrednica(planeta.getSrednica()));
//		List<Monitor> pl = managerPlanety.getPlanetyBySrednica(planeta.getSrednica());
//		Monitor pl2 = pl.get(0);
//		assertEquals(SrednicaUnique, pl2.getSrednica());
//		
//		managerPlanety.deleteAllPlanety();
//	}
//	
//	
//	@Test
//	public void findPlanetaBySrednicaIl_ksCheck() {
//		List<Monitor> listaPlanet = managerPlanety.getAllPlanety();
//		int liczbaPlanetprzedDodaniem = listaPlanet.size();
//		
//		Monitor planeta1 = new Monitor();
//		planeta1.setNazwa(nazwa1);
//		planeta1.setSrednica(srednicaUnique1);
//		planeta1.setIl_ks(il_ksUnique1);
//	
//		Monitor planeta2 = new Monitor();
//		planeta2.setNazwa(nazwa2);
//		planeta2.setSrednica(srednicaUnique2);
//		planeta2.setIl_ks(il_ksUnique2);
//	
//		managerPlanety.addPlaneta(planeta1);
//		managerPlanety.addPlaneta(planeta2);
//	
//		List<Monitor> listaPlanet2 = managerPlanety.getAllPlanety();
//		int liczbaPlanetpoDodaniu = listaPlanet2.size();
//	
//		assertEquals(liczbaPlanetpoDodaniu, liczbaPlanetprzedDodaniem+2);
//		
//		assertNotNull(managerPlanety.getPlanetyBySrednicaIl_ks(srednicaUnique1, il_ksUnique1));
//		List<Monitor> pl1 = managerPlanety.getPlanetyBySrednicaIl_ks(srednicaUnique1, il_ksUnique1);
//		int length1 = pl1.size();
//		assertEquals(1, length1);
//		
//		assertNotNull(managerPlanety.getPlanetyBySrednicaIl_ks(srednicaUnique2, il_ksUnique2));
//		List<Monitor> pl2 = managerPlanety.getPlanetyBySrednicaIl_ks(srednicaUnique2, il_ksUnique2);
//		int length2 = pl2.size();
//		assertEquals(1, length2);
//		
//		List<Monitor> pl3 = managerPlanety.getPlanetyBySrednicaIl_ks(srednica2, il_ks1);
//		int length3 = pl3.size();
//		assertEquals(0, length3);
//		
//		managerPlanety.deleteAllPlanety();
//	}
//	
//}
