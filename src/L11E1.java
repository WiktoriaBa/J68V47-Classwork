import java.util.Scanner;
import java.io.*;
public class L11E1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Input a base number: ");
        int base = input.nextInt();
        int count;
        try (PrintWriter out = new PrintWriter( new FileWriter("output.txt") ) ) {
            for (count = 1; count <= 12; count += 1) {
            out.println(base + " x " + count + " = " + (count * base)); }
        } catch (IOException e) {
            System.out.println("Error occurred writing to file: " + e);
        }








    }
}
