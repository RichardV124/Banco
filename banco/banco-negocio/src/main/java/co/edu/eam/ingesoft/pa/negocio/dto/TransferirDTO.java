package co.edu.eam.ingesoft.pa.negocio.dto;

public class TransferirDTO {

	private String cuentaAsociada;
	
	private double cantidad;
	
	private String cuentaAhorros;

	
	public TransferirDTO() {
		super();
	}

	public String getCuentaAsociada() {
		return cuentaAsociada;
	}

	public void setCuentaAsociada(String cuentaAsociada) {
		this.cuentaAsociada = cuentaAsociada;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getCuentaAhorros() {
		return cuentaAhorros;
	}

	public void setCuentaAhorros(String cuentaAhorros) {
		this.cuentaAhorros = cuentaAhorros;
	}
	
	
}
