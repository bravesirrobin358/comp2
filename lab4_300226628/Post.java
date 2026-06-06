import java.util.Calendar;
import java.util.Date;

public class Post implements Likeable, Comparable<Post> {

    protected int likes;
    private Date timeStamp;
    private String userName;

    public Post(String userName) {
      this.userName = userName;
      this.timeStamp = Calendar.getInstance().getTime();
      this.likes = 0;
    }

    public String getUserName() {
	     return this.userName;
    }

    public Date getTimeStamp() {
	     return this.timeStamp;
    }

    public void like(){
      this.likes++;
    }

    public int getLikes(){
      return this.likes;
    }

    // Implement the methods required by the interface Likeable.
    // This file will not compile unless they are present with the correct name and signature.

    public String toString() {
    	String str = new String();
    	str = getClass().getName() + ": " + timeStamp + ", " + userName + ", likes = " + likes;
    	return  str;
    }


  	public int compareTo(Post other){
  		if (this.getTimeStamp().after(other.getTimeStamp()) == true){
        return 1;
      } else if (this.getTimeStamp().before(other.getTimeStamp()) == true){
        return -1;
      } else{
        return 0;
      }
  	}

  	public boolean isPopular(){
  		if (this.likes > 100){
        return true;
      }else{
        return false;
      }
  	}

}
