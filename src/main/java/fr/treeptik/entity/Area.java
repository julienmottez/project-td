package fr.treeptik.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "area")
public class Area implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Double northWest;
	private Double southEast;

	public Area(){
	
	}


	public Area(Integer id, Double northWest, Double southEast) {
		super();
		this.id = id;
		this.northWest = northWest;
		this.southEast = southEast;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Double getNorthWest() {
		return northWest;
	}


	public void setNorthWest(Double northWest) {
		this.northWest = northWest;
	}


	public Double getSouthEast() {
		return southEast;
	}


	public void setSouthEast(Double southEast) {
		this.southEast = southEast;
	}
	
	
	

}
