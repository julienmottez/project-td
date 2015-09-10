package fr.treeptik.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
	
	@Embedded
	@Column(name = "north_west")
    @AttributeOverrides({
            @AttributeOverride(name = "longitude", column = @Column(name = "north_west_longitude")),
            @AttributeOverride(name = "latitude", column = @Column(name = "north_west_latitude"))
    })
	private Coordinate northWest;
	
	@Embedded
    @Column(name = "south_east")
    @AttributeOverrides({
            @AttributeOverride(name = "longitude", column = @Column(name = "south_east_longitude")),
            @AttributeOverride(name = "latitude", column = @Column(name = "south_east_latitude"))
    })
	private Coordinate southEast;

	public Area() {
		
	}
	
	public Area(Coordinate northWest, Coordinate southEast) {
		super();
		this.northWest = northWest;
		this.southEast = southEast;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Coordinate getNorthWest() {
		return northWest;
	}

	public void setNorthWest(Coordinate northWest) {
		this.northWest = northWest;
	}

	public Coordinate getSouthEast() {
		return southEast;
	}

	public void setSouthEast(Coordinate southEast) {
		this.southEast = southEast;
	}
	

}
