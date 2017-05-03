package co.edu.eam.ingesoft.pa.negocio.dto;

public class CuentaAsociadaDTO {

	private String ownerName;

	private String ownerTypeId;

	private String ownerNumId;

	private String bank;

	private String idType;

	private String idNum;

	private String number;

	private String name;

	private String estado;

	public CuentaAsociadaDTO() {
		this.estado = "No verificada";
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerTypeId() {
		return ownerTypeId;
	}

	public void setOwnerTypeId(String ownerTypeId) {
		this.ownerTypeId = ownerTypeId;
	}

	public String getOwnerNumId() {
		return ownerNumId;
	}

	public void setOwnerNumId(String ownerNumId) {
		this.ownerNumId = ownerNumId;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
