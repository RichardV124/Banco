package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.BindingProvider;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SegundaClave;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ISegundaClaveRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.pa.clientews.Mail;
import co.edu.eam.pa.clientews.Notificaciones;
import co.edu.eam.pa.clientews.NotificacionesService;
import co.edu.eam.pa.clientews.RespuestaNotificacion;
import co.edu.eam.pa.clientews.Sms;


@LocalBean
@Stateless
@Remote(ISegundaClaveRemote.class)
public class SegundaClaveEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * metodo para crea una segunda clave
	 * @param sc segunda clave
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(SegundaClave sc,Customer c){
		//String clave = generarClave();
		sc.setClave(sc.getClave());
		Date fechaGeneracion = fechaGeneracion();
		sc.setFechaGeneracion(fechaGeneracion);
		Date fechaVencimiento = fechaVencimiento();
		sc.setFechaVencimiento(fechaVencimiento);
		sc.setCustomer(c);
		
		SegundaClave s = buscar(sc.getClave());
		if(s==null){
			em.persist(sc);
		}else{
			throw new ExcepcionNegocio("Ya existe la segunda Clave");
		}
	}
	
	/**
	 * metodo para buscar una segunda clave
	 * @param clave 
	 * @retur null si no encuentra la segunda clave, de lo contrario la retorna
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public SegundaClave buscar(String clave){
		return em.find(SegundaClave.class, clave);
	}
	
	/**
	 * metodo para generar la clave
	 * @return clave generada
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String generarClave(){
		Long codigo = ThreadLocalRandom.current().nextLong(100000L, 999999l);
		System.out.println(codigo);
		String clave = codigo.toString();
		return clave;
	}
	
	/**
	 * metodo para capturar la fecha actual
	 * @return fecha de generacion
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private Date fechaGeneracion(){
		Calendar calendar = Calendar.getInstance();
		Date fechaGeneracion = calendar.getTime();
		System.out.println(fechaGeneracion);
		return fechaGeneracion;
	}
	
	/**
	 * metodo para calcular la fecha de vencimiento
	 * @return fecha de vencimiento
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private Date fechaVencimiento(){
		Calendar calendar = Calendar.getInstance();
		Date fechaVencimiento = calendar.getTime();
		fechaVencimiento.setMinutes(fechaVencimiento.getMinutes() + 5);
		System.out.println(fechaVencimiento);
		return fechaVencimiento;
	}
	
	public void enviarSms(String claveGenerada,String numeroTelefono){
		
		NotificacionesService cliente = new NotificacionesService();
		Notificaciones servicio = cliente.getNotificacionesPort();

		String endpointURL = "http://104.197.238.134:8080/notificaciones/notificacionesService";
		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);

		Sms msj = new Sms();
		// Mensaje
		msj.setTexto("Su codigo de verificacion es: " + claveGenerada + "\n \nSu codigo expirara en 90 minutos");
		// A quien se le envia
		msj.setTo(numeroTelefono);
		
		RespuestaNotificacion resp = servicio.enviarSMS(msj);
		System.out.println(resp.getMensaje());
		
	}
	
	public void enviarEmail(String claveGenerada,String email){
		NotificacionesService cliente = new NotificacionesService();
		Notificaciones servicio = cliente.getNotificacionesPort();

		String endpointURL = "http://104.197.238.134:8080/notificaciones/notificacionesService";
		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);

		Mail correo = new Mail();
		// Mensaje
		correo.setBody("Su codigo de verificacion es: " + claveGenerada + "\n \nSu codigo expirara en 90 minutos");
		//
		correo.setFrom("idontknow@eam.edu.co");
		// A quien se le envia
		correo.setTo(email);
		// Asunto
		correo.setSubject("Codigo de verificacion BANCO TRIVINIERS");
		RespuestaNotificacion resp = servicio.enviarMail(correo);
		System.out.println(resp.getMensaje());
	}
}
