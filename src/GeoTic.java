import java.io.*;
import java.util.*;

public class GeoTic {

    // UserAccount class to store user information
    static class UserAccount {
        String username;
        String password;
        int points;
        char[][] ticTacToeBoard;
        Set<String> answeredQuestions;

        public UserAccount(String username, String password) {
            this.username = username;
            this.password = password;
            this.points = 0;
            this.ticTacToeBoard = new char[3][3];
            this.answeredQuestions = new HashSet<>();
        }
    }

    private static UserAccount isValidLogin(String enteredUsername, String enteredPassword, List<UserAccount> userAccounts, Scanner scanner) {
        UserAccount currentUser = null;

        while (currentUser == null) {
            for (UserAccount user : userAccounts) {
                if (enteredUsername.trim().equals(user.username) && enteredPassword.trim().equals(user.password)) {
                    currentUser = user;
                    break;
                }
            }

            if (currentUser == null) {
                System.out.println("Error: Invalid information. Please try again.");
                System.out.print("Username: ");
                enteredUsername = scanner.nextLine();
                System.out.print("Password: ");
                enteredPassword = scanner.nextLine();
            }
        }

        return currentUser;
    }


    private static boolean isUsernameTaken(String username, List<UserAccount> userAccounts) {
        for (UserAccount user : userAccounts) {
            if (user.username.equals(username)) {
                return true; // Username is already taken
            }
        }
        return false; // Username is available
    }

    private static void saveUserAccounts(List<UserAccount> userAccounts) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/userAccounts.txt"))) {
            for (UserAccount user : userAccounts) {
                writer.println(user.username + "," + user.password + "," + user.points);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<UserAccount> loadUserAccounts() {
        List<UserAccount> loadedAccounts = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("src/userAccounts.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    UserAccount loadedUser = new UserAccount(parts[0], parts[1]);
                    loadedUser.points = Integer.parseInt(parts[2]);
                    loadedAccounts.add(loadedUser);
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist, ignore
        }
        return loadedAccounts;
    }
    private static UserAccount getUserAccount(String username, List<UserAccount> userAccounts) {
        for (UserAccount user : userAccounts) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }
    private static boolean playQuestionGame(UserAccount currentUser, List<UserAccount> userAccounts, Scanner scanner) {

        List<String> questions = loadQuestions();
        Collections.shuffle(questions);

        for (String question : questions) {
            if (!currentUser.answeredQuestions.contains(question)) {
                String questionWithoutCorrect = question.replace(", correct", "");
                System.out.println(questionWithoutCorrect);

                char correctAnswer = getCorrectAnswer(question, false);
                System.out.print("Please type which letter has the correct answer: ");

                String userAnswer = scanner.next().toUpperCase();
                if (userAnswer.equals("A") || userAnswer.equals("B") || userAnswer.equals("C") || userAnswer.equals("D")) {
                    if (userAnswer.charAt(0) == correctAnswer) {
                        System.out.println("Correct! \n");
                        currentUser.answeredQuestions.add(question);
                        saveUserAccounts(userAccounts);
                        playTicTacToe(currentUser, scanner);
                    } else {
                        System.out.println("Incorrect! \n");
                        currentUser.answeredQuestions.add(question);
                        saveUserAccounts(userAccounts);
                        playSystemX(currentUser, scanner);
                    }
                } else {
                    System.out.println("ERROR: Invalid letter, please put in a valid letter from the answers available. \n");
                }
            }
        }
        return true;
    }
    private static List<String> loadQuestions() {
        List<String> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/Questions.txt"))) {
            StringBuilder currentQuestion = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Question: ")) {
                    if (currentQuestion.length() > 0) {
                        questions.add(currentQuestion.toString());
                        currentQuestion.setLength(0);
                    }
                    currentQuestion.append(line).append("\n");
                } else {
                    currentQuestion.append(line).append("\n");
                }
            }

