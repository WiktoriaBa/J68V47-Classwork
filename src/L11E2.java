import java.io.*;

public class L11E2 {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader( new FileReader("output.txt") );
            String line;
            do {
                line = in.readLine();
                if (line != null) {
                    System.out.println(line);
                }
            } while (line != null);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
