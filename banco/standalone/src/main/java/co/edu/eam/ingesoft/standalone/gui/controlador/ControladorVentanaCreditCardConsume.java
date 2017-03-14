package co.edu.eam.ingesoft.standalone.gui.controlador;

import java.util.List;

import javax.naming.NamingException;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICreditCardConsumeRemote;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICreditCardRemote;
import co.edu.eam.ingesoft.standalone.gui.util.ServiceLocator;

public class ControladorVentanaCreditCardConsume {

	/**
	 * Inteface remota del EJB
	 */
	private ICreditCardRemote creditCardRemoto;
	
	/**
	 * Inteface remota del EJB
	 */
	private ICreditCardConsumeRemote creditCardConsumeRemoto;

	/**
	 * constructor
	 * 
	 * @throws NamingException
	 */
	public ControladorVentanaCreditCardConsume() throws NamingException {
	creditCardRemoto = (ICreditCardRemote) ServiceLocator.buscarEJB("CreditCardEJB",
			ICreditCardRemote.class.getCanonicalName());
	creditCardConsumeRemoto = (ICreditCardConsumeRemote) ServiceLocator.buscarEJB("CreditCardConsumeEJB",
			ICreditCardConsumeRemote.class.getCanonicalName());
	}

	/**
	 * Metodo para listar todas las tarjetas de credito de un cliente
	 * 
	 * @return lista con las tarjetas de credito de un cliente
	 */
	public List<CreditCard> listarTarjetasCliente(Customer c) {
		return creditCardRemoto.listarTarjetasCliente(c);
	}
	
	/**
	 * metodo para crear consumo de la tarjeta
	 * @param cus, consumo de la tarjeta
	 */
	public void crearCreditCardConsume(CreditCardConsume cus){
		creditCardConsumeRemoto.crearCreditCardConsume(cus);
	}
	
	public List<CreditCardConsume> listarConsumosTarjeta(CreditCard credit){
		return creditCardConsumeRemoto.listarConsumosTarjeta(credit);
	}
	
	
	public void editarCreditCard(CreditCard cc,double consumo){
		creditCardRemoto.editarCreditCard(cc,consumo);
	}
	
	
}
