package server.tests;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import server.model.Vacation;
import server.repository.VacationRepository;
public class VacationTest {
@Autowired 
VacationRepository vrepos; 

@Test
public void testCreateVacation() {
	Vacation v = new Vacation();
	v.setId(1L);
	v.setTitle("Kreuzfahrt");
	v.setDestination("Karibik");
	v.setDescription("Wunderbare Karibikrundfahrt");
	vrepos.save(v);
	assertNotNull(vrepos.findById(1L).get()); 
}
@Test
public void testUpdate() {
	Vacation v = vrepos.findById(1L).get(); 
	v.setDestination("Südsee");
	vrepos.save(v); 
	assertNotEquals(v,vrepos.findById(1L).get()); 
} 
@Test
public void testDelete() {
	vrepos.deleteById(1L);
	assertNull(vrepos.findById(1L).get());
}
}
