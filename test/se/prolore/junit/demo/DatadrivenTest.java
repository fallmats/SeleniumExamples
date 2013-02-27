package se.prolore.junit.demo;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import se.prolore.junit.demo.TestTarget;

@RunWith(Parameterized.class)
public class DatadrivenTest {

	private int x, y;
	private int oracle;
	
	public DatadrivenTest(int x, int y) {
		this.x = x;
		this.y = y;
		oracle = this.x * this.y;
	}
	
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = {{2,3},{55,89},{333,45}};
		return Arrays.asList(data);
	}
	
	@Test
	public void dataTest() {
		int actual = new TestTarget().multiply(x, y);
		assertEquals("Not matching", oracle, actual);
	}
	
}
