import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class Dictionary implements Map<String, Integer> {

    private final static int INITIAL_CAPACITY = 10;
    private final static int INCREMENT = 5;
    private int count;

    private Pair[] elems;

    public int getCount() {
      return count;
    }

    public int getCapacity() {
      return elems.length;
    }

    public Dictionary() {
        count = 0;
        elems = new Pair[INITIAL_CAPACITY];
    }

    @Override
    public void put(String key, Integer value) throws NullPointerException{
        if (key == null || value == null) {
            throw new NullPointerException("key or value is null");
        }
        if (getCount() == getCapacity()){
            increaseCapacity();
        }
        elems[getCount()] = new Pair(key, value);
        count++;
        return;
    }

    private void increaseCapacity() {
        Pair[] temp = new Pair[getCapacity() + INCREMENT];
            for (int i=0; i<elems.length; i++){
                temp[i] = elems[i];
            }
            elems = new Pair[temp.length];
            for (int i=0; i<temp.length; i++){
                elems[i] = temp[i];
            }
    }

    @Override
    public boolean contains(String key) throws NullPointerException{
        if (key == null) {
            throw new NullPointerException("key is null");
        }
        for (int i=0; i<getCapacity(); i++){
            if (elems[i] != null){
                if (elems[i].getKey().equals(key)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Integer get(String key) throws NullPointerException, NoSuchElementException{
        if (key == null) {
            throw new NullPointerException("key or value is null");
        } 
        for (int i=getCount()-1; i>-1; i--){
            if (elems[i] != null){
                if (elems[i].getKey().equals(key)){
                    return elems[i].getValue();
                }
            }
        }
        throw new NoSuchElementException("element is not in elems");
    }

    @Override
    public void replace(String key, Integer value) throws NullPointerException, NoSuchElementException{
        if (key == null) {
            throw new NullPointerException("key or value is null");
        }
        for (int i=getCount()-1; i>-1; i--){
            if (elems[i].getKey().equals(key)){
                elems[i].setValue(value);
                return;
            }
        }
        throw new NoSuchElementException("element is not in elems");
    }

    @Override
    public Integer remove(String key) throws NullPointerException, NoSuchElementException{
        int value;
        if (key == null) {
            throw new NullPointerException("key or value is null");
        }
        for (int i=getCount()-1; i>-1; i--){
            if (elems[i].getKey().equals(key)){
                value = elems[i].getValue();
                elems[i] = null;
                count--;
                return value;
            }
        }
        throw new NoSuchElementException("element is not in elems");
    }

    @Override
    public String toString() {
      String res;
      res = "Dictionary: {elems = [";
      for (int i = count-1; i >= 0 ; i--) {
          res += elems[i];
          if(i > 0) {
              res += ", ";
          }
      }
      return res +"]}";
    }

}