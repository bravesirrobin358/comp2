import java.util.Scanner;

public class Q6{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		double[] grades = new double[10];
		for (int i=0; i<10; i++){
			double grade = scan.nextDouble();
			grades[i] = grade;
		}
		System.out.println(calculateAverage(grades));
		System.out.println(calculateMedian(grades));
		System.out.println(calculateNumberFailed(grades));
		System.out.println(calculateNumberPassed(grades));

	}
	
	public static double calculateAverage(double[] notes){
		double result = 0;
		for (double number : notes){
			result += number;
		}
		result = result / notes.length;
		return result;
	}

	public static double calculateMedian(double[] notes){
		int position = -1; 
		for (int i=notes.length-1; i>0; i--){
			double highest = notes[i];
			for (int j=i-1; j>-1; j--){
				if (highest < notes[j]){
					position = j;
					highest = notes[j];
				}
			}
			if (highest != notes[i]){
				double tempOldValue = notes[i];
				notes[i] = notes[position];
				notes[position] = tempOldValue;
			}
		}
		if (notes.length % 2 == 1){
			int center = 0;
			center = (int)(notes.length / 2);
			return notes[center];
		} else{
			int higherThanCenter = 0;
			higherThanCenter = notes.length/2;
			return (notes[higherThanCenter]+notes[higherThanCenter-1])/2;
		}
		
	}
	
	public static int calculateNumberFailed(double[] notes){
		int count = 0;
		for (double score : notes){
			if (score < 50){
				count++;
			}
		}
		return count;
	}

	public static int calculateNumberPassed(double[] notes){
		int count = 0;
		for (double score : notes){
			if (score >= 50){
				count++;
			}
		}
		return count;
	}

}