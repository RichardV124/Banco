package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import java.util.List;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Transaction;


/**
 * Interface remota para acceder a las operaciones del EJB.
 * @author Richard Vanegas
 *
 */
public interface ISavingAccountRemote {

	public void crearSavingAccount(SavingAccount saving);
	public SavingAccount buscarSavingAccount(String num);
	public List<SavingAccount> listarCuentasCliente(Customer c);
	public void consignarMontoCuenta(SavingAccount sav,double monto);
	public void retirarMontoCuenta(SavingAccount sav,double monto);
	public void transladarMontoCuenta(SavingAccount savSalida,SavingAccount savEntrada,double monto);
	public void crearTransaccion(Transaction t);
	
}
