package fr.treeptik.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "drink")
public class Drink implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "cold")
    @Embedded
    private TemperatureRange coldStorage;
}
