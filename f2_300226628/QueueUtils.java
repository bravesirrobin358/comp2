/**
 * COPYRIGHT MATERIAL -- DO NOT DISTRIBUTE
 *
 * @author Mehrdad Sabetzadeh 
 */

// Student name: Yannick Vaillancourt
// Student id: 300226628
public class QueueUtils {
	public static Queue<String> merge(Queue<String>[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException();
		}
		Queue<String> combined = new LinkedQueue<String>();
		boolean allEmpty = false;
		while(true){
			allEmpty = true;
			for (int i=0; i<array.length; i++){
				if(!array[i].isEmpty()){
					allEmpty = false;
					break;
				}
			}
			if(allEmpty == true){
				break;
			}
			for (int i=0; i<array.length; i++){
				if(!array[i].isEmpty()){
					combined.enqueue(array[i].dequeue());
				}
			}
		}
		return combined;
	}
}