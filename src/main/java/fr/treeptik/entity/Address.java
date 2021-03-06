package fr.treeptik.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class Address implements Serializable{

	private static final long serialVersionUID = 1L;

	private String street;
	private String tawn;
	private String zip;
	
	
	public Address(String street, String tawn, String zip) {
		super();
		this.street = street;
		this.tawn = tawn;
		this.zip = zip;
	}
	
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTawn() {
		return tawn;
	}
	public void setTawn(String tawn) {
		this.tawn = tawn;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
}
