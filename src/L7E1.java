import java.util.*;
public class L7E1 {
    public static void sm(){


        String op1 = "1. Say hello";
        System.out.println(op1);

        String op2 = "2. Tell me the time";
        System.out.println(op2);

        String op3 = "3. Tell me a joke";
        System.out.println(op3);

        String op4 = "4. Quit";
        System.out.println(op4);

    }
     public static void op1(){
         System.out.println("HELLO!");
     }

     public static void op2(){
         System.out.println("The time is now.");
     }

     public static void op3(){
         System.out.println("Knock knock. Who's there? ....... long pause ........ Java");
     }

     public static void op4(){
         System.out.println("Bye!");
     }

     public static void noop(){
         System.out.println("This isn't a valid option. Try a number between 1 and 4.");
     }

     public static int getOption(){
         Scanner scanner = new Scanner(System.in);
         System.out.print("Input an option (1-4): ");
         int option = scanner.nextInt();
         System.out.format("You chose option %d %n", option);
         return option;
     }


    public static void main(String[] args) {
        int option = 0;




        do {

            sm();
            option = getOption();

            if (option == 1){
                op1();
            } else if (option == 2){
                op2();
            } else if (option == 3){
                op3();
            } else if (option == 4){
                op4();
            } else {
                noop();
            }
        } while (option != 4);

    }








}
