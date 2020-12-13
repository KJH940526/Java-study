package prob5;

public class Account {
	String accountNo;
	int balance;
	
	public Account(String accountNo) {
		this.accountNo = accountNo;
		this.balance = 0;
		System.out.println(accountNo + " 계좌가 개설되었습니다.");
	}
	
	public String getAccNo() {
		return accountNo;
	}
	
	public void setAccNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public void withdraw(int amount) {
		this.balance -= amount;
		System.out.println(accountNo + " 계좌에 " + amount + "만원이 출금되었습니다.");
	}

	public void deposit(int amount) {
		this.balance += amount;
		System.out.println(accountNo + " 계좌에 " + amount + "만원이 입금되었습니다.");
	}
	
	
}
