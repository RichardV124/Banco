package co.edu.eam.ingesoft.pa.banco.web.controladores;

import java.awt.image.BandCombineOp;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Bank;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociada;
import co.edu.eam.ingesoft.pa.negocio.beans.BankEJB;

@Named("cuentaAsociadaController")
@ViewScoped
public class CuentaAsociadaControllerAjax implements Serializable{

	/**
	 * Nombre del Titular
	 */
	@NotNull(message="Debe ingresar el nombre del titular")
	private String nombretitular;
	
	/**
	 * Tipo de documento del customer
	 */
	@NotNull(message="Debe seleccionar el tipo de documento")
	private String tipodocumento;
	
	/**
	 * Numero de identificacion del titular
	 */
	@NotNull(message="Debe ingresar el numero de documento")
	private String numerodocumento;
	
	/**
	 * banco selecionado
	 */
	private String bancoSeleccionado;
	
	/**
	 * lista de los bancos registrados
	 */
	private List<Bank> bancos;
	
	/**
	 * numero de la cuenta a asociar
	 */
	@NotNull(message="Debe ingresar el numero de la cuenta")
	private String numerocuenta;
	
	/**
	 * nombre de la cuenta a asociar
	 */
	@NotNull(message="Debe ingresar el nombre de la cuenta")
	private String nombrecuenta;
	
	/**
	 * EJB del banco
	 */
	@EJB
	private BankEJB bankEJB;

	
	@PostConstruct
	public void inicializar(){
		bancos = bankEJB.listarBancos();
	}
	
	public void asociar(){
		CuentaAsociada ca = new CuentaAsociada();
		ca.setOwnerName(nombretitular);
		ca.setOwnerTypeId(tipodocumento);
		ca.setOwnerNumId(numerodocumento);
		Bank b = bankEJB.buscar(bancoSeleccionado);
	}
	
	public void cancelar(){
		
	}
	
	
	
	public String getNombretitular() {
		return nombretitular;
	}

	public void setNombretitular(String nombretitular) {
		this.nombretitular = nombretitular;
	}

	public String getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getNumerodocumento() {
		return numerodocumento;
	}

	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}

	public String getBancoSeleccionado() {
		return bancoSeleccionado;
	}

	public void setBancoSeleccionado(String bancoSeleccionado) {
		this.bancoSeleccionado = bancoSeleccionado;
	}

	public List<Bank> getBancos() {
		return bancos;
	}

	public void setBancos(List<Bank> bancos) {
		this.bancos = bancos;
	}

	public String getNumerocuenta() {
		return numerocuenta;
	}

	public void setNumerocuenta(String numerocuenta) {
		this.numerocuenta = numerocuenta;
	}

	public String getNombrecuenta() {
		return nombrecuenta;
	}

	public void setNombrecuenta(String nombrecuenta) {
		this.nombrecuenta = nombrecuenta;
	}
	
	
}
