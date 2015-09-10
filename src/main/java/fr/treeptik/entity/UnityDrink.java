package fr.treeptik.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "unitydrink")
public class UnityDrink implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;
	
	
	
	@ManyToOne
	@JoinColumn(name = "drink_id")
	private Drink drink;



	public UnityDrink() {
		
	}



	public UnityDrink(int id, String name, Drink drink) {
		super();
		this.id = id;
		this.name = name;
		this.drink = drink;
	}



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



	public Drink getDrink() {
		return drink;
	}



	public void setDrink(Drink drink) {
		this.drink = drink;
	}
	
	
	
	
	
}
