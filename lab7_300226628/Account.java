public class Account{

	private double balance;
	public Account(){
		this.balance = balance;
	}

	public double getBalance(){

		return this.balance;
	}
	public void deposit(double amount){
		balance += amount;
		System.out.println("new balance="+balance+"$");
	}
	public void withdraw(double amount) throws NotEnoughMoneyException{
		if (balance < amount){
			throw new NotEnoughMoneyException(amount, this);
		}
		balance -= amount;
		System.out.println("new balance="+balance+"$");
	}
}