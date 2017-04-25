package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.ArrayList;
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
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Bank> listarBancosWS() {
		InterbancarioWS_Service cliente = new InterbancarioWS_Service();
		InterbancarioWS servicio = cliente.getInterbancarioWSPort();

		String endpointURL = "http://104.197.238.134:8080/interbancario/InterbancarioWS?wsdl";
		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);

		ArrayList<Banco> resp = (ArrayList<Banco>) servicio.listarBancos();
		
		List<Bank> banquitos = new ArrayList<>();
		
		for (Banco banco : resp) {
			
			Bank b = new Bank(banco.getCodigo(), banco.getNombre());
			banquitos.add(b);
			
		}
		

		return banquitos;
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
