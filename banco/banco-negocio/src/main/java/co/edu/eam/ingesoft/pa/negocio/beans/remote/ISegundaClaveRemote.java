package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SegundaClave;

public interface ISegundaClaveRemote {

	public void crear(SegundaClave sc);
	public SegundaClave buscar(String clave);
	public void enviarEmail(String claveGenerada,String email);
	public void enviarSms(String claveGenerada,String telefono);
}
