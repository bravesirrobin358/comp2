/**
 * @author Name (student id)
 */

public class SparseVector {

    private static class Elem{
        private long index;
        private double value;
        private Elem next;
        private Elem prev;

        private Elem(long index, double value, Elem prev, Elem next){
            this.index = index;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
    
    private Elem head;
    private long dimension;

    public SparseVector(long dimension) {

        if(dimension < 0){
            throw new IllegalArgumentException("dimension cannot be lower than 0");
        }
        head = new Elem(-1, 0, null, null);
        head.next = head;
        head.prev = prev;
        this.dimension = dimension; 

    }

    public long getDimension() {
        
        return dimension;
        
    }

    public double get(long index) {

        if (index < 0 || index >= dimension){
            throw new IndexOutOfBoundsException();
        }
        Elem current = head.next;

        while(current != head && current.index < index){
            current = current.next;
        }
        if (current == head || current.index > index){
            return 0.0;
        } else{
            return current.value;
        }

    }

    private void addAfter(Elem before, long index, double value){
        Elem after = before.next;
        before.next = new Elem(index, value, before, after);
        after.previous = before.next;
    }

    private void removeAfter(Elem before){
        Elem after = before.next.next;
        before.next = after;
        after.previous = before;
    }

    public void set(long index, double value) {

        if (index < 0 || index >= dimension){
            throw new IndexOutOfBoundsException();
        }
        Elem current = head.next;
        while (current != head && current.index < index){
            current = current.next;
        }
        if(current == head || current.index > index){
            if(value != 0.0){
            addAfter(current.previous, index, value);
            }
        } else {
            if (value == 0.0){
                removeAfter(current.previous);
            } else {
                current.value = value;
            }
        }


    }

    public double getL1Norm() {

        double norm = 0.0;
        Elem current = head.next;

        while(current != head){
            norm += Math.abs(current.value);
            current = current.next;
        }
        return norm;
                

    }
    
    // nested class implementing the iterator
    
    public Iterator getIterator() {

        return new SparseVectorIterator();         
    }
    
    private class SparseVectorIterator implements Iterator{
        private Elem current = head;
        private int index = -1;
        public boolean hasNext(){
            return index < (dimension -1);
        }

        public double next(){
            if (!hasNext()){
                throw new IllegalStateException();
            }
            double saved = 0.0;
            if(current.next != head && (index+1) == current.next.index){
                current = current.next;
                saved = current.value;
            }
            index++;
            return saved;
        }
    }
}
