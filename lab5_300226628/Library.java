import java.util.ArrayList;

public class Library {

    private ArrayList<Book> library = new ArrayList<Book>();

    public Book getBook(int i) {
      return library.get(i);
    }

    public int getSize() {
      return library.size();
    }

    public void addBook (Book b) {
        library.add(b);
    }

    public void sort() { 
        int position = -1;
        BookComparator bookCompare = new BookComparator();
        for (int i=0; i<getSize()-1; i++){
            Book first = library.get(i);
            for (int j=i+1; j<getSize(); j++){
                if (bookCompare.compare(library.get(i), library.get(j)) > 0){
                    position = j;
                    first = library.get(j);
                }
            }
            if (first != library.get(i)){
                Book tempOldValue = library.get(i);
                library.set(i, library.get(position));
                library.set(position, tempOldValue);
            }
        }
    }


    public void printLibrary() {
        for (Book i : library){
            System.out.println(i);
        }
    }
}