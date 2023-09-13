import java.util.*;
public class L3E2 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Q1.What is the capital of Spain? ");
        String Spain = "madrid";
        String text = input.nextLine();

        System.out.println( text.toUpperCase().toLowerCase().contains(Spain));


        System.out.print("Q2.What is the capital of the UK? ");
        String uk = "london";
        String text2 = input.nextLine();

        System.out.println( text2.toUpperCase().toLowerCase().contains(uk));

        System.out.print("Q2.What is the capital of Italy? ");
        String Italy = "rome";
        String text3 = input.nextLine();

        System.out.println( text3.toUpperCase().toLowerCase().contains(Italy));



    }
}
