package ch15;

public class TransactionRecord {

	private double transactionAmount;
	private int account;
	
	public TransactionRecord(int account, double transactionAmount){
		this.account = account;
		this.transactionAmount = transactionAmount;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
}
