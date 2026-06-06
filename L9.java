import java.io.*; 

public class L9 { 

    public static String cat( String fileName ) { 

        FileInputStream fin = new FileInputStream( fileName ); 

        BufferedReader input = new BufferedReader( new InputStreamReader( fin ) ); 

        StringBuffer buffer = new StringBuffer(); 

        String line = null; 

        while ( ( line = input.readLine() ) != null ) { 

            line = line.replaceAll( "\\s+", " " ); 

            buffer.append( line ); 

        } 

        fin.close(); 

        return buffer.toString(); 

    } // End of cat 

        public static void main( String[] args ) { 

        System.out.println( cat( args[ 0 ] ) ); 

        } 
}