            if (currentQuestion.length() > 0) {
                questions.add(currentQuestion.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions;
    }

    private static char getCorrectAnswer(String question, boolean isUser) {
        String[] lines = question.split("\n");
        for (String line : lines) {
            if (line.contains("correct")) {
                return isUser ? ' ' : line.charAt(0);
            }
        }
        return 'A';
    }


    private static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" " + (board[i][j] != '\u0000' ? board[i][j] : " ") + " ");
                if (j < board[i].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < board.length - 1) {
                for (int k = 0; k < board[i].length * 4 - 1; k++) {
                    System.out.print("-");
                }
                System.out.println();
            }
        }
    }

    private static int[] getUserMove(Scanner scanner) {
        System.out.print("Enter which row you would like to place an 'O' in (1-3): ");
        int row = scanner.nextInt();
        System.out.print("Enter which column you would like to place an 'O' in (1-3): ");
        int col = scanner.nextInt();
        System.out.println("\n");
        return new int[]{row, col};
    }

    private static boolean isValidMove(char[][] board, int[] move) {
        int row = move[0] - 1;  // Adjusting indices
        int col = move[1] - 1;  // Adjusting indices
        return row >= 0 && row < board.length && col >= 0 && col < board[row].length && board[row][col] == '\u0000';
    }

    private static boolean hasPlayerWon(char[][] board, char player) {
        // Check rows and columns
        for (int i = 0; i < board.length; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        // Check diagonals
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private static void makeSystemMove(char[][] board) {
        Random rand = new Random();
        int row, col;

        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (board[row][col] != '\u0000');

        board[row][col] = 'X';

        System.out.println("Program placed 'X' at Row " + (row + 1) + " and Column " + (col + 1));
    }

    private static void playTicTacToe(UserAccount currentUser, Scanner scanner) {
        System.out.println("Play Tic-Tac-Toe! \n");

        char[][] board = currentUser.ticTacToeBoard;
        boolean gameWon = false;
        int moves = 0;

        while (!gameWon && moves < 9) {
            printBoard(board);

            int[] move = getUserMove(scanner);

            if (isValidMove(board, move)) {
                board[move[0] - 1][move[1] - 1] = 'O';
                moves++;

                printBoard(board);

                System.out.println("\nYou placed 'O' at Row " + move[0] + " and Column " + move[1] + "\n");

                if (hasPlayerWon(board, 'O')) {
                    gameWon = true;
                    break;
                }

                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        if (gameWon) {
            System.out.println("\nCongratulations! You won!");
            currentUser.points++;
            System.out.println("1 point gained.\n");
            currentUser.ticTacToeBoard = new char[3][3];
            currentUser.answeredQuestions.clear();
        }
    }

    private static void playSystemX(UserAccount currentUser, Scanner scanner) {
        System.out.println("System plays 'X' \n");

        char[][] board = currentUser.ticTacToeBoard;

        makeSystemMove(board);
        printBoard(board);
        if (hasPlayerWon(board, 'X')) {
            System.out.println("\nBOOO you lost!\n");
            currentUser.ticTacToeBoard = new char[3][3];
            currentUser.answeredQuestions.clear();
        }

        System.out.print("\nPress Enter to continue\n");
        scanner.nextLine();
        scanner.nextLine();
    }





    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<UserAccount> userAccounts = loadUserAccounts();

        System.out.println("GeoTic \n");
        System.out.print("Hello! Please enter 'A' to login or 'B' to create an account: ");

        String option = scanner.nextLine();

        UserAccount currentUser = null;

        if (option.equalsIgnoreCase("B")) {
            System.out.println("\nCreate An Account: ");

            String newUsername;
            do {
                System.out.print("Username: ");
                newUsername = scanner.nextLine();
                if (isUsernameTaken(newUsername, userAccounts)) {
                    System.out.println("Username already taken. Please choose another one.");
                }
            } while (isUsernameTaken(newUsername, userAccounts));

            System.out.print("Password: ");
            String newPassword = scanner.nextLine();
            System.out.print("\nPress Enter to create an account");
            scanner.nextLine();

            UserAccount newUser = new UserAccount(newUsername, newPassword);
            userAccounts.add(newUser);
            saveUserAccounts(userAccounts);
            System.out.println("Account created successfully!\n");
            currentUser = newUser;
        } else if (option.equalsIgnoreCase("A")) {
            boolean isValid = false;

            while (!isValid) {
                System.out.println("\nLogin");
                System.out.print("Username: ");
                String enteredUsername = scanner.nextLine();
                System.out.print("Password: ");
                String enteredPassword = scanner.nextLine();

                currentUser = isValidLogin(enteredUsername, enteredPassword, userAccounts, scanner);
                isValid = currentUser != null;

                if (isValid) {
                    System.out.println("Login successful. Welcome, " + enteredUsername + "!\n");
                } else {
                    System.out.println("Error: Invalid information. Please try again.");
                }
            }
        } else {
            System.out.println("ERROR: invalid option");
        }

        while (currentUser != null && currentUser.points <= 3) {
            System.out.println("Welcome to GeoTic!!\n");
            playQuestionGame(currentUser, userAccounts, scanner);

            if (hasPlayerWon(currentUser.ticTacToeBoard, 'O')) {
                currentUser.points++;
            }

            saveUserAccounts(userAccounts);
        }

        if (currentUser != null) {
            System.out.println("WOOO you have reached 3 points!!");
            System.out.println("Congratulations, you have completed the game!");
            System.out.print("Press Enter to restart the game.");

            currentUser.points = 0;
            currentUser.ticTacToeBoard = new char[3][3];
            currentUser.answeredQuestions.clear();

            scanner.nextLine();

            saveUserAccounts(userAccounts);
        }

        scanner.close();
    }
}
