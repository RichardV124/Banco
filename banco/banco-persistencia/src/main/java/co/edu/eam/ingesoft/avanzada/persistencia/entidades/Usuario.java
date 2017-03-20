package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_USUARIO")
public class Usuario implements Serializable {

	@Id
	@Column(unique=true,name="usuario")
	private String user;
	
	@Column(name="password")
	private String password;
	
	@OneToOne
	@JoinColumns({
		@JoinColumn(name="idType",referencedColumnName="identification_type"),
		@JoinColumn(name="idNum",referencedColumnName="identification_number")
		})
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="idrol")
	private Rol rol;
	
	public Usuario() {
	}



	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
	
}
