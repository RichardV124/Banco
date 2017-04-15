package co.edu.eam.ingesoft.pa.banco.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.CreditCardConsumeEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CreditCardEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CreditCardPaymentConsumeEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SavingAccountEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@Named("payAjax")
@ViewScoped
public class CreditCardPaymentControllerAjax implements Serializable{
	
	/**
	 * Numero de la tarjeta de credito
	 */
	private String tarjetaSeleccionada;
	
	/**
	 * lista de la starjetas de credito
	 */
	private List<CreditCard> tarjetas;
	
	/**
	 * valor excedente para el pago de la cuota
	 */
	private double excedente;
	
	/**
	 * lista de consumos de la tarjeta de credito
	 */
	private List<CreditCardConsume> consumos;
	
	/**
	 * cuenta de ahorros seleccionada
	 */
	private String cuentaSeleccionada;
	
	/**
	 * lista las cuentas de ahorro
	 */
	private List<SavingAccount> cuentas;
	
	/**
	 * EJB del customer
	 */
	@EJB
	private CustomerEJB customerEJB;
	
	/**
	 * EJB de la tarjeta de credito
	 */
	@EJB
	private CreditCardEJB creditCardEJB;
	
	/**
	 * EJB del consumo de la tarjeta de credito
	 */
	@EJB
	private CreditCardConsumeEJB creditCardConsume;
	
	/**
	 * EJB del pago del consumo
	 */
	@EJB
	private CreditCardPaymentConsumeEJB creditCardPaymentEJB;
	
	/**
	 * EJB del consumo de la cuenta de ahorros
	 */
	@EJB
	private SavingAccountEJB savingAccountEJB;

	
	
	@PostConstruct
	public void inicializar(){
		
		try {
			Usuario usuario = Faces.getSessionAttribute("user");
			Customer c = usuario.getCustomer();
			tarjetas = creditCardEJB.listarTarjetasCliente(c);
		}catch(ExcepcionNegocio e1){
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para buscar un customer
	 */
	public void buscar(){
		try {
			Usuario usuario = Faces.getSessionAttribute("user");
			Customer c = usuario.getCustomer();
			if(c!=null){
				CreditCard cc = creditCardEJB.buscarCreditCard(tarjetaSeleccionada);
				if(cc!=null){
					setConsumos(creditCardConsume.listarConsumosTarjeta(cc));
					cuentas = savingAccountEJB.listarCuentasCliente(c);
				}
			}
		}catch(ExcepcionNegocio e1){
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}	
	}
	
	
	//gg se me borró todo. didier se la come
	
	/**
	 * Metodo para pagar uan cuota de la tarjeta
	 */
	public void pagarCuota(){
		System.out.println("asdkasdlkdaslkd" +cuentaSeleccionada);
			try {
				creditCardPaymentEJB.crearCreditCardPaymentCuentaAhorros(tarjetaSeleccionada, excedente,"11882590920");
				Messages.addFlashGlobalInfo("Pago realizado con exito");
			}catch(ExcepcionNegocio e1){
				Messages.addFlashGlobalError(e1.getMessage());
				e1.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				Messages.addFlashGlobalInfo(e.getMessage());
				e.printStackTrace();
			}
	}
	
	public void pagarConsumo(CreditCardConsume con){
		try{
			
			creditCardPaymentEJB.pagarUnConsumo(con);
			Messages.addFlashGlobalInfo("Se ha completado el pago de el consumo con exito");
			
		}catch(ExcepcionNegocio e1){
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	}

	
	

	public String getTarjetaSeleccionada() {
		return tarjetaSeleccionada;
	}

	public void setTarjetaSeleccionada(String tarjetaSeleccionada) {
		this.tarjetaSeleccionada = tarjetaSeleccionada;
	}

	public List<CreditCard> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<CreditCard> tarjetas) {
		this.tarjetas = tarjetas;
	}

	public SavingAccountEJB getSavingAccountEJB() {
		return savingAccountEJB;
	}

	public void setSavingAccountEJB(SavingAccountEJB savingAccountEJB) {
		this.savingAccountEJB = savingAccountEJB;
	}

	public List<CreditCardConsume> getConsumos() {
		return consumos;
	}

	public void setConsumos(List<CreditCardConsume> consumos) {
		this.consumos = consumos;
	}

	public CustomerEJB getCustomerEJB() {
		return customerEJB;
	}

	public void setCustomerEJB(CustomerEJB customerEJB) {
		this.customerEJB = customerEJB;
	}

	public CreditCardEJB getCreditCardEJB() {
		return creditCardEJB;
	}

	public void setCreditCardEJB(CreditCardEJB creditCardEJB) {
		this.creditCardEJB = creditCardEJB;
	}

	public CreditCardConsumeEJB getCreditCardConsume() {
		return creditCardConsume;
	}

	public void setCreditCardConsume(CreditCardConsumeEJB creditCardConsume) {
		this.creditCardConsume = creditCardConsume;
	}

	public double getExcedente() {
		return excedente;
	}

	public void setExcedente(double excedente) {
		this.excedente = excedente;
	}

	public String getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(String cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	public List<SavingAccount> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<SavingAccount> cuentas) {
		this.cuentas = cuentas;
	}

	public CreditCardPaymentConsumeEJB getCreditCardPaymentEJB() {
		return creditCardPaymentEJB;
	}

	public void setCreditCardPaymentEJB(CreditCardPaymentConsumeEJB creditCardPaymentEJB) {
		this.creditCardPaymentEJB = creditCardPaymentEJB;
	}
	
	
	
}
