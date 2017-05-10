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

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CustomerPK;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICustomerRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;


@LocalBean
@Stateless
@Remote(ICustomerRemote.class)
public class CustomerEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * MEtodo para crear un Customer...
	 * @param cus
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCustomer(Customer cus){
		Customer busc=buscarCustomer(cus.getIdType(),cus.getIdNum());
		//no existe, se puede crear...
		if(busc==null){
			em.persist(cus);
		}else{
			throw new ExcepcionNegocio("Ya existe el Customer");
		}
		
	}
	
	/**
	 * MEtodo para buscar un Customer.
	 * @param documentoidentificacion
	 * @return el Customer.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Customer buscarCustomer(String idType,String idNum){
		CustomerPK pk = new CustomerPK();
		pk.setIdNum(idNum);
		pk.setIdType(idType);
		Customer cus = em.find(Customer.class, pk);
		if(cus!=null){
			return cus;
		}
		return null;
	}

	
	/**
	 * MEtodo para editar un Customer...
	 * @param cus
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarCustomer(Customer cus){
		Customer busc=buscarCustomer(cus.getIdType(),cus.getIdNum());
		//no existe,no se puede editar...
		if(busc!=null){
			em.merge(cus);
		}else{
			throw new ExcepcionNegocio("No existe el customer a editar");
		}
		
	}
	
	/**
	 * metodo para listar los clientes
	 */
	public List<Customer> listarCustomers(){	
		Query query = em.createNamedQuery(Customer.CONSULTA_LISTAR_CUSTOMERS);
		List<Customer> cus = query.getResultList();
		return cus;
	}
}
