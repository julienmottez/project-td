package fr.treeptik.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Temperature implements Serializable {

	private static final long serialVersionUID = 1L;

	public static boolean unitsAreTheSame(Temperature.Unit unit, Temperature.Unit comparedUnit) {
        if (unit.equals(comparedUnit)) {
            return true;
        } else {
        	return false;
        }
    }
	
	public static Temperature inCelsius(int value) {
		return new Temperature(value, Unit.CELSIUS);
	}
	
	public static Temperature inFahrenheit(int value) {
		return new Temperature(value, Unit.FAHRENHEIT);
	}

	public enum Unit {
		FAHRENHEIT, CELSIUS
	}

	@Column(name = "temperature_value")
	private float value;

	@Column(name = "temperature_unit")
	@Enumerated(EnumType.STRING)
	private Unit unit;

	public Temperature() {
	}
	
	public Temperature(int value, Unit unit) {
		this.value = value;
		this.unit = unit;
	}
	
	public float getValue() {
		return value;
	}

	public Unit getUnit() {
		return unit;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Temperature))
			return false;

		Temperature that = (Temperature) o;

		if (Float.compare(that.value, value) != 0)
			return false;
		return unit == that.unit;

	}

	@Override
	public int hashCode() {
		int result = (value != +0.0f ? Float.floatToIntBits(value) : 0);
		result = 31 * result + unit.hashCode();
		return result;
	}

	@Override
	public String toString() {
		String u = "";
		
		switch (unit) {
		case CELSIUS:
			u = "°C";
			break;
		case FAHRENHEIT:
			u = "°F";
			break;
		}

		return String.valueOf(value + " " + u);
	}
}
