package fr.treeptik.entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class TemperatureRange implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static TemperatureRange inCelsius(int from, int to) {
        return new TemperatureRange(
                new Temperature(from, Temperature.Unit.CELSIUS),
                new Temperature(to, Temperature.Unit.CELSIUS)
        );
    }

    @Column(name = "from")
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "cold_storage_from_value")),
            @AttributeOverride(name = "unit", column = @Column(name = "cold_storage_from_unit"))
    })
    private Temperature from;

    @Column(name = "to")
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "cold_storage_to_value")),
            @AttributeOverride(name = "unit", column = @Column(name = "cold_storage_to_unit"))
    })
    private Temperature to;

    public TemperatureRange(Temperature from, Temperature to) {
        checkTemperaturesHaveSameUnit(from, to);

        this.from = from;
        this.to = to;
    }

    public Temperature getFrom() {
        return from;
    }

    public void setFrom(Temperature from) {
        this.from = from;
    }

    public Temperature getTo() {
        return to;
    }

    public void setTo(Temperature to) {
        this.to = to;
    }

    public boolean contains(Temperature temperature) {
        checkUnitsAreTheSame(temperature.getUnit(), from.getUnit());

        return temperature.getValue() >= from.getValue() &&
                temperature.getValue() <= to.getValue();
    }

    private void checkTemperaturesHaveSameUnit(Temperature from, Temperature to) {
        checkUnitsAreTheSame(from.getUnit(), to.getUnit());
    }

    private void checkUnitsAreTheSame(Temperature.Unit unit, Temperature.Unit comparedUnit) {
        if (unit != comparedUnit) {
            throw new TemperaturesDoesNotHaveSameUnitException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TemperatureRange)) return false;

        TemperatureRange that = (TemperatureRange) o;

        if (!from.equals(that.from)) return false;
        return to.equals(that.to);

    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TemperatureRange{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }

    public class TemperaturesDoesNotHaveSameUnitException extends RuntimeException {
    }
}
