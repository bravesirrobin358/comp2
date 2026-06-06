import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {

    private static class Node<T> {

        private final T value;
        private Node<T> prev;
        private Node<T> next;

        private Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private final Node<E> head;
    private int size;

    public LinkedList() {
        head = new Node<E>(null, null, null); // dummy node!
        head.prev = head.next = head;
        size = 0;
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node<E> current;

        public LinkedListIterator() {
            current = head;
            while (hasNext()) {
                System.out.println(nextIndex());
                System.out.println(next());
            }
        }

        public LinkedListIterator(int nextIndex) {
            current = head;
            for (int i = 0; i < nextIndex; i++) {
                current = current.next;
            }
            while (hasNext()) {
                System.out.println(nextIndex());
                System.out.println(next());
            }
        }

        public boolean hasNext() {
            return (current.next != head);
        }

        public E next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            current = current.next;

            return current.value;
        }

        public int nextIndex() {
            int i = 0;
            Node<E> temp = head;
            while (temp != current) {
                i++;
                temp = temp.next;
            }
            return i;
        }
    }

    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    public Iterator<E> iterator(int nextIndex) {
        if (nextIndex < 0 || nextIndex >= size) {
            throw new IndexOutOfBoundsException();
        }
        return new LinkedListIterator(nextIndex);
    }

    public Iterator<E> iterator(Iterator<E> other) {
        if (other == null) {
            throw new NullPointerException();
        }
        return new LinkedListIterator(other.nextIndex());
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return false;
    }

    public E get(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        Node<E> p = head.next;

        for (int i = 0; i < index; i++) {
            p = p.next;
        }

        return p.value;
    }

    public void addFirst(E elem) {

        if (elem == null) {
            throw new NullPointerException();
        }

        Node<E> second = head.next;

        head.next = new Node<E>(elem, head, second);
        second.prev = head.next;

        size++;
    }


    public void add(int index, E elem) {
        if (elem == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        //Create temp node and traverse through list to desired index
        Node<E> temp = head;

        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        // Add node elem
        temp.next = new Node<>(elem, temp, temp.next.next);
        temp.next.next.prev = temp.next;
    }

    public void add(E elem) {

        if (elem == null) {
            throw new NullPointerException();
        }

        Node<E> before = head.prev, after = head;

        before.next = new Node<E>(elem, before, after);
        after.prev = before.next;

        size++;
    }

    public E remove(int index) {
        return null;
    }

    public boolean remove(E o) {
        return false;
    }

}
