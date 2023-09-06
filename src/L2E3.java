import java.util.*;
public class L2E3 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the name of item 1 ");
        String item1 = input.nextLine();

        System.out.print("Enter the price of item 1 ");
        float price1 = input.nextFloat();

        System.out.print("Enter the name of item 2 ");
        String item2 = input.nextLine();
        item2 = input.nextLine();

        System.out.print("Enter the price of item 2 ");
        float price2 = input.nextFloat();

        System.out.print("Enter the name of item 3 ");
        String item3 = input.nextLine();
        item3 = input.nextLine();

        System.out.print("Enter the price of item 3 ");
        float price3 = input.nextFloat();

        System.out.println( "\nNESMART" );

        System.out.format( item1 + "...........£%.2f \n" , price1 );
        System.out.format( item2 + "...........£%.2f \n" , price2 );
        System.out.format( item3 + "...........£%.2f \n" , price3 );

        System.out.format( "SUBTOTAL " + "........£%.2f \n" , (price1 + price2 + price3));




    }
}
