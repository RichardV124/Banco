package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import javax.xml.ws.BindingProvider;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Bank;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.IBankRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa.negocio.serviciosinterbancariosws.Banco;
import co.edu.eam.ingesoft.pa.negocio.serviciosinterbancariosws.InterbancarioWS;
import co.edu.eam.ingesoft.pa.negocio.serviciosinterbancariosws.InterbancarioWS_Service;
import co.edu.eam.ingesoft.pa.negocio.serviciosinterbancariosws.ListarBancosResponse;
import co.edu.eam.ingesoft.pa.negocio.serviciosinterbancariosws.RespuestaServicio;

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
	public List<Bank> listarBancos() {
		Query query = em.createNamedQuery(Bank.CONSULTA_LISTAR_BANCOS);
		List<Bank> bancos = query.getResultList();
		if (bancos.isEmpty()) {
			throw new ExcepcionNegocio("No hay bancos registrados en la base de datos");
		} else {
			return bancos;
		}
	}

	/**
	 * metodo para listar los bancos registrados
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Bank> listarBancosWS() {

		InterbancarioWS_Service cliente = new InterbancarioWS_Service();
		InterbancarioWS servicio = cliente.getInterbancarioWSPort();

		String endpoint = "http://104.198.67.149:8080/interbancario/InterbancarioWS/InterbancarioWS";

		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

		ArrayList<Banco> resp = (ArrayList<Banco>) servicio.listarBancos();

		List<Bank> banquitos = new ArrayList<Bank>();

		for (Banco banco : resp) {

			Bank b = new Bank(banco.getCodigo(), banco.getNombre());
			banquitos.add(b);
			Bank buscado = em.find(Bank.class, b.getId());

			if (buscado == null) {

				crearBanco(b);

			}

		}
		return banquitos;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearBanco(Bank b) {
		em.persist(b);
	}

	/**
	 * Metodo para buscar un banco
	 * 
	 * @param id,
	 *            identificador del banco
	 * @return el banco si lo encuentra
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Bank buscar(String id) {
		Bank b = em.find(Bank.class, id);
		if (b == null) {
			throw new ExcepcionNegocio("El banco no está registrado");
		} else {
			return b;
		}
	}
}
