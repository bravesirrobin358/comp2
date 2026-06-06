/**
 * @author Name (student id)
 */

public class MediaLibrary {

    public static int getMaxDiff(int[] sizes, int[] map, int k) {

        if (sizes == null || map == null) {
            throw new NullPointerException();
        }

        if (sizes.length != map.length) {
            throw new IllegalArgumentException("sizes and map must be of the same length.");
        }

        if (sizes.length < 1) {
            throw new IllegalArgumentException("sizes and map must be of length 1 or more");
        }

        if (k<1) {
            throw new IllegalArgumentException("k must be equal to or larger than 1");
        }

        int min, max;

        min = max = map[0];

        for (int i=1; i<map.length; i++) {
            if (map[i] < min) {
                min = map[i];
            }
            if (map[i] > max) {
                max = map[i];
            }
        }

        if (min < 0 || max >= k) {
            throw new IllegalArgumentException("map must contain values between 0 and (k-1)");
        }

        int[] totals = new int[k];

        for (int i =0; i<map.length; i++){
            totals[map[i]] += sizes[i];
        }

        int minTotal, maxTotal;
        minTotal = maxTotal = totals[0];

        for(int i=1; i<totals.length; i++){
            if(totals[i] < minTotal){
                minTotal = totals[i];
            }

            if(totals[i] > maxTotal){
                maxTotal = totals[i];
            }
        }
        return maxTotal - minTotal;


    }



}
