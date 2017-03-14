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

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Franchise;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.IFranchiseRemote;


@LocalBean
@Stateless
@Remote(IFranchiseRemote.class)
public class FranchiseEJB {

	
	@PersistenceContext
	private EntityManager em;
	
	/**
     * Método con la lógica para listar todas las franquicias registradas
     * @throws Exception
     */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Franchise> listarFranquicia() throws Exception {
		
		Query query = em.createNamedQuery(Franchise.CONSULTA_LISTAR_FRANQUICIAS);
		return query.getResultList();
	}
	
}
