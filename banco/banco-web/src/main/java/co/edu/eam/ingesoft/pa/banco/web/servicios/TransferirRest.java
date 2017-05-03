package co.edu.eam.ingesoft.pa.banco.web.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociada;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SegundaClave;
import co.edu.eam.ingesoft.pa.negocio.beans.BankEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SavingAccountEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SegundaClaveEJB;
import co.edu.eam.ingesoft.pa.negocio.dto.TransferirDTO;

//para invocar un servicio se necesita:
/*
* 1. la url del servicio: http://ip:puerto/<root>/<raizRest>/<pathclase>/<pathmetodo>
*/
//<pathclase>=cuentaasociada
@Path("/transferir")
public class TransferirRest {

	@EJB
	private CuentaAsociadaEJB cuentaAsociadaEJB;

	@EJB
	private SavingAccountEJB savAccountEJB;

	/**
	 * EJB de la segunda clave
	 */
	@EJB
	private SegundaClaveEJB segundaClaveEJB;

	/**
	 * EJB de la segunda clave
	 */
	@EJB
	private CustomerEJB customerEJB;

	/**
	 * EJB de las cuentas asociadas
	 */
	@EJB
	private CuentaAsociadaEJB asociadaCuentaEJB;

	/**
	 * EJB del banco
	 */
	@EJB
	private BankEJB bankEJB;

	@Path("/verificar")
	// @Produces(MediaType.APPLICATION_JSON) // retorno
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // recibe
	@POST
	public String verificar(@FormParam("cuenta") String cuenta, @FormParam("id") String cedula,
			@FormParam("tipoId") String tipoId) {

		return savAccountEJB.verficarCuenta(cuenta, cedula, tipoId);

	}

	// @POST
	// @Path("/transferir")
	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// @Produces(MediaType.APPLICATION_JSON)
	// public RespuestaDTO transferir(@FormParam("cuentaasociada") String
	// cuentaAsociada,
	// @FormParam("cantidad") double cantidad, @FormParam("cuentaahorros")
	// String cuentaAhorros) {
	// CuentaAsociada asociada = asociadaCuentaEJB.buscar(cuentaAsociada);
	// System.out.println(cuentaAsociada + "WWWWWWW");
	// System.out.println(asociada.getBank() + "ADASDASD");
	// if (asociada != null) {
	//
	// cuentaAsociadaEJB.transferenciaInterbancariaWS(asociada.getBank().getId(),
	// cuentaAsociada, cantidad,
	// cuentaAhorros);
	// return new RespuestaDTO("Se completo la transferencia exitosamente!", 0,
	// null);
	// }
	//
	// return new RespuestaDTO("Ocurrio un error al completar la transferencia",
	// 1, null);
	//
	// }

	@POST
	@Path("/recibirdinero")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String recibirDinero(@FormParam("numerocuenta") String numeroCuenta,
			@FormParam("cantidad") double cantidad) {
		SavingAccount cuenta = savAccountEJB.buscarSavingAccount(numeroCuenta);
		if (cuenta != null) {
			savAccountEJB.consignarMontoCuenta(cuenta, cantidad);
			return "EXITO";
		}
		return "ERROR";
	}

	@GET
	@Path("/listarCuentasAsociadasVerificadas")
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarCuentasVerificadas(@QueryParam("id") String cedula, @QueryParam("tipoId") String tipoId) {

		Customer c = customerEJB.buscarCustomer(tipoId, cedula);

		List<CuentaAsociada> lista = cuentaAsociadaEJB.listarCuentasAsociadasValidadas(c);

		if (lista.isEmpty()) {
			return new RespuestaDTO("No hay registros", 1, null);
		} else {
			return new RespuestaDTO("Se encontraron registros", 0, lista);
		}
	}

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public String generarEnviarCodigo(@QueryParam("id") String cedula, @QueryParam("tipoId") String tipoId) {

		Customer cus = customerEJB.buscarCustomer(tipoId, cedula);
		String claveGenerada = segundaClaveEJB.generarClave();
		SegundaClave sc = new SegundaClave();
		sc.setClave(claveGenerada);
		segundaClaveEJB.crear(sc, cus);
		// segundaClaveEJB.enviarEmail(claveGenerada, "");

		return claveGenerada;
	}

	@GET
	@Path("/listarCuentasCliente")
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarCuentasCliente(@QueryParam("id") String cedula, @QueryParam("tipoId") String tipoId) {

		Customer c = customerEJB.buscarCustomer(tipoId, cedula);

		List<SavingAccount> lista = savAccountEJB.listarCuentasCliente(c);

		if (lista.isEmpty()) {
			return new RespuestaDTO("No hay registros", 1, null);
		} else {
			return new RespuestaDTO("Se encontraron registros", 0, lista);
		}
	}

	@POST
	@Path("/transferir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO transferir(TransferirDTO dto) {
		CuentaAsociada asociada = asociadaCuentaEJB.buscar(dto.getCuentaAsociada());
		System.out.println(dto.getCuentaAsociada() + "WWWWWWW");
		System.out.println(asociada.getBank().getId() + "ADASDASD");

		if (asociada != null) {

			String msj = cuentaAsociadaEJB.transferenciaInterbancariaWS(asociada.getBank().getId(), dto.getCuentaAsociada(),
					dto.getCantidad(), dto.getCuentaAhorros());
			System.out.println(msj+"ASJDKASDJ");
			return new RespuestaDTO("Se completo la transferencia exitosamente!", 0, true);
		}

			return new RespuestaDTO("Ocurrio un error al completar la transferencia", 1, false);
		

	}

}
