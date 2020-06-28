package org.d11.rest;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Tag(Tags.INTEGRATION_TEST)
class D11RestApplicationTest {

	@Test	
	public void contextLoads() {
		// We don't have to do anything here. The mere presence of this test will spin up the Spring application context
		// and check many things, including that methods in JPA repositories make sense against the JPA database schema. 
	}

}
