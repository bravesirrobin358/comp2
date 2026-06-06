import java.io.File;
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
	private Spot[][] occupancy;

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

		lotDesign = new CarType[numRows][numSpotsPerRow];
		occupancy = new Spot[numRows][numSpotsPerRow];

		// populate lotDesign and occupancy; you can do so by
		// writing your own code or alternatively completing the 
		// private populateFromFile(...) that I have provided
		populateDesignFromFile(strFilename);
		
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumSpotsPerRow() {
		return numSpotsPerRow;
	}

	/**
	 * Parks a car (c) at a give location (i, j) within the parking lot.
	 * 
	 * @param i         is the parking row index
	 * @param j         is the index of the spot within row i
	 * @param c         is the car to be parked
	 * @param timestamp is the (simulated) time when the car gets parked in the lot
	 */
	public void park(int i, int j, Car c, int timestamp) {
		if (canParkAt(i, j, c)){
			Spot currentSpot = new Spot(c,timestamp);
			occupancy[i][j] = currentSpot;
		} else {
			System.out.println("Car " + c + " cannot be parked at (" + i + "," + j + ")");
		}		

	}

	/**
	 * Removes the car parked at a given location (i, j) in the parking lot
	 * 
	 * @param i is the parking row index
	 * @param j is the index of the spot within row i
	 * @return the spot removed; the method returns null when either i or j are out
	 *         of range, or when there is no car parked at (i, j)
	 */
	public Spot remove(int i, int j) {

		if (i >= numRows || j >= numSpotsPerRow){
			System.out.println("Out of range index error.");
			return null;
		}
		//Store the removed car in a temp variable to return it after removing it from the occupancy array
		Spot removed = new Spot(occupancy[i][j].getCar(),occupancy[i][j].getTimestamp());
		occupancy[i][j] = null;
		return removed; 
	}

	/**
	 * Returns the spot instance at a given position (i, j)
	 * 
	 * @param i is the parking row index
	 * @param j is the index of the spot within row i
	 * @return the spot instance at position (i, j)
	 */
	public Spot getSpotAt(int i, int j) {
		if (i < numRows && j < numSpotsPerRow && i > -1 && j > -1){
			return occupancy[i][j];
		} else {
			System.out.println("Out of range index error.");
			return null; 
		}
		
	}

	/**
	 * Checks whether a car (which has a certain type) is allowed to park at
	 * location (i, j)
	 *
	 * NOTE: This method is complete; you do not need to change it.
	 * 
	 * @param i is the parking row index
	 * @param j is the index of the spot within row i
	 * @return true if car c can park at (i, j) and false otherwise
	 */
	public boolean canParkAt(int i, int j, Car c) {

		if (i >= numRows || j >= numSpotsPerRow || lotDesign[i][j] == CarType.NA || occupancy[i][j] != null){
			return false;
		}
		//Verifies parking rules for each type of car
		switch(c.getType()){
			case ELECTRIC:
				return true;
			case SMALL:
				return lotDesign[i][j] != CarType.ELECTRIC;
			case REGULAR:
				return lotDesign[i][j] == CarType.REGULAR || lotDesign[i][j] == CarType.LARGE;
			case LARGE:
				return lotDesign[i][j] == CarType.LARGE;
		}
		return false;
		
		}

	/**
	 * Attempts to park a car in the lot. Parking is successful if a suitable parking spot
	 * is available in the lot. If some suitable spot is found (anywhere in the lot), the car
	 * is parked at that spot with the indicated timestamp and the method returns "true".
	 * If no suitable spot is found, no parking action is taken and the method simply returns
	 * "false"
	 * 
	 * @param c is the car to be parked
	 * @param timestamp is the simulation time at which parking is attempted for car c 
	 * @return true if c is successfully parked somwhere in the lot, and false otherwise
	 */
	public boolean attemptParking(Car c, int timestamp) {
		CarType[] eComp = {CarType.ELECTRIC, CarType.SMALL, CarType.REGULAR, CarType.LARGE};
		CarType[] sComp = {CarType.SMALL, CarType.REGULAR, CarType.LARGE};
		CarType[] rComp = {CarType.REGULAR, CarType.LARGE};
		CarType[] lComp = {CarType.LARGE};

		switch(c.getType()){
			case ELECTRIC:
				if (optimisedSpotSearch(c, timestamp, eComp)) return true;
			case SMALL:
				if (optimisedSpotSearch(c, timestamp, sComp)) return true;
			case REGULAR:
				if (optimisedSpotSearch(c, timestamp, rComp)) return true;
			case LARGE:
				if (optimisedSpotSearch(c, timestamp, lComp)) return true;
		}
		return false;
		
	}

	private boolean optimisedSpotSearch(Car c, int timestamp, CarType[] compSpots) {
		for (int i = 0; i < compSpots.length; i++){
			for (int j=0; j<numRows; j++){
				for (int k=0; k<numSpotsPerRow; k++){
					if (canParkAt(j, k, c) && lotDesign[j][k] == compSpots[i]){
						park(j, k, c, timestamp);
						return true;
					}
				}
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
	 * @return the total occupancy of the parking lot
	 */
	public int getTotalOccupancy() {

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

	private void populateDesignFromFile(String strFilename) throws Exception {

		Scanner scanner = new Scanner(new File(strFilename));

		int rowNum = 0;

		// while loop for reading lot design, 
		// same procedure as calculateLotDimenions
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			// WRITE YOUR CODE HERE!
			str = str.strip().replace(",","").replace(" ", "");

			for (int i = 0; i < str.length(); i++){
				// Get CarType by the letter at position i in that row
				lotDesign[rowNum][i] = Util.getCarTypeByLabel(String.valueOf(str.charAt(i)));
			}
			rowNum++;
		}
		scanner.close();

	}

	/**
	 * NOTE: This method is complete; you do not need to change it.
	 * @return String containing the parking lot information
	 */
	public String toString() {
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
}