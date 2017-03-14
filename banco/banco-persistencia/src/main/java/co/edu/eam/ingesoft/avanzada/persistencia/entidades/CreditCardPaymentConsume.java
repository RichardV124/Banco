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

@Entity
@Table(name = "T_CREDITCARD_PAYMENT_CONSUME")
@NamedQueries({
	@NamedQuery(name = CreditCardPaymentConsume.PAGOS_CONSUMOS, query = "SELECT p FROM CreditCardPaymentConsume p"
			+ " WHERE p.idConsume = ?1") })
public class CreditCardPaymentConsume implements Serializable{

	/**
	 * Obtiene la lista de pagos de un consumo ?1: el consumo
	 */
	public static final String PAGOS_CONSUMOS = "CreditCardPaymentConsume.listarPagosConsumos";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@ManyToOne(cascade = {})
	@JoinColumn(name = "id_consume")
	private CreditCardConsume idConsume;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "payment_date", nullable = false)
	private Date paymentDate;

	@Column(name = "share", nullable = false)
	private int share;

	@Column(name = "ammount", nullable = false)
	private double ammount;

	@Column(name = "capital_ammount", nullable = false)
	private double capitalAmmount;

	@Column(name = "interest_ammount", nullable = false)
	private double interestAmmount;

	public CreditCardPaymentConsume(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CreditCardConsume getIdConsume() {
		return idConsume;
	}

	public void setIdConsume(CreditCardConsume idConsume) {
		this.idConsume = idConsume;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	public double getCapitalAmmount() {
		return capitalAmmount;
	}

	public void setCapitalAmmount(double capitalAmmount) {
		this.capitalAmmount = capitalAmmount;
	}

	public double getInterestAmmount() {
		return interestAmmount;
	}

	public void setInterestAmmount(double interestAmmount) {
		this.interestAmmount = interestAmmount;
	}
	
	
}
