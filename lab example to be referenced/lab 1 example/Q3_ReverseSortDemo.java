public class Q3_ReverseSortDemo {
	public static void main(String[] args){
		char[] unorderedLetters;
		unorderedLetters = new char[]{'b', 'm', 'z', 'a', 'u'};
		reverseSort(unorderedLetters);
		for (int i = 0 ; i < unorderedLetters.length; i++ )
			System.out.print(unorderedLetters[i]);
	}

	//method that sorts a char array into its reverse alphabetical order
	public static void reverseSort(char[] values){
		int position = -1; 
		for (int i=values.length-1; i>0; i--){
			int highest = values[i];
			for (int j=i-1; j>-1; j--){
				if (highest > values[j]){
					position = j;
					highest = values[j];
				}
			}
			if (highest != values[i]){
				char tempOldValue = values[i];
				values[i] = values[position];
				values[position] = tempOldValue;
			}
		}
	}

}