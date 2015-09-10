package fr.treeptik.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@OneToMany(mappedBy = "typeDistributor")
	private List<Distributor> distributors;

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

	public Integer getNumberColumns() {
		return numberColumns;
	}

	public void setNumberColumns(Integer numberColumns) {
		this.numberColumns = numberColumns;
	}

	public Integer getNumberLines() {
		return numberLines;
	}

	public void setNumberLines(Integer numberLines) {
		this.numberLines = numberLines;
	}

}