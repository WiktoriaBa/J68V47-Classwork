import java.util.*;
public class L4E2 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print(" Q1. What is the capital of Spain? ");
        String Madrid = input.nextLine();

       if (!Madrid.toLowerCase().equals("madrid")) {

           System.out.println("This is incorrect, the correct answer is Madrid");
       }

       System.out.print(" Q2. What is the capital of the UK? ");
       String london = input.nextLine();

       if (london.toLowerCase().equals("london")) {

       } else {
           System.out.println("This is incorrect, the correct answer is London");
       }

       System.out.print(" Q3. What is the capital of Italy? ");
       String rome = input.nextLine();

       if (rome.toLowerCase().equals("rome")) {

       } else {
           System.out.println("This is incorrect, the correct answer is Rome");
       }









    }




}
