package fr.treeptik.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Coordinate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "longitude")
	private float longitude;

	@Column(name = "latitude")
	private float latitude;
	
	public Coordinate() {
	}
	
	public Coordinate(float longitude, float latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "[longitude = " + longitude + ", latitude = " + latitude + "]";
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}





	
}
