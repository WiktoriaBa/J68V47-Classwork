import java.util.*;
public class L5E3 {

    public static void main(String[] args) {


        int score = 0;


        for(int count=1;  count<=10; count+=1)
        {
            System.out.print("What is 2+5 = ");
            Scanner input = new Scanner(System.in);
            int bob = input.nextInt();

            if (bob == 7) {
                System.out.println("Wooo correct");
                score += 1;
            }
            else {
                System.out.println( "BOOOO incorrect");
            }
        }

        System.out.println("Here is your score = " + score);













    }
}
