package co.edu.eam.ingesoft.pa.banco.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociada;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SavingAccountEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@Named("controladorTransferirAjax")
@ViewScoped
public class TransferirCuentaAsociada implements Serializable{

	/**
	 * cuenta de ahorros seleccionada
	 */
	private String asociadaSeleccionada;
	
	/**
	 * cuenta de ahorros seleccionada
	 */
	private String cuentaSeleccionada;

	/**
	 * Monto que se va a transferir
	 */
	private double monto;
	
	/**
	 * Codigo de verificacion
	 */
	private String codigoverificacion;
	
	/**
	 * lista las cuentas de ahorro
	 */
	private List<SavingAccount> cuentas;
	
	/**
	 * lista las cuentas de ahorro
	 */
	private List<CuentaAsociada> asociadas;
	
	/**
	 * EJB del consumo de la cuenta de ahorros
	 */
	@EJB
	private SavingAccountEJB savingAccountEJB;
	
	/**
	 * EJB de las cuentas asociadas
	 */
	@EJB
	private CuentaAsociadaEJB asociadaCuentaEJB;
	
	@PostConstruct
	public void inicializar() {
		try {
			Usuario usuario = Faces.getSessionAttribute("user");
			cuentas = savingAccountEJB.listarCuentasCliente(usuario.getCustomer());
			asociadas = asociadaCuentaEJB.listarCuentas(usuario.getCustomer());
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	}

	public String getAsociadaSeleccionada() {
		return asociadaSeleccionada;
	}

	public void setAsociadaSeleccionada(String asociadaSeleccionada) {
		this.asociadaSeleccionada = asociadaSeleccionada;
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

	public String getCodigoverificacion() {
		return codigoverificacion;
	}

	public void setCodigoverificacion(String codigoverificacion) {
		this.codigoverificacion = codigoverificacion;
	}

	public List<SavingAccount> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<SavingAccount> cuentas) {
		this.cuentas = cuentas;
	}

	public List<CuentaAsociada> getAsociadas() {
		return asociadas;
	}

	public void setAsociadas(List<CuentaAsociada> asociadas) {
		this.asociadas = asociadas;
	}

	public SavingAccountEJB getSavingAccountEJB() {
		return savingAccountEJB;
	}

	public void setSavingAccountEJB(SavingAccountEJB savingAccountEJB) {
		this.savingAccountEJB = savingAccountEJB;
	}

	public CuentaAsociadaEJB getAsociadaCuentaEJB() {
		return asociadaCuentaEJB;
	}

	public void setAsociadaCuentaEJB(CuentaAsociadaEJB asociadaCuentaEJB) {
		this.asociadaCuentaEJB = asociadaCuentaEJB;
	}

	
}
