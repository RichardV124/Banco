package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_CUENTA_ASOCIADA")
public class CuentaAsociada implements Serializable{
	
	/**
	 * id autoincrementable de la cuenta asociada
	 */
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="owner_name", nullable=false)
	private String ownerName;
	
	@Column(name="identification_type", nullable=false)
	private String ownerTypeId;
	
	@Column(name="identification_number")
	private String ownerNumId;
	
	@JoinColumn(name = "bank")
	@ManyToOne(cascade = {})
	private Bank bank;
	
	@JoinColumn(name = "customer")
	@ManyToOne(cascade = {})
	private Customer customer;
	
	@Column(name="number", nullable=false)
	private String number;
	
	@Column(name="name")
	private String name;
}
