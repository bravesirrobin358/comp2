import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'arrayManipulation' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */

    public long arrayManipulation(int n, List<List<Integer>> queries) {
        long[] arr = new long[n+1];
        int a;
        int b;
        int k;
        long max = 0;
        System.out.println(queries.get(0).get(1));
        System.out.println(queries.get(1).get(1));
        System.out.println(queries.get(2).get(1));
        for (int i=0; i<queries.size(); i++){
            a = queries.get(i).get(0);
            b = queries.get(i).get(1);
            k = queries.get(i).get(2);
            arr[a-1] += k;
            arr[b] -= k;
            System.out.println(a);
            System.out.println(b);
            System.out.println(k);
        }
        long current=0;
        for (int j=0; j<queries.size(); j++){
            current += arr[j];
            if(current > max){
                max = current;
            }
        }
        return max;

    }

    public static void main(String[] args) throws IOException {
        List<List<Integer>> queriesss = new ArrayList<>();
        List<Integer> query = new ArrayList<>();
        query.add(1);
        query.add(5);
        query.add(3);
        queriesss.add(query);
        System.out.println(queriesss.get(0).get(1));
        query.clear();
        query.add(4);
        query.add(8);
        query.add(7);
        queriesss.add(query);
        System.out.println(queriesss.get(1).get(1));
        query.clear();
        query.add(6);
        query.add(9);
        query.add(1);
        queriesss.add(query);
        System.out.println(queriesss.get(2).get(1));
        Result now = new Result();
        long result = now.arrayManipulation(10,queriesss);
        System.out.println(result);
    }
}
