public class Balanced {

    static DynamicArrayStack<Character> stack; 
    public static boolean algo1( String s ) {

        int curly = 0;
        int square = 0;
        int round = 0;

        for ( int i=0; i<s.length(); i++ ) {

            char c = s.charAt( i );

            switch ( c ) {
            case '{':
                curly++;
                break;
            case '}':
                curly--;
                break;
            case '[':
                square++;
                break;
            case ']':
                square--;
                break;
            case '(':
                round++;
                break;
            case ')':
                round--;
            }
        }
        return curly == 0 && square == 0 && round == 0;
    }

    public static boolean algo2( String s ) {
        int curly = 0;
        int square = 0;
        int round = 0;

        for ( int i=0; i<s.length(); i++ ) {

            char c = s.charAt( i );
             

            if ( c =='{') {
                stack.push(c);
            } else if (c =='}') {  
                if (stack.peek() != '{'){
                    return false;
                }
                stack.pop();
            } else if (c =='[') {
                stack.push(c);
            } else if (c ==']') {
                if (stack.peek() != '['){
                    return false;
                }
                stack.pop();
            } else if (c =='(') {
                stack.push(c);
            } else if (c ==')') {
                if (stack.peek() != '('){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();

    }

    public static void main( String[] args ) {
        stack = new DynamicArrayStack<Character>(25);
        for ( int i=0; i<args.length; i++ ) {
            System.out.println( "algo1( \"" + args[ i ] + "\" ) -> " + algo1( args[ i ] ) );
        }
        for ( int i=0; i<args.length; i++ ) {
            System.out.println( "algo2( \"" + args[ i ] + "\" ) -> " + algo2( args[ i ] ) );
        }
    }
}