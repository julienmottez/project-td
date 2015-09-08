package fr.treeptik.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Distributeur implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id_logic")
	private String idLogic;
	@Embedded
	private Address address;
	
	public String getIdLogic() {
		return idLogic;
	}
	public void setIdLogic(String idLogic) {
		this.idLogic = idLogic;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
