package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="T_BANK")
@NamedQueries({ 
	@NamedQuery(name = Bank.CONSULTA_LISTAR_BANCOS, query = "SELECT b FROM Bank b") 
	})
public class Bank implements Serializable{
	
	public static final String CONSULTA_LISTAR_BANCOS = "Bank.ListarBancos";
	
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

	
	public Bank(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
}
