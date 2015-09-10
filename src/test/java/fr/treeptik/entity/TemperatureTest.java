package fr.treeptik.entity;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.treeptik.entity.Temperature.Unit;

public class TemperatureTest {

	@Test
	public void testToString() throws Exception {
		assertEquals("5.0 °C", Temperature.inCelsius(5).toString());
		assertEquals("10.0 °F", Temperature.inFahrenheit(10).toString());
	}
	
	@Test
	public void testEquals() throws Exception {
		Temperature temp1 = Temperature.inCelsius(5);
		Temperature temp2 = Temperature.inCelsius(5);
		Temperature temp3 = Temperature.inCelsius(480);
		
		assertTrue(temp1.equals(temp1));
		assertTrue(temp1.equals(temp2));
		
		assertFalse(temp1.equals(temp3));
	}
	
	@Test
	public void testUnitEquality() throws Exception {
		assertTrue(Temperature.unitsAreTheSame(Unit.CELSIUS, Unit.CELSIUS));
		
		assertFalse(Temperature.unitsAreTheSame(Unit.CELSIUS, Unit.FAHRENHEIT));
	}
}
