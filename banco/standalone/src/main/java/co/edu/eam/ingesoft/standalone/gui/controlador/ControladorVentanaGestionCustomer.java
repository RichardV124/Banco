package co.edu.eam.ingesoft.standalone.gui.controlador;

import javax.naming.NamingException;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICustomerRemote;
import co.edu.eam.ingesoft.standalone.gui.util.ServiceLocator;


/**
 * Controlador de la ventana gestion customer
 * @author Richard Vanegas
 *
 */
public class ControladorVentanaGestionCustomer {

	/**
	 * Inteface remota del EJB
	 */
	private ICustomerRemote customerRemoto;
	
	/**
	 * constructor
	 * @throws NamingException
	 */
	public ControladorVentanaGestionCustomer() throws NamingException {
		customerRemoto=(ICustomerRemote) ServiceLocator.buscarEJB("CustomerEJB",
				ICustomerRemote.class.getCanonicalName());
	}
	
	
	/**
	 * metodo para buscar customer.
	 * @param identificacion y tipo de identificacion
	 * @return el customer.
	 */
	public Customer buscarCustomer(String idType, String idNum){
		return customerRemoto.buscarCustomer(idType, idNum);
	}
	
	/**
	 * metodo para crear customer.
	 * @param cus, customer
	 */
	public void crearCustomer(Customer cus){
		customerRemoto.crearCustomer(cus);
	}
	
	/**
	 * metodo para editar customer.
	 * @param c
	 * @return el customer.
	 */
	public void editarCustomer(Customer c){
		customerRemoto.editarCustomer(c);
	}
	
	
}
