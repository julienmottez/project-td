package fr.treeptik.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TemperatureRangeTest {

    @Test
    public void testRange() throws Exception {
        TemperatureRange range = new TemperatureRange(
                new Temperature(0, Temperature.Unit.CELSIUS),
                new Temperature(5, Temperature.Unit.CELSIUS)
        );

        assertTrue(range.contains(Temperature.inCelsius(0)));
        assertTrue(range.contains(Temperature.inCelsius(1)));
        assertTrue(range.contains(Temperature.inCelsius(2)));
        assertTrue(range.contains(Temperature.inCelsius(3)));
        assertTrue(range.contains(Temperature.inCelsius(4)));
        assertTrue(range.contains(Temperature.inCelsius(5)));

        assertFalse(range.contains(Temperature.inCelsius(-1)));
        assertFalse(range.contains(Temperature.inCelsius(-100)));
        assertFalse(range.contains(Temperature.inCelsius(6)));
        assertFalse(range.contains(Temperature.inCelsius(600)));
    }

    @Test(expected = TemperatureRange.TemperaturesDoesNotHaveSameUnitException.class)
    public void rangeFromToShouldHaveSameUnits() throws Exception {
        new TemperatureRange(
                new Temperature(5, Temperature.Unit.CELSIUS),
                new Temperature(6, Temperature.Unit.FAHRENHEIT)
        );
    }

    @Test
    public void testInCelsius() throws Exception {
        TemperatureRange temperatureRange = TemperatureRange.inCelsius(0, 5);
        
        assertEquals(Temperature.inCelsius(0), temperatureRange.getFrom());
        assertEquals(Temperature.inCelsius(5), temperatureRange.getTo());
    }

    @Test
    public void testEquality() throws Exception {
        TemperatureRange range1 = TemperatureRange.inCelsius(0, 5);
        TemperatureRange range2 = TemperatureRange.inCelsius(0, 5);
        TemperatureRange range3 = TemperatureRange.inCelsius(50, 500);

        assertTrue(range1.equals(range1));
        assertTrue(range1.equals(range2));

        assertFalse(range1.equals(range3));
    }
}