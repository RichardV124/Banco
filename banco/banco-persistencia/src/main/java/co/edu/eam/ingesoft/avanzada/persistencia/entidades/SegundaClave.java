package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_SEGUNDA_CLAVE")
public class SegundaClave implements Serializable{
	
	@Id
	@Column(name="clave")
	private String clave;
	
	@JoinColumns({
		@JoinColumn(name="holder_idtype", referencedColumnName="identification_type"),
		@JoinColumn(name="holder_idnumber", referencedColumnName="identification_number")
	}
			)
	@ManyToOne(fetch=FetchType.EAGER)
	private Customer customer;
	
	@Column(name="fecha_generacion")
	@Temporal(TemporalType.TIME)
	private Date fechaGeneracion;
	
	@Column(name="fecha_vencimiento")
	@Temporal(TemporalType.TIME)
	private Date fechaVencimiento;

	public SegundaClave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SegundaClave(String clave, Customer customer, Date fechaGeneracion, Date fechaVencimiento) {
		super();
		this.clave = clave;
		this.customer = customer;
		this.fechaGeneracion = fechaGeneracion;
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	

}
