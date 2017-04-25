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

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Bank;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociada;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.BankEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa.negocio.serviciosinterbancariosws.Banco;

@Named("cuentaAsociadaController")
@ViewScoped
public class CuentaAsociadaControllerAjax implements Serializable {

	/**
	 * Nombre del Titular
	 */
	@NotNull(message = "Debe ingresar el nombre del titular")
	private String nombretitular;

	/**
	 * Tipo de documento del customer
	 */
	@NotNull(message = "Debe seleccionar el tipo de documento")
	private String tipodocumento;

	/**
	 * Numero de identificacion del titular
	 */
	@NotNull(message = "Debe ingresar el numero de documento")
	private String numerodocumento;

	/**
	 * banco selecionado
	 */
	private String bancoSeleccionado;

//	/**
//	 * lista de los bancos registrados
//	 */
//	private List<Banco> bancos;
	
	/**
	 * lista de los bancos registrados
	 */
	private List<Bank> bancos;

	/**
	 * numero de la cuenta a asociar
	 */
	@NotNull(message = "Debe ingresar el numero de la cuenta")
	private String numerocuenta;

	/**
	 * nombre de la cuenta a asociar
	 */
	@NotNull(message = "Debe ingresar el nombre de la cuenta")
	private String nombrecuenta;

	/**
	 * Listado de las cuentas asociadas
	 */
	private List<CuentaAsociada> cuentasAsociadas;

	/**
	 * EJB del banco
	 */
	@EJB
	private BankEJB bankEJB;

	/**
	 * EJB de la cuenta asociada
	 */
	@EJB
	private CuentaAsociadaEJB cuentaAsociadaEJB;

	@PostConstruct
	public void inicializar() {
		try {
			bancos = bankEJB.listarBancosWS();
			actualizarListadoCuentas();
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para asociarle una cuenta al cliente logeado
	 */
	public void asociar() {

		CuentaAsociada ca = new CuentaAsociada();
		ca.setOwnerName(nombretitular);
		ca.setOwnerTypeId(tipodocumento);
		ca.setOwnerNumId(numerodocumento);
		Bank b = bankEJB.buscar(bancoSeleccionado);
		ca.setBank(b);
		ca.setNumber(numerocuenta);
		ca.setName(nombrecuenta);
		Usuario usuario = Faces.getSessionAttribute("user");
		ca.setCustomer(usuario.getCustomer());
		cuentaAsociadaEJB.crear(ca);

		// limpiar campos del formulario
		nombretitular = "";
		tipodocumento = null;
		numerodocumento = "";
		bancoSeleccionado = null;
		numerocuenta = "";
		nombrecuenta = "";

		// ActualizarTabla
		actualizarListadoCuentas();
	}

	public void cancelar() {

	}

	/**
	 * metodo para eliminar una cuenta asociada del cliente
	 * 
	 * @param cuenta,
	 *            cuenta asociada
	 */
	public void eliminarCuenta(CuentaAsociada cuenta) {
		// int id = cuenta.getId();
		// CuentaAsociada ca = cuentaAsociadaEJB.buscar(id);
		cuentaAsociadaEJB.eliminar(cuenta.getId());
		Messages.addFlashGlobalInfo("Se ha eliminado la cuenta asociada con exito!");
		actualizarListadoCuentas();
	}

	/**
	 * metodo para actualizar la tabla con el listado de cuentas asociadas
	 */
	public void actualizarListadoCuentas() {
		try {
			Usuario usuario = Faces.getSessionAttribute("user");
			cuentasAsociadas = cuentaAsociadaEJB.listarCuentas(usuario.getCustomer());
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	}

	public void verificarCuenta (CuentaAsociada cuenta){	
		try {
			cuentaAsociadaEJB.verificarCuenta(cuenta);
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
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

	public List<CuentaAsociada> getCuentasAsociadas() {
		return cuentasAsociadas;
	}

	public void setCuentasAsociadas(List<CuentaAsociada> cuentasAsociadas) {
		this.cuentasAsociadas = cuentasAsociadas;
	}

	public BankEJB getBankEJB() {
		return bankEJB;
	}

	public void setBankEJB(BankEJB bankEJB) {
		this.bankEJB = bankEJB;
	}

	public CuentaAsociadaEJB getCuentaAsociadaEJB() {
		return cuentaAsociadaEJB;
	}

	public void setCuentaAsociadaEJB(CuentaAsociadaEJB cuentaAsociadaEJB) {
		this.cuentaAsociadaEJB = cuentaAsociadaEJB;
	}

}
