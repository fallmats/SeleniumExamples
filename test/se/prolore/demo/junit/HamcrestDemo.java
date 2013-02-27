package se.prolore.demo.junit;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.Test;

import se.prolore.junit.demo.City;

public class HamcrestDemo {
	
	@Test
	public void testIntegers() {
		Integer myInt = new Integer(1);
		Integer anotherInt = new Integer(1);
		assertThat("Comparing", myInt, equalTo(anotherInt));
	}

	@Test
	public void testCitiesSameName() {
		City myCity = new City("Uppsala");
		City anotherCity = new City("Uppsala");
		assertThat("Same city name", myCity, equalTo(anotherCity));
	}
	
	@Test
	public void testCitiesDifferentName() {
		City myCity = new City("Uppsala");
		City anotherCity = new City("Falun");
		assertThat("Different city name", myCity, not(anotherCity));
	}
	
	@Test
	public void testCityInstance() {
		City myCity = new City("Uppsala");
		assertThat("City instance", myCity, instanceOf(City.class));
	}

}
