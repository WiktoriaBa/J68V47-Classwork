import java.time.Year;
import java.util.*;
public class L3E3 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Loan amount: £");
        Float Loan = input.nextFloat();

        System.out.print("Interest Rate (APR %): ");
        Float Rate = input.nextFloat();

        System.out.print("Number of years ");
        Float Years = input.nextFloat();

        System.out.print("Monthly payment for this loan =£" + Loan *( Rate /100 /12 / (1- (float) Math.pow((1+(Rate/100/12)),(-Years*12)))));



    }
}
