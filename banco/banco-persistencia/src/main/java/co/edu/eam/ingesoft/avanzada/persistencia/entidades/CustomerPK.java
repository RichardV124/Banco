package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

public class CustomerPK implements Serializable {

	private String idType;

	private String idNum;

	public CustomerPK() {

	}

	public CustomerPK(String idType, String idNum) {
		super();
		this.idType = idType;
		this.idNum = idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNum() {
		return idNum;
	}

	public String getIdType() {
		return idType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idNum == null) ? 0 : idNum.hashCode());
		result = prime * result + ((idType == null) ? 0 : idType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerPK other = (CustomerPK) obj;
		if (idNum == null) {
			if (other.idNum != null)
				return false;
		} else if (!idNum.equals(other.idNum))
			return false;
		if (idType == null) {
			if (other.idType != null)
				return false;
		} else if (!idType.equals(other.idType))
			return false;
		return true;
	}

}
