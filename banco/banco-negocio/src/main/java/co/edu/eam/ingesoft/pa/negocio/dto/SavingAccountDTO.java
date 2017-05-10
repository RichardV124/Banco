package co.edu.eam.ingesoft.pa.negocio.dto;

public class SavingAccountDTO {

	private String number;
	
	private double savingInterest;

	private double ammount;
	
	
	public SavingAccountDTO() {
		super();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
