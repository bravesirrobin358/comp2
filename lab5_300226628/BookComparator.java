import java.util.Comparator;

public class BookComparator implements Comparator<Book> {

    public int compare(Book book1, Book book2){
        if (book1.getAuthor() == book2.getAuthor()){
            if (book1.getTitle() == book2.getTitle()){
                if (book1.getYear() > book2.getYear()){
                    return 1;
                } else {
                    return -1;
                }
            } else { 
                int comparisonTitle = book1.getTitle().compareTo(book2.getTitle());
                if (comparisonTitle < 0){
                    return -1;
                } else {
                    return 1;
                }
            }
        } else { 
                int comparisonAuthor = book1.getAuthor().compareTo(book2.getAuthor());
                if (comparisonAuthor > 0){
                    return 1;
                } else {
                    return -1;
                }
        }
    }

}