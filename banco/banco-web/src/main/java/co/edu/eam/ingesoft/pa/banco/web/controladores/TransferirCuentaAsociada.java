package co.edu.eam.ingesoft.pa.banco.web.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.xml.ws.BindingProvider;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociada;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SegundaClave;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SavingAccountEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SegundaClaveEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.pa.clientews.Mail;
import co.edu.eam.pa.clientews.Notificaciones;
import co.edu.eam.pa.clientews.NotificacionesService;
import co.edu.eam.pa.clientews.RespuestaNotificacion;

@Named("controladorTransferirAjax")
@ViewScoped
public class TransferirCuentaAsociada implements Serializable {

	/**
	 * cuenta de ahorros seleccionada
	 */
	private String asociadaSeleccionada;

	/**
	 * cuenta de ahorros seleccionada
	 */
	private String cuentaSeleccionada;

	/**
	 * cuenta de ahorros seleccionada
	 */
	private String claveGenerada;
	
	/**
	 * True si esta verificada en caso contrario false
	 */
	private boolean verificada;

	/**
	 * Monto que se va a transferir
	 */
	private double monto;

	/**
	 * Codigo de verificacion
	 */
	private String codigoverificacion;

	/**
	 * Usuario que ha iniciado sesion
	 */
	private Usuario usuario;

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

	/**
	 * EJB de la segunda clave
	 */
	@EJB
	private SegundaClaveEJB segundaClaveEJB;

	@PostConstruct
	public void inicializar() {
		try {
			usuario = Faces.getSessionAttribute("user");
			cuentas = savingAccountEJB.listarCuentasCliente(usuario.getCustomer());
			asociadas = asociadaCuentaEJB.listarCuentas(usuario.getCustomer());
			verificada = false;
		} catch (ExcepcionNegocio e1) {
			Messages.addFlashGlobalError(e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messages.addFlashGlobalInfo(e.getMessage());
			e.printStackTrace();
		}
	}

	public void generarClave() {
		claveGenerada = segundaClaveEJB.generarClave();
		SegundaClave sc = new SegundaClave();
		sc.setClave(claveGenerada);
		segundaClaveEJB.crear(sc);
		segundaClaveEJB.enviarEmail(claveGenerada, usuario.getCustomer().getEmail());
//		segundaClaveEJB.enviarSms(claveGenerada, usuario.getCustomer().getNumberPhone());
	}

	public void verificar() {
		SegundaClave sc = segundaClaveEJB.buscar(claveGenerada);
		if(sc.getFechaGeneracion().before(sc.getFechaVencimiento())){
		//if(sc.getFechaGeneracion().compareTo(sc.getFechaVencimiento())>0){
			verificada = true;
			Messages.addFlashGlobalInfo("Clave verificada");
		}else{
			Messages.addFlashGlobalInfo("Esta clave se ha vencido, porfavor genere otra clave "
					+ "para completar su transaccion");
		}
	}
	
	public void transferir() {
		try {
			savingAccountEJB.tranferenciaInterbancaria(asociadaSeleccionada, cuentaSeleccionada, monto);
			Messages.addFlashGlobalInfo("Se hizo el avance correctamente");
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
