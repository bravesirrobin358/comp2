public class UniquifiableLinkedQueue<E> extends LinkedQueue<E> {

	public Queue<E> uniquify() {
		
		Queue<E> result = new LinkedQueue<E>();
		Queue<E> temp = new LinkedQueue<E>();

		E prev = null;

		while(!isEmpty()){
			E elem = this.dequeue();

			if (result.isEmpty() || !elem.equals(prev)){
				result.enqueue(elem);
				prev = elem;
			}
			temp.enqueue(elem);
		}

		while(!temp.isEmpty()){
			this.enqueue(temp.dequeue());
		}
		return result;
	}

}