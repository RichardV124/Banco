package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.ws.BindingProvider;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociada;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICuentaAsociadaRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa.negocio.serviciosinterbancariosws.InterbancarioWS;
import co.edu.eam.ingesoft.pa.negocio.serviciosinterbancariosws.InterbancarioWS_Service;
import co.edu.eam.ingesoft.pa.negocio.serviciosinterbancariosws.RegistrarCuentaAsociada;
import co.edu.eam.ingesoft.pa.negocio.serviciosinterbancariosws.RespuestaServicio;
import co.edu.eam.ingesoft.pa.negocio.serviciosinterbancariosws.TipoDocumentoEnum;
import co.edu.eam.pa.clientews.Notificaciones;
import co.edu.eam.pa.clientews.NotificacionesService;
import co.edu.eam.pa.clientews.RespuestaNotificacion;
import co.edu.eam.pa.clientews.Sms;

@LocalBean
@Stateless
@Remote(ICuentaAsociadaRemote.class)
public class CuentaAsociadaEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * metodo para asociar una cuenta bancaria
	 * @param cuenta, cuenta a asociar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crear(CuentaAsociada cuenta){
		CuentaAsociada ca = buscar(cuenta.getId());
		if(ca!=null){
			throw new ExcepcionNegocio("La cuenta ya está asociada");
		}else{
			em.persist(cuenta);
		}
	}
	
	/**
	 * metodo para buscar una cuenta asociada
	 * @param id identificador de la cuenta asociada
	 * @return null si la cuenta no está registrada, de lo contrario retorna la cuenta asociada
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CuentaAsociada buscar(int id){
		CuentaAsociada ca = em.find(CuentaAsociada.class, id);
		if(ca!=null){
			return ca;
		}
		return null;
	}
	
	/**
	 * metodo para listar las cuentas asociadas de un cliente
	 * @param cus, cliente al que se le listaran las cuentas asociadas
	 * @return la lista con las cuentas que el cliente tiene asociadas
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CuentaAsociada> listarCuentas(Customer cus){
		Query query = em.createNamedQuery(CuentaAsociada.CONSULTA_LISTAR_CUENTAS_ASOCIADAS);
		query.setParameter(1, cus);
		List<CuentaAsociada> cuentas = query.getResultList();
		if (cuentas.isEmpty()) {
			throw new ExcepcionNegocio("Este cliente no tiene ninguna cuenta asociada");
		} else {
			return cuentas;
		}
	}
	
	/**
	 * metodo para listar las cuentas asociadas de un cliente
	 * @param cus, cliente al que se le listaran las cuentas asociadas
	 * @return la lista con las cuentas que el cliente tiene asociadas
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CuentaAsociada> listarCuentasAsociadasValidadas(Customer cus){
		Query query = em.createNamedQuery(CuentaAsociada.CONSULTA_LISTAR_CUENTAS_ASOCIADAS_VALIDADAS);
		query.setParameter(1, cus);
		List<CuentaAsociada> cuentas = query.getResultList();
		if (cuentas.isEmpty()) {
			throw new ExcepcionNegocio("Este cliente no tiene ninguna cuenta asociada");
		} else {
			return cuentas;
		}
	}
	
	/**
	 * metodo para eliminar una cuenta asociada
	 * @param ca, cuenta asociada a eliminar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminar(int id){
			CuentaAsociada ca = em.getReference(CuentaAsociada.class, id);
			ca.getId();
			em.remove(ca);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void verificarCuenta(CuentaAsociada cuenta){
		InterbancarioWS_Service cliente = new InterbancarioWS_Service();
		InterbancarioWS servicio = cliente.getInterbancarioWSPort();

		String endpointURL = "http://104.197.238.134:8080/interbancario/InterbancarioWS";
		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);

		RegistrarCuentaAsociada ca = new RegistrarCuentaAsociada();
		// Id banco
		ca.setIdbanco(cuenta.getBank().getId());
		// Tipo de documento
		TipoDocumentoEnum tipoDoc = null;
		tipoDoc = tipoDoc.fromValue(cuenta.getOwnerTypeId());
		ca.setTipodoc(tipoDoc);
		//numero documento
		ca.setNumerodoc(cuenta.getOwnerNumId());
		//nombre
		ca.setNombre(cuenta.getName());
		//numero cuenta
		ca.setNumerocuenta(cuenta.getNumber());
		
		RespuestaServicio resp = servicio.registrarCuentaAsociada(ca.getIdbanco(), ca.getTipodoc(), ca.getNumerodoc(), ca.getNombre(), ca.getNumerocuenta());
		
		System.out.println(resp.getMensaje());
		
		cuenta.setEstado(resp.getMensaje());
		editar(cuenta);
	}
	
	/**
	 * MEtodo para editar una cuenta asociada...
	 * @param ca
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editar(CuentaAsociada ca){
		CuentaAsociada busc=buscar(ca.getId());
		//no existe,no se puede editar...
		if(busc!=null){
			em.merge(ca);
		}else{
			throw new ExcepcionNegocio("No existe la cuenta a editar");
		}
		
	}

}
