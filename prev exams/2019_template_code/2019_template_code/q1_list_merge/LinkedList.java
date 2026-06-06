public class LinkedList<E> {

    private static class Node<T> {

        private T value;

        private Node<T> prev;
        private Node<T> next;

        private Node( T value, Node<T> prev, Node<T> next ) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> head;
    private int size;

    public LinkedList() {
        head = new Node<E>( null, null, null );
        head.next = head.prev = head; // circularize
        size = 0;
    }

    public int size() {
        return size;
    }

    public void merge(LinkedList<E> other) {
    
        if (other != null && other.size > 0){
            Node<E> endNode = this.head.prev;
            Node<E> otherStartNode = other.head.next;
            Node<E> otherEndNode = other.head.prev;

            endNode.next = otherStartNode;
            otherStartNode.prev = endNode;
            head.prev = otherEndNode;
            otherEndNode.next = head;


            other.head.next = other.head;
            other.head.prev = other.head;
            size += other.size;
            other.size = 0;

        }
    }
    
    public E get( int pos ) {

        if ( pos < 0 || pos >= size ) {
            throw new IndexOutOfBoundsException( Integer.toString( pos ) );
        }

        Node<E> p = head.next;
        
        for ( int i=0; i<pos; i++ ) {
            p = p.next;
        }

        return p.value;
    }

    public void addLast( E obj ) {

        if ( obj == null ) {
            throw new IllegalArgumentException( "null" );
        }

        size++;

	Node<E> before, after;

	before = head.prev;
	after = head;

	before.next = new Node<E>( obj, before, after );
	after.prev = before.next;
    }

    public void remove( int pos ) {

        if ( pos < 0 || pos > (size-1) ) {
            throw new IndexOutOfBoundsException( Integer.toString( pos ) );
        }

        Node<E> left = head; // starts at head, not head.next!
        
        for ( int i=0; i < pos; i++ ) {
            left = left.next;
        }

        Node<E> current = left.next;
        Node<E> right = current.next;

        left.next = right;
        right.prev = left;

        size--;
    }

    public String toString() {
        String res = "[";
        String backward = "[";
        if(size > 0) {
            Node<E> p =head.next;
            Node<E> q =head.prev;
            res += p.value;
            backward += q.value;
            while(p.next != head){
                p = p.next;
                res += ", " + p.value;
                q = q.prev;
                backward += ", " + q.value;
            }
        }
        return res + "] - " + backward + "]";
    }

    public static void main(String[] args){
        LinkedList<String> xs, ys;
        xs = new LinkedList<String>();
        ys = new LinkedList<String>();

        ys.addLast("alpha");
        ys.addLast("bravo");
        ys.addLast("charlie");
        ys.addLast("delta");

        System.out.println(xs);
        System.out.println(ys);

        xs.merge(ys);

        System.out.println(xs);
        System.out.println(ys);

        ys.addLast("echo");
        ys.addLast("foxtrot");

        System.out.println(xs);
        System.out.println(ys);

        xs.merge(ys);

        System.out.println(xs);
        System.out.println(ys);
        xs.merge(ys);

        System.out.println(xs);
        System.out.println(ys);

    }
}
