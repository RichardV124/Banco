package co.edu.eam.ingesoft.pa.banco.web.controladores;

import java.awt.image.BandCombineOp;
import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Bank;

@Named("cuentaAsociadaController")
@ViewScoped
public class CuentaAsociadaControllerAjax implements Serializable{

	/**
	 * Nombre del Titular
	 */
	@NotNull(message="Debe ingresar el nombre del titular")
	private String nombretitular;
	
	/**
	 * Tipo de documento del customer
	 */
	@NotNull(message="Debe seleccionar el tipo de documento")
	private String tipodocumento;
	
	/**
	 * Numero de identificacion del titular
	 */
	@NotNull(message="Debe ingresar el numero de documento")
	private String numerodocumento;
	
	/**
	 * banco selecionado
	 */
	private String bancoSeleccionado;
	
	/**
	 * lista de los bancos registrados
	 */
	private List<Bank> bancos;
}
