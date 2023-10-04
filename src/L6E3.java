import java.util.*;
public class L6E3 {

    public static void main(String[] args) {


        int score = 0;
        int bob = 7;
        int hearts = 3;
        int count = 1;


        do {

            System.out.print(" What is 5 + 2 = ");
            count++;
                Scanner input = new Scanner(System.in);
                bob = input.nextInt();

                if (bob == 7) {
                    System.out.println("Wooo correct");
                    score += 1;
                } else {
                    System.out.println("BOOOO incorrect");
                    hearts -= 1;
                }






            }
            while (count <=10 && hearts!=0);

            System.out.println("Here is your score = " + score);

            if (count >= 10) {

                System.out.println("WOo well done");

            }




    }
}
