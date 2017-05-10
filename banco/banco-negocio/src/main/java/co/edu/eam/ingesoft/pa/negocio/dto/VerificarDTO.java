package co.edu.eam.ingesoft.pa.negocio.dto;

public class VerificarDTO {

	private String idType;

	private String idNum;
	
	private String cuenta;

	
	public VerificarDTO() {
		super();
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	
	
}