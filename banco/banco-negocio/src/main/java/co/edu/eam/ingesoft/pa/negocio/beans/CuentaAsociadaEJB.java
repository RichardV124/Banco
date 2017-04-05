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

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociada;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICuentaAsociadaRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

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

}
