package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="T_FRANCHISE")
@NamedQueries({
	@NamedQuery(name=Franchise.CONSULTA_LISTAR_FRANQUICIAS,query="SELECT f FROM Franchise f")
})
public class Franchise implements Serializable{

	public static final String CONSULTA_LISTAR_FRANQUICIAS = "Franquicia.ListarTodas";
	@Id
	@Column(name="code")
	private String code;
	
	@Column(name="name")
	private String name;

	public Franchise() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Franchise(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String toString() {
		return name;
	}

	
	
	
}
