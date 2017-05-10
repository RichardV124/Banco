package co.edu.eam.ingesoft.pa.negocio.dto;

public class LoginRespuestaDTO {

	private String token;
	private String idType;
	private String idNum;
	
	public LoginRespuestaDTO() {
		// TODO Auto-generated constructor stub
	}

	
	
	public LoginRespuestaDTO(String token, String idType, String idNum) {
		super();
		this.token = token;
		this.idType = idType;
		this.idNum = idNum;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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