/**
 * COPYRIGHT MATERIAL -- DO NOT DISTRIBUTE
 *
 * @author Mehrdad Sabetzadeh
 * 
 * Name: Yannick Vaillancourt
 * Student id: 300226628 
 */

public class IntegerUtil {

    public static Integer[] sumRows (Integer[][] matrix) {
    
        if (matrix == null) {
            return null;
        }
        
        Integer[] result = new Integer[matrix.length];
        for (int i=0; i<matrix.length; i++){
            Integer sum = 0;
            for (int j=0; j<matrix[i].length; j++){
                if (matrix[i][j] == null){
                    continue;
                }
                sum += matrix[i][j];
            } 
            result[i] = sum;
        }
        return result;
        
        
    }
}