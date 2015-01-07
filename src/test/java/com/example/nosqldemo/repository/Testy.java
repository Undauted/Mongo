package com.example.nosqldemo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.nosqldemo.service.FirmaManager;
import com.example.nosqldemo.service.MonitorManager;
import com.example.nosqldemo.domain.Firma;
import com.example.nosqldemo.domain.Monitor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class Testy {
	
	@Autowired
	MonitorManager managerMonitor;
	@Autowired
	FirmaManager managerFirmy;
	
	
	private final String nazwa1 = "DELL";
	private final String nazwa2 = "LG";
	
	private final String rodzaj1 = "ABCDEFG";
	private final String rodzaj2 = "GFDSA";
	
	private final int przekatna1 = 15;
	private final int przekatna2 = 17;
	
	private final int waga1 = 2;
	private final int waga2 = 4;
	
	private final String marka1 = "DELL";
	private final String marka2 = "LG";
	
	private final int regon1 = 12345;
	private final int regon2 = 54321;
	
	private final String szef1 = "KOWALSKI";
	private final String szef2 = "NOWAK";
	
	
//-----------------DODAWANIE MONITORA I FIRMY-----------------------------------
	
	@Test
	public void checkAddingMonitor(){
		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
		
		List<Monitor> monit = managerMonitor.getAllMonitory();
		assertNull(monit.size());
		
		
		managerMonitor.addMonitor(monitor);
		
		List<Monitor> monit1 = managerMonitor.getAllMonitory();
		Monitor mo = monit1.get(0);
		assertNotNull(monit1.size());
		
		assertEquals(monit.size()+1, monit1.size());
		
		assertEquals(nazwa1,mo.getNazwa());
		assertEquals(rodzaj1,mo.getRodzaj());
		assertEquals(przekatna1,mo.getPrzekatna());
		assertEquals(waga1,mo.getWaga());
		
	}
	
	@Test
	public void checkAddingFirma(){

		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
		
		managerMonitor.addMonitor(monitor);
		List<Monitor> monit = new ArrayList<Monitor>();
		monit.add(monitor);
		
		Firma firma = new Firma();
		firma.setMarka(marka1);
		firma.setRegon(regon1);
		firma.setSzef(szef1);
		firma.setMonitory(monit);

		managerFirmy.addFirma(firma);
		
		List<Monitor> monit1 = managerMonitor.getAllMonitory();
		Monitor mo = monit1.get(0);
		
		assertEquals(nazwa1,mo.getNazwa());
		assertEquals(rodzaj1,mo.getRodzaj());
		assertEquals(waga1,mo.getWaga());
		
		List<Firma> fir = managerFirmy.getAllFirma();
		Firma fir1= fir.get(0);
		
		assertEquals(marka1,fir1.getMarka());
		assertEquals(regon1,fir1.getRegon());
		assertEquals(szef1,fir1.getSzef());
		assertEquals(monit.size(),fir1.getMonitory().size());
		
		
		
	}
	
//----------------------USUWANIE MONITORA I FIRMY-------------------------	
	@Test
	public void deleteMonitorCheck() {
		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
		
		managerMonitor.addMonitor(monitor);
		List<Monitor> monit = managerMonitor.getAllMonitory();
		assertNotNull(monit.size());
		
		managerMonitor.deleteMonitor(monitor);
		
		List<Monitor> monit1 = managerMonitor.getAllMonitory();
		assertNull(monit1.size());
		
		assertEquals(monit.size(), monit1.size()+1);
		
		
		assertNull(managerMonitor.getMonitorById(monitor.getId()));
	
	}
	
	@Test
	public void checkdeleteFirma() {
		
		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
		
		managerMonitor.addMonitor(monitor);
		
		List<Monitor> monit = new ArrayList<Monitor>();
		monit.add(monitor);
		
		
		Firma firma = new Firma();
		firma.setMarka(marka1);
		firma.setRegon(regon1);
		firma.setSzef(szef1);
		firma.setMonitory(monit);

		managerFirmy.addFirma(firma);
		
		List<Firma> fir = managerFirmy.getAllFirma();
		assertEquals(fir.size(),1);
		assertNotNull(managerMonitor.getMonitorById(monitor.getId()));
		
		managerFirmy.deleteFirma(firma);
		
		List<Firma> fir1 = managerFirmy.getAllFirma();
		assertEquals(fir1.size(),0);
		assertNull(managerFirmy.getFirmaById(firma.getId()));
		
		assertNull(managerMonitor.getMonitorById(monitor.getId()));
		
		
	}
	
	
//-------------------USUWANIE WSZYSTKICJ MONITORÓW I FIRM-------------------------------	
	@Test
	public void deleteAllMonitorCheck() {
		
		List<Monitor> monit = managerMonitor.getAllMonitory();
		
		
		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
	
		Monitor monitor1 = new Monitor();
		monitor1.setNazwa(nazwa2);
		monitor1.setRodzaj(rodzaj2);
		monitor1.setPrzekatna(przekatna2);
		monitor1.setWaga(waga2);
	
		managerMonitor.addMonitor(monitor);
		managerMonitor.addMonitor(monitor1);
	
		assertEquals(monit.size(),2);
		
		
		managerMonitor.deleteAllMonitory();
		
		List<Monitor> monit1 = managerMonitor.getAllMonitory();
		assertNull(monit1.size());
	}
	
	@Test
	public void checkdeleteAllFirma() {
	
		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
		
		managerMonitor.addMonitor(monitor);
		
		List<Monitor> monit = new ArrayList<Monitor>();
		monit.add(monitor);
		
		Monitor monitor1 = new Monitor();
		monitor1.setNazwa(nazwa2);
		monitor1.setRodzaj(rodzaj2);
		monitor1.setPrzekatna(przekatna2);
		monitor1.setWaga(waga2);
	
		managerMonitor.addMonitor(monitor1);
		
		List<Monitor> monit1 = new ArrayList<Monitor>();
		monit1.add(monitor1);
		
		Firma firma = new Firma();
		firma.setMarka(marka1);
		firma.setRegon(regon1);
		firma.setSzef(szef1);
		firma.setMonitory(monit);

		managerFirmy.addFirma(firma);
		
		Firma firma1 = new Firma();
		firma1.setMarka(marka2);
		firma1.setRegon(regon2);
		firma1.setSzef(szef2);
		firma1.setMonitory(monit1);

		managerFirmy.addFirma(firma1);
	
		
		List<Firma> fir = managerFirmy.getAllFirma();
		assertEquals(fir.size(),2);
		
		managerFirmy.deleteAllFirma();
		
		List<Firma> fir1 = managerFirmy.getAllFirma();
		assertEquals(fir1.size(),0);
		assertNull(fir1.size());
	}
	
//------------------SZUKANIE MONITORÓW I FIRM PO ID---------------------------
	
	@Test
	public void checkfindMonitorById() {
	
		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
		
		managerMonitor.addMonitor(monitor);
		assertNotNull(managerMonitor.getMonitorById(monitor.getId()));
		
		managerMonitor.deleteMonitor(monitor);
		assertNull(managerMonitor.getMonitorById(monitor.getId()));
		
		List<Monitor> monit = managerMonitor.getAllMonitory();
		
		assertNull(monit.size());
		
	}
	
	@Test
	public void checkfindFirmaById() {
		
		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
		
		managerMonitor.addMonitor(monitor);
		
		List<Monitor> monit = new ArrayList<Monitor>();
		monit.add(monitor);
		
		
		Firma firma = new Firma();
		firma.setMarka(marka1);
		firma.setRegon(regon1);
		firma.setSzef(szef1);
		firma.setMonitory(monit);

		managerFirmy.addFirma(firma);
		
		assertNotNull(managerMonitor.getMonitorById(monitor.getId()));
		assertNotNull(managerFirmy.getFirmaById(firma.getId()));
		
		
		managerFirmy.deleteFirma(firma);

		assertNull(managerMonitor.getMonitorById(monitor.getId()));
		assertNull(managerFirmy.getFirmaById(firma.getId()));
		
		List<Firma> fir = managerFirmy.getAllFirma();

		assertEquals(fir.size(),0);
	}
	
//------------SZUKANIE WSZYSTKICH MONITORÓW I FIRM-------------------------------------------------	
	@Test
	public void checkfindAllMonitor() {
	
		List<Monitor> monit = managerMonitor.getAllMonitory();
		assertNull(monit.size());
		assertEquals(0,monit.size());
		
		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
	
		managerMonitor.addMonitor(monitor);
	
		List<Monitor> monit1 = managerMonitor.getAllMonitory();
		assertNotNull(monit1.size());
		assertEquals(1,monit1.size());
	}
	
	@Test
	public void findAllUkladyCheck() {
	
		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
		
		managerMonitor.addMonitor(monitor);
		
		List<Monitor> monit = new ArrayList<Monitor>();
		monit.add(monitor);
		
		Monitor monitor1 = new Monitor();
		monitor1.setNazwa(nazwa2);
		monitor1.setRodzaj(rodzaj2);
		monitor1.setPrzekatna(przekatna2);
		monitor1.setWaga(waga2);
	
		managerMonitor.addMonitor(monitor1);
		
		List<Monitor> monit1 = new ArrayList<Monitor>();
		monit1.add(monitor1);
		
		Firma firma = new Firma();
		firma.setMarka(marka1);
		firma.setRegon(regon1);
		firma.setSzef(szef1);
		firma.setMonitory(monit);

		managerFirmy.addFirma(firma);
		
		Firma firma1 = new Firma();
		firma1.setMarka(marka2);
		firma1.setRegon(regon2);
		firma1.setSzef(szef2);
		firma1.setMonitory(monit1);

		managerFirmy.addFirma(firma1);

		List<Firma> fir = managerFirmy.getAllFirma();
		assertEquals(fir.size(),2);
		assertNotSame(fir.size(), 1);
		assertNotNull(fir.size());
	
		
	}
	

//---------------EDYCJA MONITORÓW I FIRM-----------------------------------------------	
	@Test
	public void checkUpdateMonitor() {
		
		
		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
		
		Monitor monitor1 = new Monitor();
		monitor1.setNazwa(nazwa2);
		monitor1.setRodzaj(rodzaj2);
		monitor1.setPrzekatna(przekatna2);
		monitor1.setWaga(waga2);
		
		
		managerMonitor.addMonitor(monitor);
		managerMonitor.addMonitor(monitor1);

		List<Monitor> monit = managerMonitor.getAllMonitory();
		assertEquals(monit.size(),2);

		Monitor mo = monit.get(0);
		
		monitor.setNazwa(rodzaj1);
		monitor.setRodzaj(nazwa1);

		managerMonitor.updateMonitor(monitor);
		
		assertEquals(rodzaj1,mo.getNazwa());
		assertEquals(nazwa1,mo.getRodzaj());
		assertEquals(przekatna1,mo.getPrzekatna());
		assertEquals(waga1,mo.getWaga());
		
		assertNotSame(rodzaj1,monitor1.getNazwa());
		assertNotSame(nazwa1,monitor1.getRodzaj());
		
		
	}
	
	@Test
	public void editUkladCheck() {
		
		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
		
		managerMonitor.addMonitor(monitor);
		
		List<Monitor> monit = new ArrayList<Monitor>();
		monit.add(monitor);
		
		Monitor monitor1 = new Monitor();
		monitor1.setNazwa(nazwa2);
		monitor1.setRodzaj(rodzaj2);
		monitor1.setPrzekatna(przekatna2);
		monitor1.setWaga(waga2);
	
		managerMonitor.addMonitor(monitor1);
		
		List<Monitor> monit1 = new ArrayList<Monitor>();
		monit1.add(monitor1);
		
		Firma firma = new Firma();
		firma.setMarka(marka1);
		firma.setRegon(regon1);
		firma.setSzef(szef1);
		firma.setMonitory(monit);

		managerFirmy.addFirma(firma);
		
		Firma firma1 = new Firma();
		firma1.setMarka(marka2);
		firma1.setRegon(regon2);
		firma1.setSzef(szef2);
		firma1.setMonitory(monit1);

		managerFirmy.addFirma(firma1);

		
		List<Firma> fir = managerFirmy.getAllFirma();
		
		assertEquals(fir.size(),2);
		
		firma.setMarka(szef1);
		firma.setSzef(marka1);
		
		managerFirmy.updateFirma(firma);
		
		List<Firma> fir1 = managerFirmy.getAllFirma();
		Firma fir2= fir1.get(0);
		
		assertEquals(szef1,fir2.getMarka());
		assertEquals(regon1,fir2.getRegon());
		assertEquals(marka1,fir2.getSzef());
		assertEquals(monit.size(),fir2.getMonitory().size());
		
		assertNotSame(szef1,firma1.getMarka());
		assertNotSame(marka1,firma1.getSzef());
		
		
		
	}
//-------------------SZUKANIE PO CZYMŚ-------------------------------------	
	
	@Test
	public void findMonitorbyWaga() {
		
	
		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
		
		managerMonitor.addMonitor(monitor);
		assertNotNull(managerMonitor.getMonitorById(monitor.getId()));
		
		assertNotNull(managerMonitor.getMonitorByWaga(monitor.getWaga()));
		List<Monitor> monit = managerMonitor.getMonitorByWaga(monitor.getWaga());
		Monitor mo = monit.get(0);
		assertEquals(waga1, mo.getWaga());
		
	
	}
	
	@Test
	public void checkfindByRegon() {
		
		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
		
		managerMonitor.addMonitor(monitor);
		
		List<Monitor> monit = new ArrayList<Monitor>();
		monit.add(monitor);
		
		
		Firma firma = new Firma();
		firma.setMarka(marka1);
		firma.setRegon(regon1);
		firma.setSzef(szef1);
		firma.setMonitory(monit);

		managerFirmy.addFirma(firma);
		
		
		assertNotNull(managerFirmy.getFirmaById(firma.getId()));
		assertNotNull(managerMonitor.getMonitorById(monitor.getId()));
		
		List<Firma> fir = managerFirmy.getAllFirma();

		assertEquals(fir.size(), 1);
		
		assertNotNull(managerFirmy.getFirmaByRegon(firma.getRegon()));
		
		List<Firma> fir1 = managerFirmy.getFirmaByRegon(firma.getRegon());
		Firma firmo = fir1.get(0);
		assertEquals(regon1, firmo.getRegon());
		
		
	}
//------------WYRAŻENIA REGULARNE-----------------------------------------------------
	
	@Test
	public void checkznajdzMonitor() {
		
		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
		
		managerMonitor.addMonitor(monitor);
	
	
	
		List<Monitor> monit = managerMonitor.getAllMonitory();
		
	
		assertEquals(monit.size(),1);
		
		assertNotNull(managerMonitor.getMonitorByPrzekatnaWaga(przekatna1, waga1));
		List<Monitor> monit1 = managerMonitor.getMonitorByPrzekatnaWaga(przekatna1, waga1);
		Monitor mo = monit1.get(0);
		
		assertEquals(przekatna1,mo.getPrzekatna());
		assertEquals(waga1,mo.getWaga());
		
	
		List<Monitor> monit2 = managerMonitor.getMonitorByPrzekatnaWaga(przekatna1, waga2);
		
		assertEquals(monit2.size(),0);
		
		
	}
	
	@Test
	public void checkznajdzFirme() {

		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
		
		managerMonitor.addMonitor(monitor);
		
		List<Monitor> monit = new ArrayList<Monitor>();
		monit.add(monitor);
		
		
		Firma firma = new Firma();
		firma.setMarka(marka1);
		firma.setRegon(regon1);
		firma.setSzef(szef1);
		firma.setMonitory(monit);

		managerFirmy.addFirma(firma);
		
		List<Firma> fir = managerFirmy.getAllFirma();

		assertEquals(fir.size(), 1);
		
		assertNotNull(managerFirmy.getFirmaByMarkaRegon(marka1, regon1));
		List<Firma> fir1 = managerFirmy.getFirmaByMarkaRegon(marka1, regon1);
		Firma f = fir1.get(0);
		
		assertEquals(marka1,f.getMarka());
		assertEquals(regon1,f.getRegon());
		
	
		List<Firma> fir2 = managerFirmy.getFirmaByMarkaRegon(marka1, regon2);
		
		assertEquals(fir2.size(),0);
	}
	
//------------------SZUKANIE MONITORÓW NALEŻĄCYCH DO FIRM----------------------------
	
	@Test
	public void checkPobranieMonitorówFirmy() {
		
		Monitor monitor = new Monitor();
		monitor.setNazwa(nazwa1);
		monitor.setRodzaj(rodzaj1);
		monitor.setPrzekatna(przekatna1);
		monitor.setWaga(waga1);
		
		managerMonitor.addMonitor(monitor);

		Monitor monitor1 = new Monitor();
		monitor1.setNazwa(nazwa2);
		monitor1.setRodzaj(rodzaj2);
		monitor1.setPrzekatna(przekatna2);
		monitor1.setWaga(waga2);
	
		managerMonitor.addMonitor(monitor1);
		
		List<Monitor> monit = new ArrayList<Monitor>();
		monit.add(monitor);
		monit.add(monitor1);
		
		Firma firma = new Firma();
		firma.setMarka(marka1);
		firma.setRegon(regon1);
		firma.setSzef(szef1);
		firma.setMonitory(monit);

		managerFirmy.addFirma(firma);
		
		List<Monitor> lista = managerFirmy.getMonitorFirmy(firma);
		assertNotNull(lista);
		
		assertEquals(lista.size(), 2);
		
		Monitor mo1 = lista.get(0);
		Monitor mo2 = lista.get(1);
		
		assertEquals(mo1.getNazwa(), nazwa1);
		assertEquals(mo1.getRodzaj(),rodzaj1);
		assertEquals(mo1.getPrzekatna(), przekatna1);
		assertEquals(mo1.getWaga(), waga1);
		
		assertEquals(mo2.getNazwa(), nazwa2);
		assertEquals(mo2.getRodzaj(),rodzaj2);
		assertEquals(mo2.getPrzekatna(), przekatna2);
		assertEquals(mo2.getWaga(), waga2);
		
	
	}
	
	
	
	
	
}
