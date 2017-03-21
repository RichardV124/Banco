package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_BANK")
public class Bank implements Serializable{
	
	/**
	 * Id del banco
	 */
	@Id
	@Column(name="id")
	private String id;
	
	/**
	 * 
	 */
	@Column(name="name")
	private String name;

	/**
	 * Constructor
	 */
	public Bank() {
		super();
	}

	/**
	 * Constructor con los atributos
	 * @param id
	 * @param nombre
	 */
	public Bank(String id, String nombre) {
		super();
		this.id = id;
		this.name = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return name;
	}

	public void setNombre(String nombre) {
		this.name = nombre;
	}
	
	
}
