import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * @author Mehrdad Sabetzadeh, University of Ottawa
 */
public class ParkingLot {
	/**
	 * The delimiter that separates values
	 */
	private static final String SEPARATOR = ",";

	/**
	 * The delimiter that separates the parking lot design section from the parked
	 * car data section
	 */
	private static final String SECTIONER = "###";

	/**
	 * Instance variable for storing the number of rows in a parking lot
	 */
	private int numRows;

	/**
	 * Instance variable for storing the number of spaces per row in a parking lot
	 */
	private int numSpotsPerRow;

	/**
	 * Instance variable (two-dimensional array) for storing the lot design
	 */
	private CarType[][] lotDesign;

	/**
	 * Instance variable (two-dimensional array) for storing occupancy information
	 * for the spots in the lot
	 */
	private Car[][] occupancy;

	/**
	 * Constructs a parking lot by loading a file
	 * 
	 * @param strFilename is the name of the file
	 */
	public ParkingLot(String strFilename) throws Exception {

		if (strFilename == null) {
			System.out.println("File name cannot be null.");
			return;
		}

		// determine numRows and numSpotsPerRow; you can do so by
		// writing your own code or alternatively completing the 
		// private calculateLotDimensions(...) that I have provided
		calculateLotDimensions(strFilename);

		// instantiate the lotDesign and occupancy variables!
		// WRITE YOUR CODE HERE!

		// populate lotDesign and occupancy; you can do so by
		// writing your own code or alternatively completing the 
		// private populateFromFile(...) that I have provided
		populateFromFile(strFilename);
	}

	/**
	 * Parks a car (c) at a give location (i, j) within the parking lot.
	 * 
	 * @param i is the parking row index
	 * @param j is the index of the spot within row i
	 * @param c is the car to be parked
	 */
	public void park(int i, int j, Car c) {
		occupancy[i][j] = c;
	}

	/**
	 * Removes the car parked at a given location (i, j) in the parking lot
	 * 
	 * @param i is the parking row index
	 * @param j is the index of the spot within row i
	 * @return the car removed; the method returns null when either i or j are out
	 *         of range, or when there is no car parked at (i, j)
	 */
	public Car remove(int i, int j) {
		//Checks if indexes are out of range or if the spot is empty
		if (i >= numRows || j >= numSpotsPerRow || occupancy[i][j] == null){
			return null;
		}
		//Store the removed car in a temp variable to return it after removing it from the occupancy array
		Car removed = occupancy[i][j];
		occupancy[i][j] = null;
		return removed; 
	}

	/**
	 * Checks whether a car (which has a certain type) is allowed to park at
	 * location (i, j)
	 * 
	 * @param i is the parking row index
	 * @param j is the index of the spot within row i
	 * @return true if car c can park at (i, j) and false otherwise
	 */
	public boolean canParkAt(int i, int j, Car c) {
		//Checks if spot can't be parked at by any car
		if (i >= numRows || j >= numSpotsPerRow || lotDesign[i][j] == CarType.NA || occupancy[i][j] != null){
			return false;
		}
		//Verifies parking rules for each type of car
		switch(c.getType()){
			case ELECTRIC:
				return true;
			case SMALL:
				if (lotDesign[i][j] != CarType.ELECTRIC){
					return true;
				} else{
					return false;
				}
			case REGULAR:
				if (lotDesign[i][j] == CarType.REGULAR || lotDesign[i][j] == CarType.LARGE){
					return true;
				} else{
					return false;
				}
			case LARGE:
				if (lotDesign[i][j] == CarType.LARGE){
					return true;
				} else{
					return false;
				}
		}
		return false;
	}

	/**
	 * @return the total capacity of the parking lot excluding spots that cannot be
	 *         used for parking (i.e., excluding spots that point to CarType.NA)
	 */
	public int getTotalCapacity() {
		int total = 0;

		//Loops through lot array and increments the total capacity by 1 if a spot is not NA
		for (int i = 0; i < lotDesign.length; i++){
			for (int j = 0; j < lotDesign[0].length; j++){
				if(lotDesign[i][j] != CarType.NA){
					total++;
				}
			}
		}

		return total;

	}

	/**
	 * @return the total occupancy of the parking lot (i.e., the total number of
	 *         cars parked in the lot)
	 */
	public int getTotalOccupancy() {
		// WRITE YOUR CODE HERE!
		int total = 0;
		//Loops through lot array and increments number of cars parked by 1 if a spot is not empty (null)
		for (int i = 0; i < occupancy.length; i++){
			for (int j = 0; j < occupancy[0].length; j++){
				if(occupancy[i][j] != null){
					total++;
				}
			}
		}
		return total; 
	}

