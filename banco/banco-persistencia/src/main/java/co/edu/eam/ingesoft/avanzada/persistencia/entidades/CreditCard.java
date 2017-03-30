package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_CREDITCARD")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({ @NamedQuery(name = CreditCard.CONSULTA_LISTAR_TARJETAS_CUSTOMER, query = "SELECT p FROM Product p "
		+ "WHERE p.customer=?1 AND LENGTH(p.number)=16") })
public class CreditCard extends Product implements Serializable {

	public static final String CONSULTA_LISTAR_TARJETAS_CUSTOMER = "CreditCard.ListarTarjetasCliente";

	@Column(name = "cvc")
	private String cvc;

	@Temporal(TemporalType.DATE)
	@Column(name = "expiration_date")
	private Date expirationDate;

	@Column(name = "ammount")
	private double ammount;

	@Column(name = "ammount_consumed")
	private double ammountConsumed;

	@JoinColumn(name = "franchise")
	@ManyToOne(cascade = {})
	private Franchise franchise;

	public CreditCard() {
		super();
	}

	public CreditCard(String cvc, Date expirationDate, double ammount, double ammountConsumed, Franchise franchise) {
		super();
		this.cvc = cvc;
		this.expirationDate = expirationDate;
		this.ammount = ammount;
		this.ammountConsumed = ammountConsumed;
		this.franchise = franchise;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Franchise getFranchise() {
		return franchise;
	}

	public void setFranchise(Franchise franchise) {
		this.franchise = franchise;
	}

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	public double getAmmountConsumed() {
		return ammountConsumed;
	}

	public void setAmmountConsumed(double ammountConsumed) {
		this.ammountConsumed = ammountConsumed;
	}
	
	public double getDisponible(){
		return ammount-ammountConsumed;
	}

}
