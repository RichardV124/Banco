package co.edu.eam.ingesoft.standalone.gui.controlador;

import java.util.List;

import javax.naming.NamingException;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardPaymentConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICreditCardConsumeRemote;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICreditCardPaymentConsumeRemote;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICreditCardRemote;
import co.edu.eam.ingesoft.standalone.gui.util.ServiceLocator;

public class ControladorVentanaCreditCardPayment {

	/**
	 * Inteface remota del EJB
	 */
	private ICreditCardRemote creditCardRemoto;
	
	/**
	 * Inteface remota del EJB
	 */
	private ICreditCardPaymentConsumeRemote creditCardPaymentRemoto;

	/**
	 * Inteface remota del EJB
	 */
	private ICreditCardConsumeRemote creditCardConsumeRemoto;
	
	/**
	 * constructor
	 * 
	 * @throws NamingException
	 */
	public ControladorVentanaCreditCardPayment() throws NamingException {
		creditCardRemoto = (ICreditCardRemote) ServiceLocator.buscarEJB("CreditCardEJB",
				ICreditCardRemote.class.getCanonicalName());
		creditCardPaymentRemoto = (ICreditCardPaymentConsumeRemote) ServiceLocator.buscarEJB("CreditCardPaymentConsumeEJB",
				ICreditCardPaymentConsumeRemote.class.getCanonicalName());
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
	
	public List<CreditCardConsume> listarConsumosTarjeta(CreditCard credit){
		return creditCardConsumeRemoto.listarConsumosTarjeta(credit);
	}
	
	public void crearCreditCardPayment(String credit, double adicionar){
		creditCardPaymentRemoto.crearCreditCardPayment(credit,adicionar);
	}
	
	public void editarCreditCard(CreditCard credit,double monto){
		creditCardRemoto.editarCreditCard(credit,monto);
	}
	
	public double calcularCuotaTarjeta (CreditCard cc) throws Exception{
		return creditCardPaymentRemoto.calcularCuotaTarjeta(cc);
	}
	
}
