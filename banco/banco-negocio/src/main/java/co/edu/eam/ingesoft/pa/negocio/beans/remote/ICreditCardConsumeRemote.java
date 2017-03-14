package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import java.util.List;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardConsume;

/**
 * Interface remota para acceder a las operaciones del EJB.
 * @author Richard Vanegas
 *
 */
public interface ICreditCardConsumeRemote {

	public void crearCreditCardConsume(CreditCardConsume creditCard);
	public List<CreditCardConsume> listarConsumosTarjeta(CreditCard credit);
	
}
