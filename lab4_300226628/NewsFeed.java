/* *
 * Use static array for NewsFeed
 * with constant MAX_SIZE
 * */

public class NewsFeed {

    private Post[] messages;
    private int size;
    public static final int MAX_SIZE = 25;

    public NewsFeed() {
    	this.messages = new Post[MAX_SIZE];
    	this.size = 0;
    }

    public void add(Post message) {
      if (size != 25){
					this.messages[this.size()] = message;
      		this.size++;
      }
    }

    public Post get(int index) {
	     return messages[index];
    }

    public int size() {
	     return size;
    }

	  public void sort(){
			int i, j, argMin;
			Post tmp;
			for (i = 0; i < size - 1; i++) {
				argMin = i;
				for (j = i + 1; j < size(); j++) {
					if (messages[j].compareTo(messages[argMin]) < 0) {
						argMin = j;
					}
				}

  			tmp = messages[argMin];
  			messages[argMin] = messages[i];
  			messages[i] = tmp;
		  }

	  }

  	public NewsFeed getPhotoPost(){
  		NewsFeed photoPost = new NewsFeed();
  		for (int w=0; w<this.size(); w++){
  			String str = this.get(w).getClass().getName(); 
  			if(str == "PhotoPost"){
  				photoPost.add(this.get(w));
  			}
  		}
  		
  		return photoPost; 
  	}

  	public NewsFeed plus(NewsFeed other){
  		NewsFeed combined = new NewsFeed();
  		combined.messages = this.messages;
  		for (int y=0; y<this.size(); y++){
  				combined.add(this.get(y));
  		}

  		for (int x=0; x<other.size(); x++){
  			if (combined.size()<26){
  				combined.add(other.get(x));
  			}
  		}
  		
  		combined.sort();
  		return combined;
  	}

}
