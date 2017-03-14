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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_TRANSACTION")
public class Transaction implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@JoinColumn(name = "saving_account_number")
	@ManyToOne(cascade = {})
	private SavingAccount savingAccNumber;

	@Column(name = "ammount", nullable = false)
	private double ammount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transaction_date", nullable = false)
	private Date transactionDate;

	@Column(name = "source_transaction",length=15)
	private String sourceTransact;

	public Transaction(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SavingAccount getSavingAccNumber() {
		return savingAccNumber;
	}

	public void setSavingAccNumber(SavingAccount savingAccNumber) {
		this.savingAccNumber = savingAccNumber;
	}

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getSourceTransact() {
		return sourceTransact;
	}

	public void setSourceTransact(String sourceTransact) {
		this.sourceTransact = sourceTransact;
	}
	
	
	
}
