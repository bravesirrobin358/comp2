public class ITIStringBuffer {

    private boolean stringUpdated;
    private String prevString;
    private SinglyLinkedList<String> strList;
    private int stringSize;
    private int currentIndex;
    
    public ITIStringBuffer() {
        strList = new SinglyLinkedList<String>();
        stringUpdated = false;
        prevString = "";
        stringSize = 0;

    }

    public ITIStringBuffer(String  firstString){
        this();
        append(firstString);
    }

    public void append(String nextString){
        strList.add(nextString);
        stringSize += nextString.length();
        stringUpdated = true;
    }

    public String toString(){

        if (stringUpdated == false){
            return prevString;
        } else {
            char[] newStrAsChars = new char[stringSize];
            currentIndex = 0;
            char[] prevStrAsChars = prevString.toCharArray();
            for (char c: prevStrAsChars){
                newStrAsChars[currentIndex] = c;
                currentIndex++;
            }
            
            while (!strList.isEmpty()){
                char[] currentStrAsChars = strList.removeFirst().toCharArray();
                for (char c:currentStrAsChars){
                    newStrAsChars[currentIndex] = c;
                    currentIndex++;
                }
            }

            stringUpdated = false;
            prevString = new String(newStrAsChars);
            return prevString;
        }
    }

}
