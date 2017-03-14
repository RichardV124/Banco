package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICreditCardRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
@Remote(ICreditCardRemote.class)
public class CreditCardEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * MEtodo para crear un creditcard...
	 * 
	 * @param creditCard
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCreditCard(CreditCard credit) {
		Date fechaExpedicion = fechaExpedicion();
		credit.setExpeditionDate(fechaExpedicion);
		Date fechaExpiracion = fechaExpiracion();
		credit.setExpirationDate(fechaExpiracion);
		credit.setNumber(generarNumero());
		credit.setCvc(generarCvc());
		CreditCard busc = buscarCreditCard(credit.getNumber());
		// no existe, se puede crear...
		if (busc == null) {
			em.persist(credit);
		} else {
			throw new ExcepcionNegocio("Ya existe la credit card");
		}

	}
	
	/**
	 * MEtodo para editar un creditcard...
	 * 
	 * @param creditCard
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarCreditCard(CreditCard credit) {
		CreditCard busc = buscarCreditCard(credit.getNumber());
		// no existe, se puede crear...
		if (busc != null) {
			em.merge(credit);
		} else {
			throw new ExcepcionNegocio("No existe la tarjeta de credito a editar");
		}

	}

	/**
	 * MEtodo para buscar una credit card.
	 * 
	 * @param numero
	 *            de la credit card
	 * @return la creditcard.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CreditCard buscarCreditCard(String num) {
		return em.find(CreditCard.class, num);
	}

	/**
	 * Metodo que captura la fecha actual del momento en que se creo la tarjeta
	 * de credito
	 * 
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Date fechaExpedicion() {
		Calendar calendar = Calendar.getInstance();
		Date fecha = calendar.getTime();
		return fecha;
	}

	/**
	 * Metodo que asigna la fecha de vencimiento de la tarjeta de credito
	 * 
	 * @return
	 */
	public Date fechaExpiracion() {
		Calendar calendar = Calendar.getInstance();
		Date fecha = calendar.getTime();
		fecha.setYear(fecha.getYear() + 4);
		return fecha;
	}

	/**
	 * Metodo que genera un numero random de 16 digitos para la tarjeta de
	 * credito
	 * 
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String generarNumero() {
		Long numero = ThreadLocalRandom.current().nextLong(100000000000000L, 999999999999999l);
		System.out.println(numero);
		String cadena = numero.toString();
		int ultimo = 1;
		int digito = 0;
		for (int i = 0; i < cadena.length(); i++) {

			char letra = cadena.charAt(i);
			if (Character.isDigit(letra)) {
				digito = Integer.parseInt(String.valueOf(cadena.charAt(i)));

				ultimo *= digito;
			}
		}
		String fin = ultimo + "";
		cadena = cadena + fin.charAt(fin.length() - 1);
		return cadena;
	}

	/**
	 * Metodo que genera un numero random de 3 digitos el cual sera cvc
	 * 
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String generarCvc() {
		Long numero = ThreadLocalRandom.current().nextLong(100L, 999L);
		String cadena = numero.toString();
		return cadena;
	}
	
	/**
	 * Método con la lógica para listar todas las tarjetas de credito de un
	 * cliente
	 * 
	 * @throws Exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CreditCard> listarTarjetasCliente(Customer c) throws Exception {

		Query query = em.createNamedQuery(CreditCard.CONSULTA_LISTAR_TARJETAS_CUSTOMER);
		query.setParameter(1, c);
		List<CreditCard> cred = query.getResultList();
		if (cred.isEmpty()) {
			throw new ExcepcionNegocio("Este cliente no tiene ninguna tarjeta de credito");
		} else {
			return cred;
		}

	}
	
	/**
	 * MEtodo para editar el saldo consumo de la tarjeta
	 * 
	 * @param creditCard
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarCreditCard(CreditCard cc,double consumo){
		
		cc.setAmmountConsumed(cc.getAmmountConsumed()+consumo);
		em.merge(cc);
	}
}
