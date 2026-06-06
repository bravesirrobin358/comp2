public class Sort{
	public static int minimumSwaps(int[] arr){
		int count = 0; 
        for(int i = 0; i < arr.length-1; i ++){
        	if(!(arr[i] == i+1)){
        		for(int j = i; j < arr.length-1; j ++){
        			if(arr[j] == i+1){
        				int temp = arr[i];
        				arr[i] = arr[j];
        				arr[j] = temp;
        				count++;
        			}
        		}
        	}
        }
        return count;
    }
	
	public static void main(String[] args){

	}
}
/*i   arr                         swap (indices)
0   [7, 1, 3, 2, 4, 5, 6]   swap (0,3)
1   [2, 1, 3, 7, 4, 5, 6]   swap (0,1)
2   [1, 2, 3, 7, 4, 5, 6]   swap (3,4)
3   [1, 2, 3, 4, 7, 5, 6]   swap (4,5)
4   [1, 2, 3, 4, 5, 7, 6]   swap (5,6)
5   [1, 2, 3, 4, 5, 6, 7]

[7, 1, 3, 2, 4, 5, 6]
[2, 1, 3, 7, 4, 5, 6]
[2, 1, 3, 7, 4, 5, 6]

*/