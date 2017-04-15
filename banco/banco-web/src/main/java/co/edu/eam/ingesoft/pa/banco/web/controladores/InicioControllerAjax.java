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
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SavingAccountEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@Named("inicioAjax")
@ViewScoped
public class InicioControllerAjax implements Serializable {

	/**
	 * lista de las tarjetas de credito del cliente
	 */
	private List<CreditCard> tarjetas;

	/**
	 * lista de las cuentas de ahorros del cliente
	 */
	private List<SavingAccount> cuentas;

	/**
	 * EJB de la tarjeta de credito
	 */
	@EJB
	private CreditCardEJB creditCardEJB;

	/**
	 * EJB del customer
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

		} catch (

		ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String redirigirAvanceCuenta(){
		return "/paginas/seguro/avancecuentaahorros.xhtml?faces-redirect=true";
	}
	
	public String redirigirInicio(){
		return "/paginas/seguro/inicio.xhtml?faces-redirect=true";
	}
	
	public String redirigirAsociacionCuenta(){
		return "/paginas/seguro/asociacioncuenta.xhtml?faces-redirect=true";
	}
	
	public String redirigirTransferirCuentaAsociada(){
		return "/paginas/seguro/transferircuentaasociada.xhtml?faces-redirect=true";
	}


	public List<CreditCard> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<CreditCard> tarjetas) {
		this.tarjetas = tarjetas;
	}

	public List<SavingAccount> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<SavingAccount> cuentas) {
		this.cuentas = cuentas;
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

}
