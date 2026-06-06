public class CircularDynamicArrayList<E> {

    private int first, last;
    private E[] array;
    private int size;
    private  int capacity = 100;

    @SuppressWarnings("unchecked")
    public CircularDynamicArrayList(){
        array = (E[])new Object[capacity];
        first = last  = 0;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public CircularDynamicArrayList(int capacity){
        if(capacity < 1){
            System.out.println("Minimum capacity is 1");
            capacity = 1;
        }
        this.capacity = capacity;
        array = (E[])new Object[capacity];
        first = last  = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull(){
        return size == capacity;
    }

    @SuppressWarnings("unchecked")
    private void ensureSpace(){
        if(isFull()){
            Object[] newArray = new Object[2*capacity];

            for(int i=0; i<capacity; i++){
                newArray[i] = array[(first+i)%capacity];
            }
            first = 0;
            last = capacity-1;
            capacity = 2*capacity;
            array = (E[])newArray;
        }
    }

    public void addFirst(E newElement){
        if(newElement == null){
            throw new NullPointerException();
        }
        ensureSpace();
        if(isEmpty()){
            first = last = 0;
        } else {
            first = ((--first + capacity)%capacity);
        }
        array[first] = newElement;
        size++;
    }

    public E removeFirst(){
        if(isEmpty()){
            throw new IllegalArgumentException();
        }
        E removedElement;
        removedElement = array[first];
        array[first] = null;
        first = (first+1)%capacity;
        size--;
        return removedElement;
    }

    public void add(E newElement, int index){
        if(0 > index || index > size){
            throw new IndexOutOfBoundsException();
        }
        if (newElement == null){
            throw new NullPointerException();
        }
        if(isEmpty()){
            addFirst(newElement);
        } else{
            ensureSpace();
            int currentIndexLocation = (first+index)%capacity;
            int i = (last+1)%capacity;
            while(i != currentIndexLocation){
                array[i] = array[(i-1+capacity)%capacity];
                i--;
            }
            
            size++;
            last = (last+1)%capacity;
            array[currentIndexLocation] = newElement;

        }
    }

    public E remove(int index){
        E removedElement;
        if(0 > index || index >= size){
            throw new IndexOutOfBoundsException();
        }
        if (index == 0){
            removedElement = removeFirst();
        } else {
            removedElement = array[index];
            int currentIndexLocation = index;
            while(currentIndexLocation != (last-1)){
                array[currentIndexLocation] = array[currentIndexLocation+1];
                currentIndexLocation++;
            }
            array[last] = null;
            last--;
            size--;
        }
        return removedElement;
    }


    public String toString() {
        StringBuffer res = new StringBuffer();
        res.append("<");
        if(!isEmpty()){
            res.append(array[first]);
            for(int i = 1; i < size; i++){
                res.append(" " + array[(first + i)%capacity]);
            }
        }
        res.append(">");
        return res.toString();
    }

    public static void main(String[] args) {
        CircularDynamicArrayList<Integer> l = new CircularDynamicArrayList<Integer>();
        System.out.println(l);
        l.addFirst(1);
        System.out.println(l);
        System.out.println("Remove: " + l.removeFirst());
        System.out.println(l);
        for(int i = 1 ; i < 11; i++) {
            l.addFirst(i);
            System.out.println(l);
        }
        for(int i = 0 ; i < 10; i++) {
            System.out.println("Remove: " + l.removeFirst());
            System.out.println(l);
        }
        
        l = new CircularDynamicArrayList<Integer>();
        System.out.println(l);
        l.add(1,0);
        System.out.println(l);
        System.out.println("Remove: " + l.remove(0));
        System.out.println(l);
        l.add(1,0);
        System.out.println(l);
        System.out.println("Remove: " + l.remove(0));
        System.out.println(l);
        l.add(1,0);
        System.out.println(l);
        System.out.println("Remove: " + l.remove(0));
        System.out.println(l);
        l.add(1,0);
        System.out.println(l);
        l.add(3,1);
        System.out.println(l);
        l.add(2,1);
        System.out.println(l);
        l.add(4,3);
        System.out.println(l);
        l.add(0,0);
        System.out.println(l);
        l.add(5,5);
        System.out.println(l);
        l.add(7,6);
        System.out.println(l);
        l.add(6,6);
        System.out.println(l);
        System.out.println("Remove: " + l.remove(0));
        System.out.println(l);
        System.out.println("Remove: " + l.remove(6));
        System.out.println(l);
        System.out.println("Remove: " + l.remove(3));
        System.out.println(l);
        System.out.println("Remove: " + l.remove(3));
        System.out.println(l);
        System.out.println("Remove: " + l.remove(3));
        System.out.println(l);
        System.out.println("Remove: " + l.removeFirst());
        System.out.println(l);
        for(int i = 1 ; i < 51; i++) {
            l.add(i,2);
            System.out.println(l);
        }
        for(int i = 1 ; i < 52; i++) {
            System.out.println("Remove: " + l.remove(1));
            System.out.println(l);
            
        }
        System.out.println("Remove: " + l.remove(0));
        System.out.println(l);

        l = new CircularDynamicArrayList<Integer>(0);
        System.out.println(l);
        l.add(1,0);
        System.out.println(l);
        
    }

}
    
