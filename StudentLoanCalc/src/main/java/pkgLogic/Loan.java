package pkgLogic;

import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.FinanceLib;

import app.Payment;

public class Loan {

	private LocalDate startDate;
	private double LoanAmount;
	private double LoanBalanceEnd;
	private double InterestRate;
	private double AdditionalPayment;
	private int LoanPaymentCnt;
	private boolean bCompoundingOption;
	
	
	private ArrayList<Payment> loanPayments = new ArrayList<Payment>();
	
	public Loan(LocalDate startDate, double loanAmount, double interestRate, double additionalPayment,
			int loanPaymentCnt) {
		super();
		this.startDate = startDate;
		LoanAmount = loanAmount;
		InterestRate = interestRate;
		AdditionalPayment = additionalPayment;
		LoanPaymentCnt = loanPaymentCnt;
		bCompoundingOption = false;
		LoanBalanceEnd = 0;
		
		double RemainingBalance = LoanAmount;
		int PaymentCnt = 1;
		
		while (RemainingBalance >= 0)
		{
		
			Payment payment = new Payment(RemainingBalance, PaymentCnt++, startDate.plusMonths(PaymentCnt-2), this);
			RemainingBalance = payment.getEndingBalance();
					
			loanPayments.add(payment);
			
		}
		
		
	}


	public Loan(LocalDate startDate, double loanAmount, double loanBalanceEnd, double interestRate,
			double additionalPayment, int loanPaymentCnt, boolean bCompoundingOption) {
		super();
		this.startDate = startDate;
		LoanAmount = loanAmount;
		LoanBalanceEnd = loanBalanceEnd;
		InterestRate = interestRate;
		AdditionalPayment = additionalPayment;
		LoanPaymentCnt = loanPaymentCnt;
		this.bCompoundingOption = bCompoundingOption;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public double getLoanAmount() {
		return LoanAmount;
	}


	public double getLoanBalanceEnd() {
		return LoanBalanceEnd;
	}


	public double getInterestRate() {
		return InterestRate;
	}


	public double getAdditionalPayment() {
		return AdditionalPayment;
	}


	public int getLoanPaymentCnt() {
		return LoanPaymentCnt;
	}


	public boolean isbCompoundingOption() {
		return bCompoundingOption;
	}
	
	public double GetPMT()
	{
		return Math.abs(FinanceLib.pmt(this.getInterestRate()/12,
				this.LoanPaymentCnt, 
				this.LoanAmount, 
				this.LoanBalanceEnd, 
				this.bCompoundingOption));
	}
	
	public double GetTotalPayments()
	{
		return loanPayments.stream().mapToDouble(p -> p.getTotalPayment()).sum();
	}
	public double GetTotalInterest()
	{
		return loanPayments.stream().mapToDouble(p -> p.getInterestPayment()).sum();
	}


	public ArrayList<Payment> getLoanPayments() {
		return loanPayments;
	}
	
}