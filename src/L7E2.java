import java.util.*;
public class L7E2 {


    public static void E(){
        String o = "Enter the first number: ";
        System.out.print(o);
    }
    public static void P(){
        String o1 = "Enter the second number: ";
        System.out.print(o1);
    }

    public static void L(int num1, int num2){
        String o2 = "The sum of " + num1 + " and " + num2 + " = " + (num1 + num2);
        System.out.println(o2);
    }

    public static int num(){
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
    public static int num2(){
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public static void main(String[] args) {

        E();
        int num1 = num();
        P();
        int num2 = num2();
        L(num1, num2);

    }








}

