package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
	
	@JoinColumns({
		@JoinColumn(name = "tipo_id_cliente", referencedColumnName = "identification_type"),
		@JoinColumn(name = "id_cliente", referencedColumnName = "identification_number") 
		})
	@ManyToOne(cascade = {})
	private Customer customer;
	
	@Column(name="number", nullable=false)
	private String number;
	
	@Column(name="name")
	private String name;

	public CuentaAsociada() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CuentaAsociada(int id, String ownerName, String ownerTypeId, String ownerNumId, Bank bank, Customer customer,
			String number, String name) {
		super();
		this.id = id;
		this.ownerName = ownerName;
		this.ownerTypeId = ownerTypeId;
		this.ownerNumId = ownerNumId;
		this.bank = bank;
		this.customer = customer;
		this.number = number;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerTypeId() {
		return ownerTypeId;
	}

	public void setOwnerTypeId(String ownerTypeId) {
		this.ownerTypeId = ownerTypeId;
	}

	public String getOwnerNumId() {
		return ownerNumId;
	}

	public void setOwnerNumId(String ownerNumId) {
		this.ownerNumId = ownerNumId;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
}
