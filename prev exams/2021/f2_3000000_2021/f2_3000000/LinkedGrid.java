import java.util.NoSuchElementException;

public class LinkedGrid<E> {
	public static class Node<T> {
		private T data;
		private Node<T> right, down;

		Node(T data, Node<T> right, Node<T> down) {
			this.data = data;
			this.right = right;
			this.down = down;
		}

		public T getData() { return data;}
		public Node<T> getRight(){ return right; }
		public Node<T> getDown(){ return down;}
	}

	private Node<E> topLeft;
	private int rowCount, columnCount;

	public LinkedGrid(E[] array) {
		if (array == null)
			throw new NullPointerException("array cannot be null");
		if (array.length == 0)
			throw new IllegalArgumentException("array must contain elements");

		addFirstRow(array);
		
	}

	private void addFirstRow(E[] array) {

		if (!isEmpty())
			throw new IllegalStateException("Grid must be empty to add a first row");

		topLeft = new Node<E>(array[0], null, null);
		Node<E> current = topLeft;

		for (int i =1; i<array.length; i++){
			current.right = new Node<E>(array[i], null, null);
			current = current.right;
		}
		rowCount = 1;
		columnCount = array.length;
		
	}

	public void addRow(E[] array) {
		if (array == null)
			throw new NullPointerException("array cannot be null");
		if (rowCount == 0)
			throw new IllegalStateException("Need to add first row first");
		if (array.length != this.columnCount)
			throw new IllegalArgumentException("array must contain contain " + this.columnCount + " elements");

		Node<E> prev = topLeft;
		while(prev.down != null){
			prev = prev.down;
		}

		Node<E> current = new Node<E>(array[0], null, null);
		prev.down = current;

		for (int i =1; i<array.length; i++){
			current.right = new Node<E>(array[i], null, null);
			current = current.right;
			prev = prev.right;
			prev.down = current;
		}
		rowCount++;

	}

	public LinkedGrid(E[][] array) {

		if (array == null)
			throw new NullPointerException("array cannot be null");

		addFirstRow(array[0]);

		for (int i = 1; i<array.length; i++){
			addRow(array[i]);
		}
	}

	public int getRowCount() {
		return rowCount;
	}
	
	public int getColumnCount() {
		return columnCount;
	}
	
	public boolean isEmpty() {
		return topLeft == null;
	}

	public Node<E> getTopLeft(){
		return topLeft;
	}

	public E getElementAt(int row, int column) {
		if (row < 0 || row >=  rowCount || column < 0 || column >= columnCount)
			throw new IllegalArgumentException("The row and column parameters both have to be within range");

		Node<E> current = topLeft;
		for(int i = 0; i < row; i++){
			current = current.down;
		}

		for(int i = 0; i < column; i++){
			current = current.right;
		}

		return current.data;

	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				buffer.append(getElementAt(i, j));
				if (j < columnCount - 1)
					buffer.append(", ");
			}
			if (i < rowCount - 1)
				buffer.append(System.lineSeparator());
		}

		return buffer.toString();

	}

	private class LinkedGridIterator implements Iterator<E> {

		private Node<E> cursor, headRow;
 
        public LinkedGridIterator() {
		 	cursor = headRow = null;
        }

        public E next() {

			if (cursor == null){
				cursor = headRow = topLeft;
			} else {
				cursor = cursor.right;
				if (cursor == null){
					headRow = headRow.down;
					cursor = headRow;
				}
			}
			return cursor.data;
        }

        public boolean hasNext(){
			if (cursor == null){
				return !isEmpty();
			} else {
				return (cursor.right != null || headRow.down != null);
			}
		}

	}

	public Iterator<E> iterator() {
		return new LinkedGridIterator();
	}
}
