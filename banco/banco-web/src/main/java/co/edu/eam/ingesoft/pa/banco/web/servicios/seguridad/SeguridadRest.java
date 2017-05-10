package co.edu.eam.ingesoft.pa.banco.web.servicios.seguridad;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociada;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.pa.banco.web.servicios.RespuestaDTO;
import co.edu.eam.ingesoft.pa.negocio.beans.SeguridadEJB;
import co.edu.eam.ingesoft.pa.negocio.dto.LoginDTO;
import co.edu.eam.ingesoft.pa.negocio.dto.LoginRespuestaDTO;

@Path("/seguridad")
public class SeguridadRest {

	public static Map<String, Usuario> usuarios = new HashMap<String, Usuario>();;

	@EJB
	private SeguridadEJB ejb;

	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO login(LoginDTO dto) {

		Usuario user = ejb.buscarUsuario(dto.getUser());
		if (user != null && user.getPassword().equals(dto.getPassword())) {
			String token = UUID.randomUUID().toString();
			usuarios.put(token, user);
			return new RespuestaDTO("EXITO", 0,
					new LoginRespuestaDTO(token, user.getCustomer().getIdType(),user.getCustomer().getIdNum()));
		} else {
			return new RespuestaDTO("Credenciales erroneas", -403, null);
		}

	}
	
	@Path("/login2")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO login2(@QueryParam("user") String nickname, @QueryParam("password") String password) {
		Usuario user = ejb.buscarUsuario(nickname);
		if (user != null && user.getPassword().equals(password)) {
			String token = UUID.randomUUID().toString();
			usuarios.put(token, user);
			return new RespuestaDTO("EXITO", 0,
					new LoginRespuestaDTO(token, user.getCustomer().getIdType(),user.getCustomer().getIdNum()));
		} else {
			return new RespuestaDTO("Credenciales erroneas", -403, null);
		}
	}
}