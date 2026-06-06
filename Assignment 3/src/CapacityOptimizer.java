public class CapacityOptimizer {
	private static final int NUM_RUNS = 10;

	private static final double THRESHOLD = 5.0d;

	public static int getOptimalNumberOfSpots(int hourlyRate) {
		System.out.println("Testing with hourly rate: " + hourlyRate);
		int n = 1;
		while (true){
			int scoreSum = 0;
			System.out.println("==== Setting lot capacity to: " + n + " ====");
			for (int i = 1; i <= 10; i++){
				long simStart = System.currentTimeMillis();
				Simulator simulator = new Simulator(new ParkingLot(n), hourlyRate,24*3600);
				simulator.simulate();
				scoreSum += simulator.getIncomingQueueSize();
				System.out.println("Simulation run " + i + " (" + (System.currentTimeMillis()-simStart) + "ms)Queue length at end of run " + simulator.getIncomingQueueSize());
			}
			if ((double) scoreSum/10 <= 5){
				//Lot is large enough to meet demand
				System.out.println("Lot size " + n + " average queue length: " + (double) scoreSum /10);
				break;
			}
			System.out.println("Lot size " + n + " average queue length: " + (double) scoreSum /10);
			//Try again with lot of size n+1
			n++;
		}

		return n;

	}

	public static void main(String args[]) {
	
		StudentInfo.display();

		long mainStart = System.currentTimeMillis();

		if (args.length < 1) {
			System.out.println("Usage: java CapacityOptimizer <hourly rate of arrival>");
			System.out.println("Example: java CapacityOptimizer 11");
			return;
		}

		if (!args[0].matches("\\d+")) {
			System.out.println("The hourly rate of arrival should be a positive integer!");
			return;
		}

		int hourlyRate = Integer.parseInt(args[0]);

		int lotSize = getOptimalNumberOfSpots(hourlyRate);

		System.out.println();
		System.out.println("SIMULATION IS COMPLETE!");
		System.out.println("The smallest number of parking spots required: " + lotSize);

		long mainEnd = System.currentTimeMillis();

		System.out.println("Total execution time: " + ((mainEnd - mainStart) / 1000f) + " seconds");

	}
}