	private void calculateLotDimensions(String strFilename) throws Exception {

		Scanner scanner = new Scanner(new File(strFilename));

		int rows = 0;
		int cols = 0;
		boolean colsCounted = false;

		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			//Check if we've reached a blank line, is so, continue to next line,
			//then check if we've reached the sectioner, if so, break.
			//Assumes that txt file input starts immediately with lot info and has an empty line followed by ### to separate
			if (str.equals("")){
				continue;
			}
			if (str.contains(SECTIONER)){
				break;
			}
			//Flag to run the column count only once to make code more efficient
			else if (!colsCounted){
				//To find the number of letters, remove excess spaces, commas, and then get length
				str = str.replace(" ", "").replace(",", "");
				cols = str.length();
				colsCounted = true;
			}
			//Increment the number of rows for each loop done
			rows++;
		}

		// Set value to instance variables
		numRows = rows;
		numSpotsPerRow = cols;

		scanner.close();
	}

	private void populateFromFile(String strFilename) throws Exception {

		Scanner scanner = new Scanner(new File(strFilename));

		// YOU MAY NEED TO DEFINE SOME LOCAL VARIABLES HERE!
		lotDesign = new CarType[numRows][numSpotsPerRow];
		occupancy = new Car[numRows][numSpotsPerRow];
		int rowNum = 0;

		// while loop for reading lot design, 
		// same procedure as calculateLotDimenions
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			// WRITE YOUR CODE HERE!
			if (str.equals("")){
				continue;
			}
			if (str.contains(SECTIONER)){
				break;
			}
			
			str = str.strip().replace(",","").replace(" ", "");

			for (int i = 0; i < str.length(); i++){
				// Get CarType by the letter at position i in that row
				lotDesign[rowNum][i] = Util.getCarTypeByLabel(String.valueOf(str.charAt(i)));
			}
			rowNum++;
		}

		// while loop for reading occupancy data
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			// WRITE YOUR CODE HERE!
			//Continue to next line until scanner gets to a line that starts with a number
			if (str.isEmpty() || !Character.isDigit(str.charAt(0))){
				continue;
			}

			//Transform Car info into an array of string values
			str = str.strip().replace(" ", "");
			String[] carInfo = str.split(",");

			// Create new car object from car properties
			Car currentCar = new Car(Util.getCarTypeByLabel(carInfo[2]), carInfo[3]);

			//Get indexes/numbers of current car position
			int carRow = Integer.parseInt(carInfo[0]);
			int carCol = Integer.parseInt(carInfo[1]);

			//Check if current car can park at the current spot and adds it if possible
			if (!canParkAt(carRow, carCol, currentCar)){
				System.out.println("Car " + currentCar.toString() + " cannot be parked at (" + carInfo[0] + "," + carInfo[1] + ")");
				continue;
			}
			else{
				park(carRow, carCol, currentCar);
			}
		}
		scanner.close();
	}

	/**
	 * Produce string representation of the parking lot
	 * 
	 * @return String containing the parking lot information
	 */
	public String toString() {
		// NOTE: The implementation of this method is complete. You do NOT need to
		// change it for the assignment.
		StringBuffer buffer = new StringBuffer();
		buffer.append("==== Lot Design ====").append(System.lineSeparator());

		for (int i = 0; i < lotDesign.length; i++) {
			for (int j = 0; j < lotDesign[0].length; j++) {
				buffer.append((lotDesign[i][j] != null) ? Util.getLabelByCarType(lotDesign[i][j])
						: Util.getLabelByCarType(CarType.NA));
				if (j < numSpotsPerRow - 1) {
					buffer.append(", ");
				}
			}
			buffer.append(System.lineSeparator());
		}

		buffer.append(System.lineSeparator()).append("==== Parking Occupancy ====").append(System.lineSeparator());

		for (int i = 0; i < occupancy.length; i++) {
			for (int j = 0; j < occupancy[0].length; j++) {
				buffer.append(
						"(" + i + ", " + j + "): " + ((occupancy[i][j] != null) ? occupancy[i][j] : "Unoccupied"));
				buffer.append(System.lineSeparator());
			}

		}
		return buffer.toString();
	}

	/**
	 * <b>main</b> of the application. The method first reads from the standard
	 * input the name of the file to process. Next, it creates an instance of
	 * ParkingLot. Finally, it prints to the standard output information about the
	 * instance of the ParkingLot just created.
	 * 
	 * @param args command lines parameters (not used in the body of the method)
	 * @throws Exception
	 */

	public static void main(String args[]) throws Exception {

		StudentInfo.display();

		System.out.print("Please enter the name of the file to process: ");

		Scanner scanner = new Scanner(System.in);

		String strFilename = scanner.nextLine();

		ParkingLot lot = new ParkingLot(strFilename);

		System.out.println("Total number of parkable spots (capacity): " + lot.getTotalCapacity());

		System.out.println("Number of cars currently parked in the lot: " + lot.getTotalOccupancy());

		System.out.print(lot);

	}
}