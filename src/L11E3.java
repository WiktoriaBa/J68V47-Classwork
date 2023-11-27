import java.io.*;
public class L11E3 {


        public static void main(String[] args) {
            String filePath = "output2.txt";

            try {
                w1(filePath);
                System.out.println("File created successfully.");
            } catch (IOException e) {
                System.out.println("Error: writing to the file: ");
            }
            try {
                int sum = s1(filePath);
                System.out.println("The sum of numbers in the file is: " + sum);
            } catch (IOException e) {
                System.out.println("Error: reading the file. ");
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid data in the file. Make sure it is numbers.");
            }
        }

    private static void w1(String filePath) throws IOException {
        try (PrintWriter out = new PrintWriter( new FileWriter(filePath))) {
            for (int i = 1; i <= 20; i++) {
                out.println(i);

            }
        }
    }
        private static int s1(String filePath) throws IOException {

            try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {
                String line;
                int sum = 0;

                while ((line = in.readLine()) != null) {
                    sum += Integer.parseInt(line.trim());
                }

                return sum;
            }
        }
    }






