package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import java.util.List;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;

/**
 * Interface remota para acceder a las operaciones del EJB.
 * @author Richard Vanegas
 *
 */
public interface ICustomerRemote {

	public void crearCustomer(Customer cus);
	public Customer buscarCustomer(String idType,String idNum);
	public void editarCustomer(Customer c);
	public List<Customer> listarCustomers();
}
