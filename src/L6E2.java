import java.util.*;

public class L6E2 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int Num = 4;

        do {
            System.out.println( "1) Play me a song \n" + "2) Play me a movie \n" + "3) Show me a pretty picture \n" + "4) Quit \n");
            System.out.print("Please type in the number of the option you want to select = ");
            Num = input.nextInt();
            System.out.println( "Option Selected = " + Num + "\n");
        }

        while ( Num != 4);












    }
}
