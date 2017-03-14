package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
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
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardPaymentConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Transaction;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICreditCardPaymentConsumeRemote;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
@Remote(ICreditCardPaymentConsumeRemote.class)
public class CreditCardPaymentConsumeEJB {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private CreditCardEJB creditCardEJB;
	@EJB
	private SavingAccountEJB savingAccountEJB;

	@EJB
	private CreditCardConsumeEJB consumeEJB;

	/**
	 * Metodo que captura la fecha actual del momento en que se pago una cuota
	 * del consumo de la tarjeta de credito
	 * 
	 * @return
	 */
	public Date fechaPago() {
		Calendar calendar = Calendar.getInstance();
		Date fecha = calendar.getTime();
		return fecha;
	}

	/**
	 * Registra el pago de los consumos de una tarjeta
	 * 
	 * @param numeroTarjeta
	 *            Número de la tarjeta
	 * @throws Exception
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCreditCardPayment(String numeroTarjeta, double adicionar) throws Exception {

		CreditCard tarjeta = creditCardEJB.buscarCreditCard(numeroTarjeta);
		List<CreditCardConsume> listaConsumos = consumeEJB.listarConsumosTarjeta(tarjeta);

		double cuotaTotal = calcularCuotaTarjeta(tarjeta);
		double sumaConsumos = 0;

		for (CreditCardConsume consumo : listaConsumos) {
			double remaining = consumo.getRemainingAmmount();
			double intereses = (remaining / consumo.getNumberShares()) * 0.036;
			double capital = remaining / consumo.getNumberShares();
			sumaConsumos += remaining + intereses + capital;
		}

		double parteAdicionada = adicionar / listaConsumos.size();

		if (cuotaTotal + adicionar <= sumaConsumos) {

			for (CreditCardConsume consumo : listaConsumos) {

				if (!consumo.isPayed()) {

					CreditCardPaymentConsume pago = new CreditCardPaymentConsume();
					int cuotas = consumo.getNumberShares();
					double remaining = consumo.getRemainingAmmount();
					double parteConsumo = parteAdicionada + remaining / cuotas;

					List<CreditCardPaymentConsume> pagosConsumo = listaPagosConsumos(consumo);
					if (pagosConsumo.size() == 0) {
						pago.setShare(1);
					} else {
						CreditCardPaymentConsume ultimoPago = pagosConsumo.get(pagosConsumo.size() - 1);
						pago.setShare(ultimoPago.getShare() + 1);
					}

					if (remaining - parteConsumo <= 0) {
						double sobrante = parteConsumo - remaining;
						adicionar += sobrante;
						consumo.setRemainingAmmount(0);
					}

					if (consumo.getRemainingAmmount() == 0) {
						consumo.setPayed(true);
					} else {
						double suma = parteAdicionada + remaining / cuotas;
						consumo.setRemainingAmmount(remaining - suma);
						consumo.setNumberShares(consumo.getNumberShares()-1);
					}
					double interes = (remaining / cuotas) * 0.036;
					double pagar = interes + remaining / cuotas;
					pago.setAmmount(pagar);
					pago.setCapitalAmmount(remaining / cuotas);
					pago.setIdConsume(consumo);
					pago.setInterestAmmount(interes);
					Date fecha = new Date();
					pago.setPaymentDate(fecha);
					em.persist(pago);
					em.merge(consumo);
				}
			}
		} else {
			throw new ExcepcionNegocio("La cantidad que desea pagar es mayor de lo que debe");
		}
	}

	/**
	 * Registra el pago de los consumos de una tarjeta
	 * 
	 * @param numeroTarjeta
	 *            Número de la tarjeta
	 * @throws Exception
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCreditCardPaymentCuentaAhorros(String numeroTarjeta, double adicionar, String numeroCuenta)
			throws Exception {

		CreditCard tarjeta = creditCardEJB.buscarCreditCard(numeroTarjeta);
		SavingAccount cuenta = savingAccountEJB.buscarSavingAccount(numeroCuenta);
		List<CreditCardConsume> listaConsumos = consumeEJB.listarConsumosTarjeta(tarjeta);

		double cuotaTotal = calcularCuotaTarjeta(tarjeta);
		double sumaConsumos = 0;

		for (CreditCardConsume consumo : listaConsumos) {
			double remaining = consumo.getRemainingAmmount();
			double intereses = (remaining / consumo.getNumberShares()) * 0.036;
			double capital = remaining / consumo.getNumberShares();
			sumaConsumos += remaining + intereses + capital;
		}

		double parteAdicionada = adicionar / listaConsumos.size();

		if (cuotaTotal + adicionar <= sumaConsumos) {

			for (CreditCardConsume consumo : listaConsumos) {

				if (!consumo.isPayed()) {

					CreditCardPaymentConsume pago = new CreditCardPaymentConsume();
					int cuotas = consumo.getNumberShares();
					double remaining = consumo.getRemainingAmmount();
					double parteConsumo = parteAdicionada + remaining / cuotas;

					List<CreditCardPaymentConsume> pagosConsumo = listaPagosConsumos(consumo);
					if (pagosConsumo.size() == 0) {
						pago.setShare(1);
					} else {
						CreditCardPaymentConsume ultimoPago = pagosConsumo.get(pagosConsumo.size() - 1);
						pago.setShare(ultimoPago.getShare() + 1);
					}

					if (remaining - parteConsumo <= 0) {
						double sobrante = parteConsumo - remaining;
						adicionar += sobrante;
						consumo.setRemainingAmmount(0);
					}

					if (consumo.getRemainingAmmount() == 0) {
						consumo.setPayed(true);
					} else {
						double suma = parteAdicionada + remaining / cuotas;
						consumo.setRemainingAmmount(remaining - suma);
						consumo.setNumberShares(consumo.getNumberShares()-1);
					}
					double interes = (remaining / cuotas) * 0.036;
					double pagar = interes + remaining / cuotas;
					if (cuenta.getAmmount() > pagar) {

						cuenta.setAmmount(cuenta.getAmmount() - pagar);
						pago.setAmmount(pagar);
						pago.setCapitalAmmount(remaining / cuotas);
						pago.setIdConsume(consumo);
						pago.setInterestAmmount(interes);
						Date fecha = new Date();
						pago.setPaymentDate(fecha);
						em.persist(pago);
						em.merge(consumo);
						em.merge(cuenta);
						savingAccountEJB.crearTransaction(cuenta, pagar);

					} else {
						throw new ExcepcionNegocio(
								"La cuenta de ahorros no tiene saldo suficiente para realizar el pago");
					}
				}
			}
		} else {
			throw new ExcepcionNegocio("La cantidad que desea pagar es mayor de lo que debe");
		}
	}

	/**
	 * Calcula la cuota que se debe pagar en un tarjeta de crédito
	 * 
	 * @param cc
	 *            tarjeta de crédito
	 * @return la cuota de la tarjeta
	 * @throws Exception
	 */
	public double calcularCuotaTarjeta(CreditCard cc) throws Exception {
		double cuota = 0;
		List<CreditCardConsume> transacciones = consumeEJB.listarConsumosTarjeta(cc);
		if (transacciones.size() == 0) {
			return 0;
		}
		for (CreditCardConsume consumo : transacciones) {
			double monto = consumo.getRemainingAmmount();
			double interes = (monto / consumo.getNumberShares()) * 0.036;
			double sinInteres = monto / consumo.getNumberShares();
			cuota = cuota + interes + sinInteres;
		}
		return cuota;
	}

	/**
	 * Obtiene la lista de pagos de un consumo
	 * 
	 * @param c
	 *            el consumo
	 * @return la lista de pagos del consumo
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CreditCardPaymentConsume> listaPagosConsumos(CreditCardConsume c) {
		Query q = em.createNamedQuery(CreditCardPaymentConsume.PAGOS_CONSUMOS);
		q.setParameter(1, c);
		List<CreditCardPaymentConsume> lista = q.getResultList();
		return lista;
	}

	/**
	 * Registra el pago de los consumos de una tarjeta
	 * 
	 * @param con
	 *            Cosumo que se pagara
	 * @throws Exception
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void pagarUnConsumo(CreditCardConsume con) {
		con.setPayed(true);
		// FALTA EDITAR EL SALDO USADO DE LA TARJETA DE CREDITO
		em.merge(con);
	}

}
