public class NotEnoughMoneyException extends IllegalStateException{
	
	Account myAccount;
	double extra;
	public NotEnoughMoneyException(double extra, Account myAccount){
        super("you do not have enough money to withdraw"+extra+"$");
        this.extra=extra;
        this.myAccount=myAccount;
	}
	public double getBalance(){
		return myAccount.getBalance();
	}

	public double getAmount(){
		return extra;
	}
	public double getMissingAmount(){
		System.out.println("this.getAmount= " + this.getAmount());
		System.out.println("this.getBalance= " + getBalance());
		System.out.println("this.getBalance= " + this.getBalance());
		return (this.getAmount() - this.getBalance());
	}

}
  