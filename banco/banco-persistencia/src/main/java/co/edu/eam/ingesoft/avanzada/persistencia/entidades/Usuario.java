package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class Usuario implements Serializable {


	@Id
	@Column(name="persona")
	private String idPersona;
	
	@Column(unique=true,name="usuario")
	private String user;
	
	@Column
	private String password;
	
	@OneToOne
	@JoinColumn(name="customer",insertable=false,updatable=false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="idrol")
	private Rol rol;
	
	public Usuario() {
	}
	
	
}
