package org.d11.rest.model.jpa;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.d11.rest.Tags;
import org.d11.rest.model.jpa.D11RestEntity;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class D11EntityTests {

    @Test
    public void validation() {
    	D11RestEntity d11Entity = new D11RestEntity();
    	
        assertTrue(d11Entity.isValid());   
    }
    
}
