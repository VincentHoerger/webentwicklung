package fh.aalen.tests;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fh.aalen.model.Member;
import fh.aalen.repository.MemberRepository;
public class MemberTest {
@Autowired
MemberRepository mrepos; 
@Test
public void testCreateMember() {
	Member m = new Member (); 
	m.setId(1L);
	m.setUsername("hansi");
	m.setFirstname("Hans-Dieter");
	m.setLastname("Flick");
	m.setDateOfBirth(null); 
	mrepos.save(m); 
	assertNotNull(mrepos.findById(1L).get()); 
}
@Test 
public void testUpdate() {
	Member m = mrepos.findById(1L).get(); 
	m.setLastname("Nagelsmann");
	mrepos.save(m); 
	assertNotEquals(m,mrepos.findById(1L).get()); 
}
@Test
public void testDelete() {
	mrepos.deleteById(1L);
	assertNull(mrepos.findById(1L).get()); 
}


}