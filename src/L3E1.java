import java.util.*;
public class L3E1 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter your first name ");
        String Fname = input.nextLine();

        System.out.print("Enter your last name ");
        String Lname =  input.nextLine();

        System.out.print("Enter your year of birth YYYY ");
        int Birth =  input.nextInt();

        System.out.println( "Username: " + Fname.substring(0,1) + Lname.toLowerCase());

        System.out.println( "Lastname: " + Lname.substring(0,1).toLowerCase() + Fname + Birth);


    }
}



