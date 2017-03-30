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

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Bank;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.IBankRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
@Remote(IBankRemote.class)
public class BankEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * metodo para listar los bancos registrados
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Bank> listarBancos(){
		Query query = em.createNamedQuery(Bank.CONSULTA_LISTAR_BANCOS);
		List<Bank> bancos = query.getResultList(); 
		if (bancos.isEmpty()) {
			throw new ExcepcionNegocio("No hay bancos registrados en la base de datos");
		} else {
			return bancos;
		}	
	}
	
	public Bank buscar (String id){
		Bank b = em.find(Bank.class, id);
		if(b==null){
			throw new ExcepcionNegocio("El banco no está registrado");
		}else{
			return b;
		}
	}
}
