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

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SegundaClave;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ISegundaClaveRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;


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
	public void crear(SegundaClave sc){
		String clave = generarClave();
		sc.setClave(clave);
		Date fechaGeneracion = fechaGeneracion();
		sc.setFechaGeneracion(fechaGeneracion);
		Date fechaVencimiento = fechaVencimiento();
		sc.setFechaVencimiento(fechaVencimiento);
		
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
		fechaVencimiento.setMinutes(fechaVencimiento.getMinutes() + 90);
		return fechaVencimiento;
	}
}
