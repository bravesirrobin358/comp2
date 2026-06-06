import java.util.EmptyStackException;
public class DynamicArrayStack<E> implements Stack<E> {

    // Instance variables

    private E[] elems;  // Used to store the elements of this ArrayStack
    private int top;    // Designates the first free cell
    private static final int DEFAULT_INC = 25;   //Used to store default increment / decrement

    @SuppressWarnings( "unchecked" )

    // Constructor
    public DynamicArrayStack( int capacity ) {
        if (capacity < DEFAULT_INC){
            elems = (E[]) new Object[DEFAULT_INC];
        } else {   
            elems = (E[]) new Object[capacity];
        }
        this.top = 0;
    }

    // Gets current capacity of the array
    public int getCapacity() {
        return elems.length;
    }

    // Returns true if this DynamicArrayStack is empty
    public boolean isEmpty() {
        return ( top == 0 );
    }

    // Returns the top element of this ArrayStack without removing it
    public E peek() throws EmptyStackException {

        // pre-conditions: ! isEmpty()
        if (elems[top] == null){
            throw new EmptyStackException();
        }
        return elems[ top-1 ];
    }

    @SuppressWarnings( "unchecked" )

    // Removes and returns the top element of this stack
    public E pop() throws EmptyStackException{
        if (elems[top] == null){
            throw new EmptyStackException();
        }
        E last = elems[top -1];
        elems[top-1] = null;
        top--;
        if((top + DEFAULT_INC) <= getCapacity()){
            E[] temp = (E[]) new Object[getCapacity() - DEFAULT_INC];
            for (int i=0; i<temp.length-1; i++){
                temp[i] = elems[i];
            }
            elems = (E[]) new Object[temp.length];
            for (int i=0; i<temp.length; i++){
                elems[i] = temp[i];
            }
        }

        return last;
    }

    @SuppressWarnings( "unchecked" )

    // Puts the element onto the top of this stack.
    public void push( E element ) {
        if (top == elems.length){
            E[] temp = (E[]) new Object[getCapacity() + DEFAULT_INC];
            for (int i=0; i<elems.length; i++){
                temp[i] = elems[i];
            }
            elems = (E[]) new Object[temp.length];
            for (int i=0; i<temp.length; i++){
                elems[i] = temp[i];
            }
        }
        elems[top] = element;
        top++;
        return;
    }

    @SuppressWarnings( "unchecked" )

    public void clear() {
        elems = (E[]) new Object[DEFAULT_INC];
        top = 0;
        return;
    }

    public static void main(String[] args){
        int numb = 0;
        DynamicArrayStack<Integer> myStack = new DynamicArrayStack<Integer>(25);
        for (int i = 0; i < 26; i++) {
          myStack.push(i);
        }
        System.out.println(myStack.peek() + " top ");
        myStack.pop();
        System.out.println(myStack.peek() + " top2");
        myStack.pop();
        System.out.println(myStack.peek() + " top3");
        System.out.println(myStack.getCapacity() + "yes");
    }

}