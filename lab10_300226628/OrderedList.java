import java.util.NoSuchElementException;

public class OrderedList implements OrderedStructure {

    // Implementation of the doubly linked nodes (nested-class)

    private static class Node {

      	private Comparable value;
      	private Node previous;
      	private Node next;

      	private Node ( Comparable value, Node previous, Node next ) {
      	    this.value = value;
      	    this.previous = previous;
      	    this.next = next;
      	}
    }

    // Instance variables

    private Node head;

    // Representation of the empty list.

    public OrderedList() {
        this.head = new Node(null, null, null);
    }

    // Calculates the size of the list

    public int size() {
        int size = 0;
      	if (head.value == null){
            return size;
        } else{
            Node current = head;
            size++;
            while(current.next != null){
                size++;
                current = current.next;
            }
        }
        return size;
    }


    public Object get( int pos ) {
        if (size() == 0){
            throw new IndexOutOfBoundsException("list is empty");
        } else if (pos < 0 || pos > size()-1){
            throw new IndexOutOfBoundsException("no entry at specified location");
        } else{

            Node current = head;
            for(int i=0; i<pos; i++){
                current = current.next;
            }
            return current.value;
        }
    }

    // Adding an element while preserving the order

    public boolean add( Comparable o ) {
        Node current = head;
        if (o == null){
            throw new IllegalArgumentException("value cannot be null");
        } 
        if (size() == 0){
            head = new Node(o, null, null);
        } else if (size() == 1){
            if (o.compareTo(head.value) == 1){
                current.next = new Node(o, current, null);

            } else{
                head.next = current;
                head = new Node(o, null, current);
                head.next.previous = head;
                current.next.next = null;
            }
        } else{
            if (o.compareTo(head.value) == -1){
                current.next = current;
                head = new Node(o, null, current);
                current.next.previous = head;
                current.next.next = null;
            } else{
                while (current.next != null && o.compareTo(current.value) != -1){
                    current = current.next;
                }
                if (current.next == null && o.compareTo(current.value) != -1){
                    current.next = new Node(o, current, null);
                } else{
                    current.previous.next = new Node(o, current.previous, current);
                    current.previous = current.previous.next;
                }
            }
        }
        return true;
    }

    //Removes one item from the position pos.

    public void remove( int pos ) {
      Node current = head;
      if (size() == 0){
            throw new IndexOutOfBoundsException("list is empty");
        } else if (pos < 0 || pos > size()-1){
            throw new IndexOutOfBoundsException("no entry at specified location");
        }  else if (size() == 1){
            head = null;
        } else if (pos == size()-1){
            while (current.next != null){
                    current = current.next;
            }
            current.previous.next = null;
            current = null; 

        }else if (pos == 0){
            head = head.next;
            head.previous = null;
        }else{
            
            for(int i=0; i<pos; i++){
                current = current.next;
            }
            current.previous.next = current.next;
            current.next.previous = current.previous;
            current = null;
        }
        return;
    }

    // Knowing that both lists store their elements in increasing
    // order, both lists can be traversed simultaneously.

    public void merge( OrderedList other ) {
      Node current = other.head;
      if (other == null || this == null){
        throw new NullPointerException("other or this is null");
      }
      if (current == null){
        return;
      }
      while (current.next != null){
        add(current.value);
        current = current.next;
      }
      add(current.value);
      return;
    }
}