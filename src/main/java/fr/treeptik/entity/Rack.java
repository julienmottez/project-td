package fr.treeptik.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "rack")
public class Rack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Transient
	private String name;

	@Column(name = "quantity_available")
	private Integer quantityAvailable;

	@ManyToOne
	private Drink drink;

	@ManyToOne
	private Distributor distributor;

	@Column(name = "column_distributor")
	private Integer colonneDistributor;

	@Column(name = "line_distributor")
	private Integer ligneDistributor;

	@ManyToOne
	@JoinColumn(name = "rack_id")
	private TypeRack typeRack;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	public Integer getColonneDistributor() {
		return colonneDistributor;
	}

	public void setColonneDistributor(Integer colonneDistributor) {
		this.colonneDistributor = colonneDistributor;
	}

	public Integer getLigneDistributor() {
		return ligneDistributor;
	}

	public void setLigneDistributor(Integer ligneDistributor) {
		this.ligneDistributor = ligneDistributor;
	}

	public TypeRack getTypeRack() {
		return typeRack;
	}

	public void setTypeRack(TypeRack typeRack) {
		this.typeRack = typeRack;
	}

	public Integer getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(Integer quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}
}
