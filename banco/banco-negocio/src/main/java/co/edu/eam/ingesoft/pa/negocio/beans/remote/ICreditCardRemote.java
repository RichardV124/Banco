package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import java.util.List;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;

/**
 * Interface remota para acceder a las operaciones del EJB.
 * @author Richard Vanegas
 *
 */
public interface ICreditCardRemote {

	public void crearCreditCard(CreditCard creditCard);
	public void editarCreditCard(CreditCard creditCard,double consumo);
	public CreditCard buscarCreditCard(String num);
	public List<CreditCard> listarTarjetasCliente(Customer c);
	
}
