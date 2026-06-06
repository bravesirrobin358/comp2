import java.io.*;
public class Test{
	public static void main(String[] args)
		throws IOException, FileNotFoundException {
			BufferedReader input = new BufferedReader(
            new InputStreamReader(
                new FileInputStream("data")));
        	String s = input.readLine();
        	System.out.println(s);
	}
}