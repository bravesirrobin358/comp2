public class LinkedList<E> {

    private static class Node<T> {
        private T value;
        private Node<T> next;

        private Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<E> head = null;

    public void addFirst(E value) {

	if (value == null ) {
	    throw new NullPointerException();
	}

        head = new Node<E>(value, head);
    }
    
    public void addLast(E value) {

	if (value == null ) {
	    throw new NullPointerException();
	}

        if (head == null) {
            head = new Node<E>(value, null);
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<E>(value, null);
        }
        
    }

    public int size() {
	return size(head);
    }

    private int size(Node<E> current) {
	if (current == null) {
	    return 0;
	}
	return 1 + size(current.next);
    }

    public int findAndReplace(E target, E replacement) {
    
        if (target == null || replacement == null){
            throw new NullPointerException();
        }
        return findAndReplace(head, target, replacement);

    }

    private int findAndReplace(Node<E> current, E target, E replacement) {

        int number;

        if (current == null){
            number = 0;
        } else {
            number = findAndReplace(current.next, target, replacement);

            if (current.value.equals(target)){
                current.value = replacement;
                number++;
            }
        }
        return number;

    }

    public String toString() {
        StringBuilder sb;
        sb = new StringBuilder("[");
        Node<E> p = head;
        while (p != null) {
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(p.value);
            p = p.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {

	LinkedList<String> l = new LinkedList<String>();

	System.out.println(l);

	System.out.println(l.findAndReplace("I", "she"));

        System.out.println(l);

        l.addLast("I");
        l.addLast("said");
        l.addLast("she");
        l.addLast("said");
        l.addLast("she");
        l.addLast("said");
        l.addLast("I");
        l.addLast("said");

	System.out.println(l);

	System.out.println(l.findAndReplace("I", "she"));

        System.out.println(l);

	System.out.println(l.findAndReplace("I", "she"));

        System.out.println(l);
        
    }
}
