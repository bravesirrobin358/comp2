import java.util.NoSuchElementException;

/**
 * COPYRIGHT MATERIAL -- DO NOT DISTRIBUTE
 *
 * @author Mehrdad Sabetzadeh 
 */

// Student name: Yannick Vaillancourt
// Student id: 300226628
public class MyLinkedList<E> {
	private static class Node<T> {
		private T value;
		private Node<T> backward, forward;

		private Node(T value, Node<T> backward, Node<T> forward) {
			this.value = value;
			this.backward = backward;
			this.forward = forward;
		}
	}

	private Node<E> head;
	private Node<E> tail;

	public MyLinkedList(E[] array) {
		if(array == null || array.length == 0){
			throw new IllegalArgumentException();
		}

    	head = new Node<E>(array[0], null, null);
    	tail = head;
    	if (array.length > 1){
    		for (int i=1; i<array.length; i++){
    			Node<E> current = new Node<E>(array[i], null, null);
    			tail.forward = current;
    			current.backward = tail;
    			tail = current;
    		}
    	}
	}

	public void tweak() {
		if( !(head == tail || tail.backward == head)){
			Node<E> current = tail;
			if (current.backward != head){
				while(current.backward != head){
					current = current.backward;
					current.forward.backward = current.backward;
				}
			}
		}
	}

	public String toString() {

		StringBuffer buffer;
		buffer = new StringBuffer("Forward traversal:  [");

		Node<E> current = head;

		while (current != null) {
			if (current != head) {
				buffer.append("->");
			}
			buffer.append(current.value);
			current = current.forward;
		}

		buffer.append("]");
	
		buffer.append(System.lineSeparator());
	
		buffer.append("Backward traversal: [");

		current = tail;

		while (current != null) {
			if (current != tail) {
				buffer.append("->");
			}
			buffer.append(current.value);
			current = current.backward;
		}
		
		buffer.append("]");

		return buffer.toString();
	}
	
	public Iterator<E> iterator() {
		return new MyLinkedListIterator();
	}

	private class MyLinkedListIterator implements Iterator<E> {

		private Node<E> cursor;
		private boolean reachedTail;
		
        public MyLinkedListIterator() {
			cursor = null;
			reachedTail = false;
        }

        public E next() {
        	if (!hasNext()){
        		throw new NoSuchElementException();
        	}
			if (cursor == null){
				cursor = head;
			} else {
				if (reachedTail == true){
					cursor = cursor.backward;
				} else {
					cursor = cursor.forward;
					if (cursor == tail){
					reachedTail = true;
					}
				}
				
			}
			return cursor.value;
        }
        
        public boolean hasNext(){
			if (cursor == null){
				return (!(head == null));
			} else {
				if(reachedTail == true){
					return (cursor.backward != null);
				} else {
					return (cursor.forward != null);
				}
			}
		}
	}
}