import java.util.Scanner;

public class Print{
	public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            System.out.println("================================");
            for(int i=0;i<3;i++){
                String s1=sc.next();
                int x=sc.nextInt();
                int len = s1.length();
                int zero;
                if(x>99){
                    zero = 0;
                } else if(x<10){
                    zero = 2;
                } else{
                    zero =1;
                }
                System.out.printf("%-15s", s1);
                String xstr = "" + x;
                for (int j=0; j<zero; j++){
                	xstr = "0" + xstr;
                }
                System.out.print(xstr);
                System.out.println("");
            }
            System.out.println("================================");

    }
}