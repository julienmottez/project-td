package fr.treeptik.entity;

import java.io.Serializable;

public interface InterfaceEntity<I extends Serializable> extends Serializable {

	// public abstract Integer getId();
	I getId();

}
