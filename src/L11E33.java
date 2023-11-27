import java.io.*;


public class L11E33 {





        public static void main(String[] args) {
            String filePath = "output2.txt";

            try {
                w1(filePath);
                System.out.println("File created successfully with integers.");
            } catch (IOException e) {
                System.err.println("Error writing to the file: " + e.getMessage());
            }
        }

        private static void w1(String filePath) throws IOException {
            try (PrintWriter out = new PrintWriter( new FileWriter(filePath))) {
                for (int i = 1; i <= 10; i++) {
                    out.println(i);

                }
            }
        }
    }


