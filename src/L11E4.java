import java.io.*;
public class L11E4 {



        public static void main(String[] args) {
            String filePath1 = UserInput("Enter the path of the first text file: ");
            String filePath2 = UserInput("Enter the path of the second text file: ");

            try {
                boolean same = compareFiles(filePath1, filePath2);

                if (same) {
                    System.out.println("The contents of the files are identical.");
                } else {
                    System.out.println("The contents of the files are not identical.");
                }
            } catch (IOException e) {
                System.err.println("Error reading the files: " + e.getMessage());
            }
        }

        private static String UserInput(String prompt) {
            System.out.print(prompt);
            try {
                BufferedReader in = new BufferedReader(new java.io.InputStreamReader(System.in));
                return in.readLine();
            } catch (IOException e) {
                System.out.println("Error reading user input: ");
                return prompt;
            }
        }

        private static boolean compareFiles(String filePath1, String filePath2) throws IOException {
            try (BufferedReader in = new BufferedReader(new FileReader(filePath1));
                 BufferedReader in1 = new BufferedReader(new FileReader(filePath2))) {

                String line1, line2;
                while ((line1 = in.readLine()) != null && (line2 = in1.readLine()) != null) {
                    if (!line1.equals(line2)) {
                        return false;
                    }
                }

                return in.readLine() == null && in1.readLine() == null;
            }
        }
    }






