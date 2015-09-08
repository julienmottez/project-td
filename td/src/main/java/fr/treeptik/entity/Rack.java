package fr.treeptik.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	// @ManyToOne
	// private Distributor distributor;

	@Column(name = "colonne_distributor")
	private Integer colonneDistributor;

	@Column(name = "ligne_distributor")
	private Integer ligneDistributor;

	// @Column(name = "type_rack")
	// private TypeRack typeRack;

}
