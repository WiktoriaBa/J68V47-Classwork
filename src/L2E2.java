import java.util.*;
public class L2E2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("First number ");
        int num = input.nextInt();

        System.out.print("Second number ");
        int numb = input.nextInt();

        float divResult = (float) num / (float) numb;

        System.out.println( num + " + " + numb + " = " + (num + numb));
        System.out.println( num + " - " + numb + " = " + (num - numb));
        System.out.println( num + " x " + numb + " = " + (num * numb));
        System.out.println( num + " / " + numb + " = " + (divResult));
        System.out.println( num + " % " + numb + " = " + (num % numb));
        System.out.println( num + " ^ " + numb + " = " + (int) Math.pow(num,numb));

    }
}
