package co.edu.eam.ingesoft.pa.banco.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;

@Named("customerController")
@ViewScoped
public class CustomerController implements Serializable{

	/**
	 * Tipo de documento del customer
	 */
	private String tipodocumento;
	
	/**
	 * Numero de identificacion del customer
	 */
	private String numerodocumento;
	
	/**
	 * Nombre del customer
	 */
	private String nombre;
	
	/**
	 * Apellido del customer
	 */
	private String apellido;

	/**
	 * lista de clientes
	 */
	private List<Customer> customers;
	
	/**
	 * EJB del customer
	 */
	@EJB
	private CustomerEJB customerEJB;
	
	@PostConstruct
	public void inicializar(){
		setCustomers(customerEJB.listarCustomers());
	}
	
	/**
	 * Metodo para crear un customer
	 */
	public void crear(){
	Customer c = new Customer();
	c.setName(nombre);
	c.setLastName(apellido);
	c.setIdNum(numerodocumento);
	c.setIdType(tipodocumento);
	customerEJB.crearCustomer(c);
	//limpiar campos
	nombre = "";
	apellido = "";
	numerodocumento = "";
	tipodocumento = null;
	setCustomers(customerEJB.listarCustomers());
	
	}
	
	/**
	 * Metodo para buscar un customer
	 */
	public void buscar(){
		
		Customer c = customerEJB.buscarCustomer(tipodocumento, numerodocumento);
		if(c!=null){
			nombre = c.getName();
			apellido = c.getLastName();
			numerodocumento = c.getIdNum();
			tipodocumento = c.getIdType();
			
			
		}
	}

	public String getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getNumerodocumento() {
		return numerodocumento;
	}

	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public CustomerEJB getCustomerEJB() {
		return customerEJB;
	}

	public void setCustomerEJB(CustomerEJB customerEJB) {
		this.customerEJB = customerEJB;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	
	
}
