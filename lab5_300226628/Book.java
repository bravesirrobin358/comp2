public class Book {

    public String author;
    public String title;
    public int year;


    public Book (String author, String title, int year) {
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public boolean equals(Object other) {
        if (other == null){
            return false;
        } else if (getClass() != other.getClass()){
            return false;
        } else {
            Book second = (Book) other;
            boolean title = false;
            boolean author = false;
            boolean year = false;

            if (this.getYear() == second.getYear()){
                year = true;
            }
            if (this.getAuthor() == null || second.getAuthor() == null){
                if (this.getAuthor() == second.getAuthor()){
                    author = true;
                } else{
                    return false;
                }
            } else if (this.getAuthor().equals(second.getAuthor())){
                author = true;
            }
            if (this.getTitle() == null || second.getTitle() == null){
                if (this.getTitle() == second.getTitle()){
                    title = true;
                } else{
                    return false;
                }
            } else if (this.getTitle().equals(second.getTitle())){
                title = true;
            }
            if (year == author == title == true){
                return true;
            } else {
                return false;
            }
        }

        
    }

    public String toString() {
        return author + ":" + title + "("+year+")";
    }


}

