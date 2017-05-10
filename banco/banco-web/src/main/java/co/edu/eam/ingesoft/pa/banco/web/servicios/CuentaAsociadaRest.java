package co.edu.eam.ingesoft.pa.banco.web.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Bank;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.CuentaAsociada;
import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.pa.negocio.beans.BankEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SavingAccountEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.SegundaClaveEJB;
import co.edu.eam.ingesoft.pa.negocio.dto.CuentaAsociadaDTO;
import co.edu.eam.ingesoft.pa.negocio.dto.VerificarDTO;

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
	 * EJB de la segunda clave
	 */
	@EJB
	private CustomerEJB customerEJB;

	/**
	 * EJB del banco
	 */
	@EJB
	private BankEJB bankEJB;

	@Path("/listarBancos")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public RespuestaDTO listarBancos() {

		List<Bank> lista = bankEJB.listarBancosWS();

		if (lista.isEmpty()) {
			return new RespuestaDTO("No hay registros", 1, null);
		} else {
			return new RespuestaDTO("Se encontraron registros", 0, lista);
		}

	}

	@GET
	@Path("/listarCuentasAsociadas")
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarCuentas(@QueryParam("id") String cedula, @QueryParam("tipoId") String tipoId) {

		Customer c = customerEJB.buscarCustomer(tipoId, cedula);

		List<CuentaAsociada> lista = cuentaAsociadaEJB.listarCuentas(c);

		if (lista.isEmpty()) {

			return new RespuestaDTO("No hay registros", 1, null);
		} else {
			List<CuentaAsociadaDTO> listaDto = new ArrayList<>();
			for (CuentaAsociada cuentas : lista) {
				CuentaAsociadaDTO dto = new CuentaAsociadaDTO();

				dto.setOwnerName(cuentas.getOwnerName());
				dto.setOwnerTypeId(cuentas.getOwnerTypeId());
				dto.setOwnerNumId(cuentas.getOwnerNumId());
				dto.setBank(cuentas.getBank().getId());
				dto.setIdType(cuentas.getCustomer().getIdType());
				dto.setIdNum(cuentas.getCustomer().getIdNum());
				dto.setNumber(cuentas.getNumber());
				dto.setName(cuentas.getName());
				dto.setEstado(cuentas.getEstado());
				listaDto.add(dto);
			}
			return new RespuestaDTO("Se encontraron registros", 0, listaDto);
		}
	}

	@POST
	@Path("/asociarCuenta")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO asociarCuenta(CuentaAsociadaDTO cuentaAsociada) {

		if (cuentaAsociada != null) {

			CuentaAsociada ca = new CuentaAsociada();
			ca.setOwnerName(cuentaAsociada.getOwnerName());
			ca.setOwnerTypeId(cuentaAsociada.getOwnerTypeId());
			ca.setOwnerNumId(cuentaAsociada.getOwnerNumId());
			Bank b = bankEJB.buscar(cuentaAsociada.getBank());
			ca.setBank(b);
			Customer cus = customerEJB.buscarCustomer(cuentaAsociada.getIdType(), cuentaAsociada.getIdNum());
			ca.setCustomer(cus);
			ca.setNumber(cuentaAsociada.getNumber());
			ca.setName(cuentaAsociada.getName());

			cuentaAsociadaEJB.crear(ca);
			return new RespuestaDTO("Se hizo la peticion de asociacion correctamente", 0, null);
		}

		return new RespuestaDTO("Hubo un error al hacer la peticion de asociacion", 1, null);
	}
	
	
	@POST
	@Path("/verificarAsociada")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO verificarAsociada(VerificarDTO dto) {

		CuentaAsociada cuentaAsociada = cuentaAsociadaEJB.buscar(dto.getCuenta());
		
		if (cuentaAsociada != null) {

			if(cuentaAsociadaEJB.verificarCuenta(cuentaAsociada)){
				return new RespuestaDTO("Verificada", 0, null);
			}
			return new RespuestaDTO("ERROR", 0, null);
			
		}

		return new RespuestaDTO("Hubo un error al intentar verificar la cuenta", 1, null);
	}
	

}
