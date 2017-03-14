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

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Franchise;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Transaction;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ISavingAccountRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
@Remote(ISavingAccountRemote.class)
public class SavingAccountEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * MEtodo para crear una cuenta de ahorros...
	 * 
	 * @param saving
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearSavingAccount(SavingAccount saving) {
		Date fechaExpedicion = fechaExpedicion();
		saving.setExpeditionDate(fechaExpedicion);
		saving.setNumber(generarCodigo());
		SavingAccount busc = buscarSavingAccount(saving.getNumber());
		// no existe, se puede crear...
		if (busc == null) {
			em.persist(saving);
		} else {
			throw new ExcepcionNegocio("Ya existe la cuenta de ahorros");
		}

	}

	/**
	 * Metodo para consignar saldo a una cuenta de ahorros
	 * 
	 * @param sav,
	 *            cuenta de ahorros a la cual se le va a consignar el saldo
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void consignarMontoCuenta(SavingAccount sav, double monto) {
		sav.setAmmount(sav.getAmmount() + monto);
		em.merge(sav);
		crearTransaction(sav, monto);
	}

	/**
	 * Metodo para retirar saldo de una cuenta de ahorros
	 * 
	 * @param sav
	 *            cuenta de ahorros de la cual se va a retirar el saldo
	 * @param monto
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void retirarMontoCuenta(SavingAccount sav, double monto) {
		if (sav.getAmmount() < monto) {
			throw new ExcepcionNegocio("Esta cuenta no tiene suficiente saldo.");
		} else {
			sav.setAmmount(sav.getAmmount() - monto);
			em.merge(sav);
			crearTransaction(sav, monto);
		}
	}

	/**
	 * Metodo para transferir saldo de una cuenta de ahorros a otra del mismo
	 * cliente
	 * 
	 * @param savSalida
	 *            cuenta de ahorros de la cual se va a retirar el saldo a
	 *            transferir * @param savEntrada cuenta de ahorros a la cual se
	 *            le va a transferir el saldo
	 * @param monto,
	 *            cantidad de saldo que se va a transferir
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void transladarMontoCuenta(SavingAccount savSalida, SavingAccount savEntrada, double monto) {
		if (savSalida.getAmmount() < monto) {
			throw new ExcepcionNegocio("Esta cuenta no tiene suficiente saldo para transferir");
		} else {
			savSalida.setAmmount(savSalida.getAmmount() - monto);
			savEntrada.setAmmount(savEntrada.getAmmount() + monto);
			em.merge(savEntrada);
			em.merge(savSalida);
			crearTransaction(savEntrada, monto);
		}
	}

	/**
	 * Método con la lógica para listar todas las cuentas de ahorros de un
	 * cliente
	 * 
	 * @throws Exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<SavingAccount> listarCuentasCliente(Customer c) throws Exception {

		Query query = em.createNamedQuery(SavingAccount.CONSULTA_LISTAR_CUENTAS_CUSTOMER);
		query.setParameter(1, c);
		List<SavingAccount> sav = query.getResultList();
		if (sav.isEmpty()) {
			throw new ExcepcionNegocio("Este cliente no tiene ninguna cuenta de ahorros");
		} else {
			return sav;
		}

	}

	/**
	 * MEtodo para buscar una cuenta de ahorros.
	 * 
	 * @param numero
	 *            de la cuenta de ahorros
	 * @return la cuenta de ahorros.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public SavingAccount buscarSavingAccount(String num) {
		return em.find(SavingAccount.class, num);
	}

	/**
	 * Metodo que captura la fecha actual del momento en que se creo la tarjeta
	 * de credito
	 * 
	 * @return
	 */
	public Date fechaExpedicion() {
		Calendar calendar = Calendar.getInstance();
		Date fecha = calendar.getTime();
		return fecha;
	}

	/**
	 * Metodo que genera un numero random de 10 digitos para la tarjeta de
	 * credito con un numero de verificacion
	 * 
	 * @return
	 */
	public String generarCodigo() {
		Long numero = ThreadLocalRandom.current().nextLong(1000000000L, 9999999999L);
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

	public void crearTransaction(SavingAccount sav, double monto) {
		Transaction t = new Transaction();
		t.setSavingAccNumber(sav);
		t.setSourceTransact("Caja");
		t.setAmmount(monto);
		t.setTransactionDate(new Date());
		em.persist(t);
	}
}
