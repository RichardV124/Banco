package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SegundaClave;

public interface ISegundaClaveRemote {

	public void crear(SegundaClave sc);
	public SegundaClave buscar(String clave);
}
