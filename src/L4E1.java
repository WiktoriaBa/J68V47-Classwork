import java.util.*;
public class L4E1 {

    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String Name = input.nextLine();

        System.out.print("Enter your surname: ");
        String lname = input.nextLine();

        System.out.print("Enter the total value of your order: £");
        float order = input.nextFloat();

        System.out.print("Enter the amount you wish to pay as a deposit: £");
        float deposit = input.nextFloat();

        System.out.println("== RECEIPT ==");

        System.out.println("Customer: " + Name.substring(0,1) + " " + lname);

        System.out.println("Order Total £" + order);

        System.out.println("Deposit Paid £" + deposit);

        System.out.println("Remainder £" + (order - deposit));

        if (deposit > 100) {
            System.out.println("You got a free toaster!!:)");
        }
        System.out.println("Have a nice day!");






    }

}


