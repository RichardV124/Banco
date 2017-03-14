package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardConsume;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICreditCardConsumeRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
@Remote(ICreditCardConsumeRemote.class)
public class CreditCardConsumeEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * MEtodo para crear un consumo de la tarjeta de credito
	 * 
	 * @param creditCard
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCreditCardConsume(CreditCardConsume credit) throws Exception {

		if(credit.getAmmount()+credit.getCreditCard().getAmmountConsumed()>credit.getCreditCard().getAmmount()){
			throw new ExcepcionNegocio("La tarjeta no tiene el cupo suficiente para cubrir este consumo");
		}
		credit.setDateConsume(fechaConsumo());
			em.persist(credit);
	}

	/**
	 * Método con la lógica para listar todos los consumos de una tarjeta
	 * 
	 * @throws Exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CreditCardConsume> listarConsumosTarjeta(CreditCard credit) throws Exception {
		Query query = em.createNamedQuery(CreditCardConsume.CONSULTA_LISTAR_CONSUMOS_TARJETA);
		query.setParameter(1, credit);
		List<CreditCardConsume> lista = query.getResultList();
		if (lista.isEmpty()) {
			throw new ExcepcionNegocio("Esta tarjeta no tiene ningun consumo");
		} else {
			return lista;
		}

	}

	/**
	 * Metodo que captura la fecha actual del momento en que se creo el consumo
	 * de la tarjeta de credito
	 * 
	 * @return
	 */
	public Date fechaConsumo() {
		Calendar calendar = Calendar.getInstance();
		Date fecha = calendar.getTime();
		return fecha;
	}

}
