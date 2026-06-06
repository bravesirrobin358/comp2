/**
 * this class represents a tool that 
 * replaces specific elements of a String[]
 * <p>
 * @author Yannick Vaillancourt
 * */
public class Utils {

    /**
     * Returns a copy of the array 'in' where each word occurring in the array
     * 'what' has been replaced by the word occurring in the same position
     * in the array 'with'.
     *
     * @param in an array of Strings;
     * @param what an array of words to be replaced;
     * @param with an array of replacement words;
     * @return a new array idententical to 'in' except that all the occurrences of words
     * found in 'what' have been replaced by the corresponding word from 'with'.
     */

    public static String[] findAndReplace( String[] in, String[] what, String[] with ) {

        String[] out = null; // The new array to be returned
	      boolean valid = true; // True if the pre-conditions are satistified

      	// Testing pre-conditions
        // Pre-conditions include: no parameters may be null, 
        // no parameters may include null as an element,
        // arrays 'what' and 'with' must be the same length 

      	if ( in == null || what == null || with == null ) {
      	    valid = false;
      	} else {
            if (with.length != what.length){
                return null;
            }
            for (int p=0; p<in.length; p++){
                if (in[p] == null){
                    valid = false;
                }
            }
            for (int j=0; j<with.length; j++){
                if (with[j] == null){
                    valid = false;
                }
            }
            for (int k=0; k<what.length; k++){
                if (what[k] == null){
                    valid = false;
                }
            }
            
      	}


        // Cycles through each word in array 'in', and if it is in 'what',
        // then the word in 'out' of the same position is the word in 'with'
        // that shares the same position as the word found in 'what', but 
        // only the first instance of that word in 'what'.
        
      	if ( valid ) {
            out = new String[ in.length ];
            for ( int i=0; i<in.length; i++ ) {
                for (int j=0; j<what.length; j++) {
                    if (in[i].equals(what[j])) {
                        out[i] = with[j];
                        break;
                    }
                }
        // Otherwise, the word in 'out' at the current position becomes the 
        // word with the same position from 'in'. 
                if (out[i] == null) {
                    out[i] = in[i];
                }
            }
        }
        // Returning a reference to the newly created array that
        // contains the same entries as 'in' except that all the
        // occurrences of words from 'what' have been replaced by
        // their corresponding occurrence from 'with'.

        return out;
    }
}