package co.edu.eam.ingesoft.pa.banco.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.CreditCardEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SavingAccountEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@Named("avanceCuentaAjax")
@ViewScoped
public class AvanceCuentaAhorrosControllerAjax implements Serializable {

	/**
	 * tarjeta de credito seleccionada
	 */
	private String tarjetaSeleccionada;

	/**
	 * Monto que se va a transferir
	 */
	private double monto;

	/**
	 * lista las cuentas de ahorro
	 */
	private List<SavingAccount> cuentas;

	/**
	 * lista las tarjetas de credito
	 */
	private List<CreditCard> tarjetas;

	/**
	 * cuenta de ahorros seleccionada
	 */
	private String cuentaSeleccionada;

	/**
	 * EJB de la tarjeta de credito
	 */
	@EJB
	private CreditCardEJB creditCardEJB;

	/**
	 * EJB del consumo de la cuenta de ahorros
	 */
	@EJB
	private SavingAccountEJB savingAccountEJB;

	@PostConstruct
	public void inicializar() {
		try {
			Usuario usuario = Faces.getSessionAttribute("user");
			cuentas = savingAccountEJB.listarCuentasCliente(usuario.getCustomer());
			tarjetas = creditCardEJB.listarTarjetasCliente(usuario.getCustomer());
			Messages.addFlashGlobalInfo(usuario.getUser());
			
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	}

	public void transferir() {

	}

	public void cancelar() {

	}

	public String getTarjetaSeleccionada() {
		return tarjetaSeleccionada;
	}

	public void setTarjetaSeleccionada(String tarjetaSeleccionada) {
		this.tarjetaSeleccionada = tarjetaSeleccionada;
	}

	public String getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(String cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public CreditCardEJB getCreditCardEJB() {
		return creditCardEJB;
	}

	public void setCreditCardEJB(CreditCardEJB creditCardEJB) {
		this.creditCardEJB = creditCardEJB;
	}

	public SavingAccountEJB getSavingAccountEJB() {
		return savingAccountEJB;
	}

	public void setSavingAccountEJB(SavingAccountEJB savingAccountEJB) {
		this.savingAccountEJB = savingAccountEJB;
	}

	public List<SavingAccount> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<SavingAccount> cuentas) {
		this.cuentas = cuentas;
	}

	public List<CreditCard> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<CreditCard> tarjetas) {
		this.tarjetas = tarjetas;
	}

}
