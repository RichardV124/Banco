package co.edu.eam.ingesoft.pa.banco.web.servicios.seguridad;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import co.edu.eam.ingesoft.pa.banco.web.servicios.RespuestaDTO;

@Secured // indicar al filtro que se interceptara lo anotado con esto
@Provider
public class FiltroSeguridadRest implements ContainerRequestFilter {
	/**
	 * Camilo ferrer
	 * Metodo que hace filtro para los servicios por
	 * medio de el token saca el user y revisa si tiene algun acceso al servicio
	 */
	public void filter(ContainerRequestContext ctxReq) throws IOException {
		String token = ctxReq.getHeaderString("Authorization");
		System.out.println(token + "hh");

		// System.out.println(metod);
		// TODO: revisar el usuario del token y el permiso de acceso al servicio
		if (!SeguridadRest.usuarios.containsKey(token)) {
			RespuestaDTO dto = new RespuestaDTO("No autorizado", -403, null);
			Response res = Response.status(403).entity(dto).build();
			ctxReq.abortWith(res);
		}

	}

}
