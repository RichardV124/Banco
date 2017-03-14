package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name="T_CREDITCARD_CONSUME")
@Entity
@NamedQueries({
	@NamedQuery(name = CreditCardConsume.CONSULTA_LISTAR_CONSUMOS_TARJETA, query = "SELECT ccc FROM CreditCardConsume ccc"
			+ " WHERE ccc.creditCard=?1 AND ccc.isPayed=false") })
public class CreditCardConsume implements Serializable{

	public static final String CONSULTA_LISTAR_CONSUMOS_TARJETA = "CreditCardConsume.ListarConsumosTarjeta";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@JoinColumn(name="creditcard_number")
	@ManyToOne(cascade={})
	private CreditCard creditCard;
	
	@Column(name="date_consume")
	@Temporal(TemporalType.DATE)
	private Date dateConsume;
	
	@Column(name="number_shares")
	private int numberShares;
	
	@Column(name="ammount")
	private double ammount;
	
	@Column(name="interest")
	private double interest;
	
	@Column(name="remaining_ammount")
	private double remainingAmmount;
	
	@Column(name="is_payed")
	private boolean isPayed;

	public CreditCardConsume() {
		super();
	}

	public CreditCardConsume(int id, CreditCard creditCard, Date dateConsume, int numberShares, double ammount,
			double interest, double remainingAmmount, boolean isPayed) {
		super();
		this.id = id;
		this.creditCard = creditCard;
		this.dateConsume = dateConsume;
		this.numberShares = numberShares;
		this.ammount = ammount;
		this.interest = interest;
		this.remainingAmmount = remainingAmmount;
		this.isPayed = isPayed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public Date getDateConsume() {
		return dateConsume;
	}

	public void setDateConsume(Date dateConsume) {
		this.dateConsume = dateConsume;
	}

	public int getNumberShares() {
		return numberShares;
	}

	public void setNumberShares(int numberShares) {
		this.numberShares = numberShares;
	}

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getRemainingAmmount() {
		return remainingAmmount;
	}

	public void setRemainingAmmount(double remainingAmmount) {
		this.remainingAmmount = remainingAmmount;
	}

	public boolean isPayed() {
		return isPayed;
	}

	public void setPayed(boolean isPayed) {
		this.isPayed = isPayed;
	}

	@Override
	public String toString() {
		return id+" - " +ammount;
	}
	
	
	
}
