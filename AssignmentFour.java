import java.util.*;

public class AssignmentFour{
    public static void main(String[] args){
        // input ex
        Scanner sc=new Scanner(System.in);
        ArrayList<String> deck=new ArrayList<String>();
        String[] ranks= {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits={" of spades", " of diamonds", " of hearts", " of clubs"};

        for (String suit:suits){
            for (String rank:ranks){
                String card = rank + suit; 
                deck.add(card);
            }
        }
        deck.remove(49);
        Collections.shuffle(deck);
        System.out.println(deck);
        ArrayList<String> dealer=new ArrayList<String>();
        ArrayList<String> other=new ArrayList<String>();

        for (int i=0;i<25;i++){
            dealer.add(deck.get(i));
        }
        for (int i=25;i<51;i++){
            other.add(deck.get(i));
        }

        System.out.println("Hello. My name is Robot and I am the dealer.");
        System.out.println("Welcome to my card game!");
        System.out.println("Your current deck of cards is:\n");
        PrintDeck(other);
        System.out.println("Do not worry. I cannot see the order of your cards");
        System.out.println("Now discard all the pairs from your deck. I will do the same.");
        WaitForPlayer();
        ArrayList<String> human = RemovePairs(other);
        ArrayList<String> robot = RemovePairs(dealer);
        boolean humanTurn = true;
        while ((human.size() != 0) && (robot.size() != 0)){
            if (humanTurn==true){
                System.out.println("***********************************");
                System.out.println("Your current deck of cards is:\n");
                PrintDeck(human);
                System.out.println("I have "+human.size()+" cards. If 1 stands for my first card and "+robot.size()+" for my last card, which of my cards would you like?");
                int pick = GetValidInput(robot.size());
                System.out.println("You asked for card "+pick);
                System.out.println("Here it is. It is "+robot.get(pick));
                System.out.println("With "+robot.get(pick)+" added, your deck now is");
                human.add(robot.get(pick)); 
                PrintDeck(human);
                robot.remove(robot.get(pick));
                System.out.println("And after discarding pairs shuffling, your deck is:\n");
                RemovePairs(human);
                if (human.size() != 0){
                    PrintDeck(human);
                }else{
                    System.out.println("...empty");
                }
                humanTurn = false;
                WaitForPlayer();
            }
            else{
                System.out.println("***********************************");
                Random randint = new Random();
                int random = randint.nextInt(0, human.size()-1);
                robot.add(human.get(random));
                human.remove(random);
                Collections.shuffle(robot);
                System.out.println("I took your card "+random);
                humanTurn = true;
                RemovePairs(robot);
                WaitForPlayer();
            }
        }
        if (human.size() == 0){
            System.out.println("You win");
        }else{
            System.out.println("You lose");
        }
    }

    public static ArrayList<String> RemovePairs(ArrayList<String> deck){
        ArrayList<String> noPairs=new ArrayList<String>();
        int i = 0;
        int e = 0;
        while (i < (deck.size()-1)){
            e = i + 1;
            while (e < deck.size()){
                String first = deck.get(i);
                String next=deck.get(e);
                if (first.charAt(0)==next.charAt(0)){
                    deck.remove(e);
                    deck.remove(i);
                    i = 0;
                    e = i;
                }
                e++;
            }
            i++;
        }
        Collections.shuffle(deck);
        return deck;
    }

    public static void PrintDeck(ArrayList<String> deck){
        for (String card : deck){
            System.out.print(card + " ");
        }
    }

    public static int GetValidInput(int length){
        Scanner sc=new Scanner(System.in);
        System.out.println("Give me an integer that is between 1 and "+length);
        int choice =sc.nextInt();
        while (0<1){
            if ((0 < choice && choice < length+1) || (length == 1 && choice == 1)){
                return choice-1;
            } else{
                System.out.println("Invalid number. Give me an integer that is between 1 and "+length);
                choice =sc.nextInt();
            }
        }
    }


    public static void WaitForPlayer(){
        Scanner sc=new Scanner(System.in);
        System.out.println("\nPress enter to continue. ");
        String s2=sc.nextLine();

    }

    
}