public class Q3_ArrayInsertionDemo{

	public static int[] insertIntoArray(int[] beforeArray, int indexToInsert, int valueToInsert){
		int len = beforeArray.length + 1;
		int[] newArray = new int[len];
		for (int i=0; i<indexToInsert; i++){
			newArray[i] = beforeArray[i];
		}
		
		newArray[indexToInsert] = valueToInsert;

		for (int i=indexToInsert+1; i<newArray.length; i++){
			newArray[i] = beforeArray[i-1];
		}
		
		return newArray;
	}

	public static void main(String[] args){
		System.out.println("Array before insertion:");
		int[] beforeArray = new int[]{1,5,4,7,9,6};
		for (int value : beforeArray){
			System.out.println(value);
		}
		int[] finalArray = insertIntoArray(beforeArray, 3, 15);
		System.out.println("Array after insertion of 15 at position 3:");
		for (int value : finalArray){
			System.out.println(value);
		}
	}
}