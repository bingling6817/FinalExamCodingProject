package app;

import java.time.LocalDate;
import java.util.Date;

import pkgLogic.Loan;

public class Payment {
	
	private int PaymentNbr;
	private LocalDate DueDate;
	private double Payment;
	private double AdditionalPayment;
	private double InterestPayment;
	private double Principle;
	private double EndingBalance;
	
	
	
	
	
	public Payment(double beginningBalance, 
			int paymentNbr, 
			LocalDate dueDate, 
			Loan loan) 
	{
		
		this.PaymentNbr = paymentNbr;
		this.DueDate = dueDate;
		this.Payment = beginningBalance > loan.GetPMT() ? loan.GetPMT() 
				: beginningBalance + (beginningBalance *(loan.getInterestRate()/12));
		this.Payment = Math.round(this.Payment * 100.0) /100.0;
		this.AdditionalPayment = loan.getAdditionalPayment();
		
		InterestPayment = Math.round(beginningBalance * (loan.getInterestRate()/12)* 100.0) /100.0;
		
		Principle = loan.GetPMT() + loan.getAdditionalPayment() - InterestPayment;
		Principle = Math.round(Principle * 100.0) /100.0;
		
		EndingBalance = beginningBalance - Principle;
		EndingBalance = Math.round(EndingBalance * 100.0) /100.0;

	}	
	
	public int getPaymentNbr() {
		return PaymentNbr;
	}

	public void setPaymentNbr(int paymentNbr) {
		PaymentNbr = paymentNbr;
	}

	public LocalDate getDueDate() {
		return DueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		DueDate = dueDate;
	}

	public double getPayment() {
		return Payment;
	}

	public void setPayment(double payment) {
		Payment = payment;
	}

	public double getAdditionalPayment() {
		return AdditionalPayment;
	}

	public void setAdditionalPayment(double additionalPayment) {
		AdditionalPayment = additionalPayment;
	}

	public double getInterestPayment() {
		return InterestPayment;
	}

	public void setInterestPayment(double interestPayment) {
		InterestPayment = interestPayment;
	}

	public double getPrinciple() {
		return Principle;
	}

	public void setPrinciple(double principle) {
		Principle = principle;
	}

	public double getEndingBalance() {
		return EndingBalance;
	}

	public void setEndingBalance(double endingBalance) {
		EndingBalance = endingBalance;
	}

	public double getTotalPayment()
	{
		return this.getPayment() + AdditionalPayment;
		
	}
}