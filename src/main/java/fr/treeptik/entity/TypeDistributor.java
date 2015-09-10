package fr.treeptik.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "type_distributor")
public class TypeDistributor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	@Column(name = "number_colomns")
	private Integer numberColumns;

	@Column(name = "number_lines")
	private Integer numberLines;

	public TypeDistributor(Integer id, String name, Integer numberColumn, Integer numberLine) {
		super();
		this.id = id;
		this.name = name;
		this.numberColumns = numberColumn;
		this.numberLines = numberLine;
	}

	public TypeDistributor() {
		// TODO Auto-generated constructor stub
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

	public Integer getNumberColumn() {
		return numberColumns;
	}

	public void setNumberColumn(Integer numberColumn) {
		this.numberColumns = numberColumn;
	}

	public Integer getNumberLine() {
		return numberLines;
	}

	public void setNumberLine(Integer numberLine) {
		this.numberLines = numberLine;
	}

}
