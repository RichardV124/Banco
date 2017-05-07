package co.edu.eam.ingesoft.pa.banco.web.servicios;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SeguridadEJB;
import co.edu.eam.ingesoft.pa.negocio.dto.CustomerDTO;
import co.edu.eam.ingesoft.pa.negocio.dto.LoginDTO;

//para invocar un servicio se necesita:
/*
* 1. la url del servicio: http://ip:puerto/<root>/<raizRest>/<pathclase>/<pathmetodo>
*/
//<pathclase>=usuario
@Path("/login")
public class LoginRest {

	/**
	 * EJB de la segunda clave
	 */
	@EJB
	private CustomerEJB customerEJB;

	@EJB
	private SeguridadEJB segEJB;

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO login(LoginDTO dto) {

		Usuario usuario = segEJB.buscarUsuario(dto.getUser());
		System.out.println(usuario.getCustomer().getName() + " JAJSJASJ");
		if (usuario != null) {
			System.out.println(usuario.getPassword().equals(dto.getPassword()) + " GG");
			if (usuario.getPassword().equals(dto.getPassword())) {
				Customer cus = usuario.getCustomer();
				cus.setProductos(null);
				return new RespuestaDTO("Se encontro!", 0, cus);
			} else {
				return new RespuestaDTO("Usuario o Pass incorrecto", 1, null);
			}
		}

		return new RespuestaDTO("Usuario o Pass incorrecto", 1, null);

	}

//	@POST
//	@Path("/login2")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public RespuestaDTO login2(UsuarioDTO dto) {
//
//		Usuario usuario = segEJB.buscarUsuario(dto.getUser());
//		System.out.println(usuario.getCustomer().getName() + " JAJSJASJ");
//		if (usuario != null) {
//			System.out.println(usuario.getPassword().equals(dto.getPassword()) + " GG");
//			if (usuario.getPassword().equals(dto.getPassword())) {
//				CustomerDTO cusDto = new CustomerDTO();
//				cusDto.setIdType(usuario.getCustomer().getIdType());
//				cusDto.setIdNum(usuario.getCustomer().getIdNum());
//				cusDto.setName(usuario.getCustomer().getName());
//				cusDto.setLastName(usuario.getCustomer().getLastName());
//				cusDto.setEmail(usuario.getCustomer().getEmail());
//				cusDto.setTelefono(usuario.getCustomer().getNumberPhone());
//
//				return new RespuestaDTO("Se encontro!", 0, cusDto);
//			} else {
//				return new RespuestaDTO("Usuario o Pass incorrecto", 1, null);
//			}
//		}
//
//		return new RespuestaDTO("Usuario o Pass incorrecto", 1, null);
//
//	}

	@POST
	@Path("/login2")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO login2(@FormParam("user") String usu,@FormParam("password") String password) {

		Usuario usuario = segEJB.buscarUsuario(usu);
		System.out.println(usuario.getCustomer().getName() + " JAJSJASJ");
		if (usuario != null) {
			System.out.println(usuario.getPassword().equals(password) + " GG");
			if (usuario.getPassword().equals(password)) {
				CustomerDTO cusDto = new CustomerDTO();
				cusDto.setIdType(usuario.getCustomer().getIdType());
				cusDto.setIdNum(usuario.getCustomer().getIdNum());
				cusDto.setName(usuario.getCustomer().getName());
				cusDto.setLastName(usuario.getCustomer().getLastName());
				cusDto.setEmail(usuario.getCustomer().getEmail());
				cusDto.setTelefono(usuario.getCustomer().getNumberPhone());

				return new RespuestaDTO("Se encontro!", 0, cusDto);
			} else {
				return new RespuestaDTO("Usuario o Pass incorrecto", 1, null);
			}
		}

		return new RespuestaDTO("Usuario o Pass incorrecto", 1, null);

	}
	
}
