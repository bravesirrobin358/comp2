public class Q3_SquareArray{

	public static int[] createArray(int size){
		int[] squareArray = new int[size];
		for (int i=0; i<squareArray.length; i++){
			squareArray[i] = i*i;
		}
		return squareArray;
	}

	public static void main(String[] args){
		int[] squareArray = createArray(13);
		for (int i=0; i<squareArray.length; i++){
			System.out.println("The square of " + i + " is: " +squareArray[i]);
		}

	}
}