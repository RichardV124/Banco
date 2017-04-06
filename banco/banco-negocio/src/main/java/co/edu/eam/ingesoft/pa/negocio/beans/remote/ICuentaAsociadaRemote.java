package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import java.util.List;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociada;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;

public interface ICuentaAsociadaRemote {
	
	public List<CuentaAsociada> listarCuentasAsociadasValidadas(Customer cus);
	public List<CuentaAsociada> listarCuentasAsociadas(Customer cus);
}
