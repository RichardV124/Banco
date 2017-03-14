package co.edu.eam.ingesoft.avanzada.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_SAVING_ACCOUNT")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
		@NamedQuery(name = SavingAccount.CONSULTA_LISTAR_CUENTAS_CUSTOMER, query = "SELECT p FROM Product p "
				+ "WHERE p.customer=?1 AND LENGTH(p.number)=11") })
public class SavingAccount extends Product implements Serializable {

	public static final String CONSULTA_LISTAR_CUENTAS_CUSTOMER = "SavingAccount.ListarCuentasCliente";

	@Column(name = "saving_interest", nullable = false)
	private double savingInterest;

	@Column(name = "ammount", nullable = false)
	private double ammount;
	
	public SavingAccount() {
	}

	public SavingAccount(double savingInterest, double ammount) {
		super();
		this.savingInterest = savingInterest;
		this.ammount = ammount;
	}

	public double getSavingInterest() {
		return savingInterest;
	}

	public void setSavingInterest(double savingInterest) {
		this.savingInterest = savingInterest;
	}

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	
}
