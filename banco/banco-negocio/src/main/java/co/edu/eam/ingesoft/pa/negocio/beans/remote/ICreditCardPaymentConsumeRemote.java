package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CreditCardConsume;

public interface ICreditCardPaymentConsumeRemote {

	public void crearCreditCardPayment(String credit,double adicionar);
	public void crearCreditCardPaymentCuentaAhorros(String credit,double adicionar,String numeroCuenta);
	public void pagarUnConsumo(CreditCardConsume con);
	public double calcularCuotaTarjeta (CreditCard cc) throws Exception;
}
