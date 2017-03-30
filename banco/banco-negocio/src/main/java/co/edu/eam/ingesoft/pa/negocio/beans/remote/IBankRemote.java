package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import java.util.List;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Bank;

public interface IBankRemote {

	public List<Bank> listarBancos();
	public Bank buscar(String id);
}
