package se.prolore.demo.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.prolore.junit.demo.TestTarget;

public class SimpleTests {
	
	TestTarget toTest;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		loadStaticData();
	}

	@Before
	public void setUp() throws Exception {
		toTest = new TestTarget();
	}

	@After
	public void tearDown() throws Exception {
		toTest = null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		clearStaticData();
	}

	@Test(expected = IllegalArgumentException.class)
	public void illegalArgument() {
		toTest.multiply(1000,3);
	}
	
	@Test
	public void happyPath() {
		int result = toTest.multiply(20,2);
		assertEquals("Happy path failing", 20*2, result);
	}
	

	private static void loadStaticData() {
		// TODO Auto-generated method stub
	}

	private static void clearStaticData() {
		// TODO Auto-generated method stub
	}

}
