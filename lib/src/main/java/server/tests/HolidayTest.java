package server.tests;




import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fh.aalen.model.Holiday;
import fh.aalen.repository.HolidayRepository;
public class HolidayTest {
@Autowired
HolidayRepository hrepos; 

@Test
public void testCreateHoliday () {
	Holiday h = new Holiday();
	h.setId(1L);
	h.setTitle("Herbstferien");
	h.setStartDate(null);
	h.setEndDate(null);
	hrepos.save(h);
	assertNotNull(hrepos.findById(1L).get()); 
	
}
@Test
public void testUpdateHoliday() {
	Holiday h = hrepos.findById(1L).get(); 
	h.setTitle("Herbstferien");
	assertNotEquals(h,hrepos.findById(1L).get()); 
}
@Test
public void testDeleteHoliday() {
	hrepos.deleteById(1L);
	assertNull(hrepos.findById(1L).get()); 
}
}
