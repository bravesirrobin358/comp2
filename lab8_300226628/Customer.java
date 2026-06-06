import java.util.Random;

public class Customer{

	int arrivalTime;
	int initialNumberOfItems;
	int numberOfItems;
	int MAX_NUMB_ITEMS = 20;

	public Customer(int arrivalTime){
		this.arrivalTime = arrivalTime;
		Random generator;
		generator = new Random();

		this.numberOfItems = generator.nextInt(MAX_NUMB_ITEMS-1)+1;
		this.initialNumberOfItems = this.numberOfItems;
	}

	public int getArrivalTime(){
		return arrivalTime;
	}

	public int getNumberOfItems(){
		return numberOfItems;
	}

	public int getNumberOfServedItems(){
		return (initialNumberOfItems - numberOfItems);
	}

	public void serve(){
		numberOfItems--;
	}
}