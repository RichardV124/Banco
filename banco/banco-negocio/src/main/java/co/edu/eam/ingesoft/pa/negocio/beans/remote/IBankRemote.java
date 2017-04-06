package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import java.util.List;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Bank;
import co.edu.eam.ingesoft.pa.negocio.serviciosinterbancariosws.Banco;

public interface IBankRemote {

	public List<Bank> listarBancos();
//	public List<Banco> listarBancos();
	public Bank buscar(String id);
}
