package com.pes.web.test.unitest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.pes.web.dao.ComplexityDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:WebContent/WEB-INF/pes-servlet.xml")
public class BasicTest {
	
	@Autowired
	private ComplexityDAO complexityDAO;

	
	@Test
    public void testAssertions() {
		assertTrue(true);
    }
}
