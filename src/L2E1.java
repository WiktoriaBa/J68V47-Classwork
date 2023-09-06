import java.util.*;
public class L2E1 {
    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        System.out.print("What is your name? ");
        String name = input.nextLine();

        System.out.print("What is your favourite hobby ");
        String hobby = input.nextLine();

        System.out.println( name + " picked up her pencil. She scrunched her face in concentration, tongue sticking out. " +
                "Carefully, she outlined a dog. Tail wagging, mouth smiling. " + "\n" + name + " beamed at her work before adding a big heart. " +
                hobby + " will always be her favourite thing to do." + "\n");

    }
}
