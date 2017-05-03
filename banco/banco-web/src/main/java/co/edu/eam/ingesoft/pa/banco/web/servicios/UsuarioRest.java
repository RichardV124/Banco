package co.edu.eam.ingesoft.pa.banco.web.servicios;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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

//para invocar un servicio se necesita:
/*
* 1. la url del servicio: http://ip:puerto/<root>/<raizRest>/<pathclase>/<pathmetodo>
*/
//<pathclase>=usuario
@Path("/usuario")
public class UsuarioRest {

	/**
	 * EJB de la segunda clave
	 */
	@EJB
	private CustomerEJB customerEJB;
	
	@EJB
	private SeguridadEJB segEJB;
	
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO login(@FormParam("usuario") String user,@FormParam("contrasena") String pass) {

			Usuario usuario=segEJB.buscarUsuario(user);
			System.out.println(usuario.getCustomer().getName()+" JAJSJASJ");
			if(usuario!=null){
				System.out.println(usuario.getPassword().equals(pass)+" GG");
				if(usuario.getPassword().equals(pass)){
					Customer cus = usuario.getCustomer();
					cus.setProductos(null);
					return new RespuestaDTO("Se encontro!", 0, cus);
				}else{
					return new RespuestaDTO("Usuario o Pass incorrecto", 1, null);
				}
			}

			return new RespuestaDTO("Usuario o Pass incorrecto", 1, null);
		

	}
	
}
