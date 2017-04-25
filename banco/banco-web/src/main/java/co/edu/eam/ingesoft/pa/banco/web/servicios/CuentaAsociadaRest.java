package co.edu.eam.ingesoft.pa.banco.web.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Bank;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociada;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.SegundaClave;
import co.edu.eam.ingesoft.pa.negocio.beans.BankEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SavingAccountEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SegundaClaveEJB;

//para invocar un servicio se necesita:
/*
* 1. la url del servicio: http://ip:puerto/<root>/<raizRest>/<pathclase>/<pathmetodo>
*/
//<pathclase>=cuentaasociada
@Path("/cuentaasociada")
public class CuentaAsociadaRest {

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
	 * EJB del banco
	 */
	@EJB
	private BankEJB bankEJB;

	@Path("/verificar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@POST
	public boolean verificar(@FormParam("cuenta") String cuenta, @FormParam("id") String cedula,
			@FormParam("tipoId") String tipoId) {
		return savAccountEJB.verficarCuenta(cuenta, cedula, tipoId);

	}

	@GET
	public List<Bank> listarBancos() {

		return bankEJB.listarBancos();
	}

	@POST
	public List<CuentaAsociada> listarCuentas(Customer cus) {

		return cuentaAsociadaEJB.listarCuentas(cus);
	}

	public boolean asociarCuenta(CuentaAsociada cuenta) {

		if (cuenta == null) {
			return false;
		}

		cuentaAsociadaEJB.crear(cuenta);
		return true;
	}

	public String generarEnviarCodigo(Customer cus) {

		String claveGenerada = segundaClaveEJB.generarClave();
		SegundaClave sc = new SegundaClave();
		sc.setClave(claveGenerada);
		segundaClaveEJB.crear(sc, cus);
		// segundaClaveEJB.enviarEmail(claveGenerada, cus);

		return claveGenerada;
	}

	@Path("/transferir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@POST
	public String transferir(@FormParam("cuenta") String numeroCuenta, @FormParam("cantidad") double cantidad) {

		System.out.println(numeroCuenta);
		System.out.println(cantidad);
		System.out.println("asdasd"+cantidad);
		
		SavingAccount cuenta = savAccountEJB.buscarSavingAccount(numeroCuenta);
		if (cuenta != null) {
			boolean verificado = verificar(cuenta.getNumber(), cuenta.getCustomer().getIdNum(),
					cuenta.getCustomer().getIdType());

			if (verificado) {
				cuentaAsociadaEJB.transferenciaInterbancariaWS("3", numeroCuenta, cantidad);
				return "OK";
			}
		}
		return "ERRORSITO";
	}
}