public class Cashier{

	ArrayQueue<Customer> queue;
	Customer currentCustomer;
	int totalCustomerWaitTime;
	int customersServed;
	int totalItemsServed;

	public Cashier(){
		this.queue = new ArrayQueue<Customer>();
		this.totalCustomerWaitTime = 0;
		this.customersServed = 0;
		this.totalItemsServed = 0;
		this.currentCustomer = null;
	}

	public void addCustomer(Customer c){
		queue.enqueue(c);
	}

	public int getQueueSize(){
		return queue.size();
	}

	public void serveCustomers(int currentTime){
		if (currentCustomer == null && getQueueSize() != 0){
			currentCustomer = queue.dequeue();
			totalCustomerWaitTime += (currentTime - currentCustomer.getArrivalTime());
		}
		if (currentCustomer != null){
			currentCustomer.serve();
		}
		if (currentCustomer != null && currentCustomer.getNumberOfItems() == 0){
			totalItemsServed += (currentCustomer.getNumberOfServedItems());
			customersServed++;
			currentCustomer = null;
		}
	}

	public int getTotalCustomerWaitTime(){
		return totalCustomerWaitTime;
	}

	public int getTotalCustomersServed(){
		return customersServed;
	}

	public int getTotalItemsServed(){
		return totalItemsServed;
	}

	public String toString(){
		int avgItemNumPerCustomers = (getTotalItemsServed()/getTotalCustomersServed());
		int avgWaitTime = (getTotalCustomerWaitTime()/getTotalCustomersServed());  
		String output = "The total number of customers served is " + getTotalCustomersServed() +
		" \nThe average number of items per customer was " + avgItemNumPerCustomers + 
		" \nThe average waiting time (in seconds) was " + avgWaitTime;
		return output;
	}
}