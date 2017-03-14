package co.edu.eam.ingesoft.standalone.gui.controlador;

import java.util.List;

import javax.naming.NamingException;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Franchise;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICreditCardRemote;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.IFranchiseRemote;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ISavingAccountRemote;
import co.edu.eam.ingesoft.standalone.gui.util.ServiceLocator;

/**
 * Controlador de la ventana gestion customer
 * 
 * @author Richard Vanegas
 *
 */
public class ControladorVentanaGestionProductos {

	/**
	 * Inteface remota del EJB
	 */
	private ICreditCardRemote creditCardRemoto;

	/**
	 * Inteface remota del EJB
	 */
	private ISavingAccountRemote savingAccountRemoto;
	
	/**
	 * Inteface remota del EJB
	 */
	private IFranchiseRemote franchiseRemoto;

	/**
	 * constructor
	 * 
	 * @throws NamingException
	 */
	public ControladorVentanaGestionProductos() throws NamingException {
		savingAccountRemoto = (ISavingAccountRemote) ServiceLocator.buscarEJB("SavingAccountEJB",
				ISavingAccountRemote.class.getCanonicalName());
		creditCardRemoto = (ICreditCardRemote) ServiceLocator.buscarEJB("CreditCardEJB",
				ICreditCardRemote.class.getCanonicalName());
		franchiseRemoto = (IFranchiseRemote) ServiceLocator.buscarEJB("FranchiseEJB",
				IFranchiseRemote.class.getCanonicalName());
	}

	/**
	 * metodo para buscar una credit card.
	 * 
	 * @param number
	 * 
	 * @return la credit card.
	 */
	public CreditCard buscarCreditCard(String number) {
		return creditCardRemoto.buscarCreditCard(number);
	}

	/**
	 * metodo para crear credit card.
	 * 
	 * @param credit,
	 *            credit card
	 */
	public void crearCreditCard(CreditCard credit) {
		creditCardRemoto.crearCreditCard(credit);
		
	}

	/**
	 * metodo para crear una cuenta de ahorros.
	 * 
	 * @param saving,
	 *            una cuenta de ahorros
	 */
	public void crearSavingAccount(SavingAccount saving) {
			savingAccountRemoto.crearSavingAccount(saving);
	}

	/**
	 * metodo para buscar una cuenta de ahorros.
	 * 
	 * @param number
	 * 
	 * @return la cuenta de ahorros.
	 */
	public SavingAccount buscarSavingAccount(String number) {
		return savingAccountRemoto.buscarSavingAccount(number);
	}
	
	/**
	 * metodo para consignar saldo a una cuenta.
	 * 
	 * @param number
	 * 
	 * @return la cuenta de ahorros.
	 */
	public void consignarMontoCuenta(SavingAccount sav,double monto) {
		savingAccountRemoto.consignarMontoCuenta(sav,monto);
	}
	
	/**
	 * metodo para transladar saldo de una cuenta a otra.
	 * 
	 * @param number
	 * 
	 * @return la cuenta de ahorros.
	 */
	public void transladarMontoCuenta(SavingAccount savSalida,SavingAccount savEntrada,double monto) {
		savingAccountRemoto.transladarMontoCuenta(savSalida,savEntrada,monto);
	}
	
	/**
	 * metodo para retirar saldo a una cuenta.
	 * 
	 * @param number
	 * 
	 * @return la cuenta de ahorros.
	 */
	public void retirarMontoCuenta(SavingAccount sav,double monto) {
		savingAccountRemoto.retirarMontoCuenta(sav,monto);
	}
	
	/**
	 * Metodo para listar todas las franquicias
	 * @return lista con las franquicias del banco
	 */
	public List<Franchise> listarFranquicia(){
		return franchiseRemoto.listarFranquicia();
	}

	/**
	 * Metodo para listar todas las cuentas de ahorro de un cliente
	 * @return lista con las cuentas de ahorro de un cliente
	 */
	public List<SavingAccount> listarCuentasCliente(Customer c){
		return savingAccountRemoto.listarCuentasCliente(c);
	}
}
