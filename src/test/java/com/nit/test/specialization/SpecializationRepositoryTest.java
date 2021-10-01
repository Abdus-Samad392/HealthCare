package com.nit.test.specialization;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.nit.entity.Specialization;
import com.nit.repo.SpecializationRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestMethodOrder(value = OrderAnnotation.class)
public class SpecializationRepositoryTest {

	@Autowired
	private SpecializationRepository repo;
	
	@Test
	@Order(1)
	public void testSpecRegister() {
		Specialization spec=new Specialization();
		spec.setSpecCode("CRDLGST");
		spec.setSpecName("Cardiologist");
		spec.setSpecNote("They are experts on the heart and blood vessel");
		spec=repo.save(spec);
		assertNotNull(spec.getId());
	}
	
	@Test
	@Order(2)
	public void testSpecData() {
		List<Specialization> specs=repo.findAll();
		if(specs==null)
			fail("No Specialiation Found");
	}
}
