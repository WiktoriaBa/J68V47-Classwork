package Project;
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

        // Constructor for creating a new user account
        private UserAccount(String username, String password) {
            // Initialize username and password
            this.username = username;
            this.password = password;

            // Initialize points to zero
            this.points = 0;

            // Initialize the Tic-Tac-Toe board as a 3x3 grid
            this.ticTacToeBoard = new char[3][3];

            // Initialize the set of answered questions
            this.answeredQuestions = new HashSet<>();
        }
    }


    // Validates user login credentials
    private static UserAccount isLoginValid(String enteredUsername, String enteredPassword, List<UserAccount> userAccounts, Scanner scanner) {
        UserAccount currentUser = null;

        // Loop until valid credentials are entered
        while (currentUser == null) {
            for (UserAccount user : userAccounts) {
                // Check if entered credentials match any user account
                if (enteredUsername.trim().equals(user.username) && enteredPassword.trim().equals(user.password)) {
                    currentUser = user;
                    break;
                }
            }

            if (currentUser == null) {
                // Prompt for new credentials if not found
                System.out.println("Error: Invalid information. Please try again.");
                System.out.print("Username: ");
                enteredUsername = scanner.nextLine();
                System.out.print("Password: ");
                enteredPassword = scanner.nextLine();
            }
        }

        return currentUser;
    }

    // Checks if a username is already taken
    private static boolean isUsernameTaken(String username, List<UserAccount> userAccounts) {
        // look through existing user accounts
        for (UserAccount user : userAccounts) {
            // Check if the username is already taken
            if (user.username.equals(username)) {
                return true; // Username is already taken
            }
        }
        return false; // Username is available
    }

    // Saves user account information to a file
    private static void saveUserAccounts(List<UserAccount> userAccounts) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/Project/userAccounts.txt"))) {
            for (UserAccount user : userAccounts) {
                // Save basic user information
                writer.print(user.username + "," + user.password + "," + user.points);

                // Save tic-tac-toe board state
                for (int i = 0; i < user.ticTacToeBoard.length; i++) {
                    for (int j = 0; j < user.ticTacToeBoard[i].length; j++) {
                        writer.print("," + user.ticTacToeBoard[i][j]);
                    }
                }

                // Save questions
                for (String question : user.answeredQuestions) {
                    char correctAnswer = getCorrectAnswer(question, true);
                    writer.print("," + correctAnswer + "Answered");
                }

                writer.println(); // Move to the next line for the next user
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads user accounts from a file
    private static List<UserAccount> loadUserAccounts() {
        List<UserAccount> loadedAccounts = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("src/Project/userAccounts.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    UserAccount loadedUser = new UserAccount(parts[0], parts[1]);
                    loadedUser.points = Integer.parseInt(parts[2]);

                    // Load tic-tac-toe board state
                    int boardIndex = 3;
                    for (int i = 0; i < loadedUser.ticTacToeBoard.length; i++) {
                        for (int j = 0; j < loadedUser.ticTacToeBoard[i].length; j++) {
                            if (boardIndex < parts.length) {
                                loadedUser.ticTacToeBoard[i][j] = parts[boardIndex].charAt(0);
                                boardIndex++;
                            }
                        }
                    }

                    // Load user's moves
                    int moveIndex = boardIndex;
                    for (String question : loadedUser.answeredQuestions) {
                        char correctAnswer = getCorrectAnswer(question, true);
                        if (moveIndex < parts.length) {
                            loadedUser.answeredQuestions.add("" + parts[moveIndex] + " correct");
                            moveIndex++;
                        }
                    }

                    loadedAccounts.add(loadedUser);
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist, ignore
        }
        return loadedAccounts;
    }

    // Plays the question game for the user
    private static void playQuestionGame(UserAccount currentUser, List<UserAccount> userAccounts, Scanner scanner) {
        List<String> questions = loadQuestions();
        Collections.shuffle(questions);

        // Play shuffled questions
        for (String question : questions) {
            if (!currentUser.answeredQuestions.contains(question)) {
                String questionWithoutCorrect = question.replace(", correct", "");
                System.out.println(questionWithoutCorrect);

                char correctAnswer = getCorrectAnswer(question, false);
                System.out.print("Please type which letter has the correct answer: ");

                String userAnswer = scanner.next().toUpperCase(); // Convert the user's answer to uppercase
                if (userAnswer.matches("[A-D]")) {
                    if (userAnswer.charAt(0) == Character.toUpperCase(correctAnswer)) {
                        System.out.println("Correct! \n");
                        currentUser.answeredQuestions.add(question);
                        saveUserAccounts(userAccounts);
                        if (hasPlayerWon(currentUser.ticTacToeBoard, 'O')) {
                            System.out.println("\nCongratulations! You won!");
                            currentUser.points++;
                            System.out.println("1 point gained. Total points: " + currentUser.points + "\n");
                            currentUser.ticTacToeBoard = new char[3][3];
                            currentUser.answeredQuestions.clear();
                            saveUserAccounts(userAccounts); // Save immediately after winning Tic-Tac-Toe
                            return; // Break out of the loop if the user won
                        }
                        playTicTacToe(currentUser, scanner, userAccounts);
                        saveUserAccounts(userAccounts); // Save immediately after playing Tic-Tac-Toe
                        break; // break to exit the loop after playing Tic-Tac-Toe
                    } else {
                        System.out.println("Incorrect! \n");
                        currentUser.answeredQuestions.add(question);
                        saveUserAccounts(userAccounts);
                        playSystemX(currentUser, userAccounts, scanner); //system places an X somewhere
                    }
                } else {
                    System.out.println("ERROR: Invalid letter, please put in a valid letter from the answers available. \n");
                }

            }
        }
    }

    // Loads a list of questions from a file
    private static List<String> loadQuestions() {
        // Create a list to store loaded questions
        List<String> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/Project/Questions.txt"))) {
            // StringBuilder to collect lines and construct a complete question
            StringBuilder currentQuestion = new StringBuilder();

            String line;

            // look through lines in the file
            while ((line = br.readLine()) != null) {
                // Check if the line starts with "Question: "
                if (line.startsWith("Question: ")) {
                    // If a new question is encountered and the StringBuilder has content, add the previous question to the list
                    if (currentQuestion.length() > 0) {
                        questions.add(currentQuestion.toString());
                        // Reset the StringBuilder for the new question
                        currentQuestion.setLength(0);
                    }
                    // add the current line to the StringBuilder
                    currentQuestion.append(line).append("\n");
                } else {
                    // If the line doesn't start with "Question: ", add it to the current question (this is for the answers)
                    currentQuestion.append(line).append("\n");
                }
            }

            // After the loop, add question
            if (currentQuestion.length() > 0) {
                questions.add(currentQuestion.toString());
            }
        } catch (IOException e) {
            // Print the stack trace in case of an IOException
            e.printStackTrace();
        }

        // Return the list of loaded questions
        return questions;
    }


    // Retrieves the correct answer from a formatted question
    private static char getCorrectAnswer(String question, boolean isUser) {
        // Split the question into lines to process each line separately
        String[] lines = question.split("\n");

        // Look through each line in the question
        for (String line : lines) {
            // Check if the line contains information about the correct answer
            if (line.contains("correct")) {
                // Return either the correct answer or a placeholder for the user's answer based on the 'isUser'
                return isUser ? ' ' : line.charAt(0);
            }
        }

        // If no information about the correct answer is found, return a default value
        return 'A';
    }

    // Prints the Tic-Tac-Toe board
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

    // Gets the user's move for Tic-Tac-Toe
    private static int[] getUserMove(Scanner scanner) {
        System.out.print("Enter which row you would like to place an 'O' in (1-3): ");
        int row = scanner.nextInt();
        System.out.print("Enter which column you would like to place an 'O' in (1-3): ");
        int col = scanner.nextInt();
        System.out.println("\n");
        return new int[]{row, col};
    }

    // Checks if the user's move is valid on the Tic-Tac-Toe board
    private static boolean isValidMove(char[][] board, int[] move) {
        // Extract row and column from the move, adjusting indices to match the array's zero-based indexing
        int row = move[0] - 1;
        int col = move[1] - 1;
        // Check if the specified cell is within the board boundaries and is empty ('\u0000' represents an empty cell)
        return row >= 0 && row < board.length && col >= 0 && col < board[row].length && board[row][col] == '\u0000';
    }

    // Checks if the player has won the Tic-Tac-Toe game
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

    // Makes a random move for the system in Tic-Tac-Toe
    private static void makeSystemMove(char[][] board) {
        Random rand = new Random();
        int row, col;

        // Generate random coordinates until an empty spot is found
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (board[row][col] != '\u0000');

        board[row][col] = 'X';

        System.out.println("Program placed 'X' at Row " + (row + 1) + " and Column " + (col + 1));
    }

    // Plays the Tic-Tac-Toe game for the user
    private static void playTicTacToe(UserAccount currentUser, Scanner scanner, List<UserAccount> userAccounts) {
        System.out.println("Play Tic-Tac-Toe! \n");

        char[][] board = currentUser.ticTacToeBoard;
        boolean gameWon = false;
        int moves = 0;

        // Loop until the game is won or there are no more moves
        while (!gameWon && moves < 9) {
            printBoard(board);

            int[] move = getUserMove(scanner);

            if (isValidMove(board, move)) {
                board[move[0] - 1][move[1] - 1] = 'O';

                printBoard(board);

                System.out.println("\nYou placed 'O' at Row " + move[0] + " and Column " + move[1] + "\n");

                if (hasPlayerWon(board, 'O')) {
                    gameWon = true;
                    System.out.println("\nCongratulations! You won!");
                    currentUser.points++;
                    System.out.println("1 point gained. Total points: " + currentUser.points + "\n");
                    currentUser.ticTacToeBoard = new char[3][3];
                    saveUserAccounts(userAccounts); // Save immediately after winning Tic-Tac-Toe
                    break;
                }

                saveUserAccounts(userAccounts); // Save immediately after each move

                break;

            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    // Plays the system's move in Tic-Tac-Toe
    private static void playSystemX(UserAccount currentUser, List<UserAccount> userAccounts, Scanner scanner) {
        System.out.println("System plays 'X' \n");

        // get the current user's Tic-Tac-Toe board
        char[][] board = currentUser.ticTacToeBoard;

        // System makes a moves
        makeSystemMove(board);

        // Display the updated board after the system's move
        printBoard(board);

        // Check if the system has won
        if (hasPlayerWon(board, 'X')) {
            System.out.println("\nBOOO you lost!\n");

            // Reset the current user's Tic-Tac-Toe board and clear answered questions
            currentUser.ticTacToeBoard = new char[3][3];
        }

        System.out.print("\nPress Enter to continue\n");
        scanner.nextLine();
        scanner.nextLine();

        // Save user accounts immediately after the system makes a move
        saveUserAccounts(userAccounts);
    }

    // Waits for the user to press Enter
    private static void waitForEnterKey(Scanner scanner) {
        System.out.print("Press Enter to restart the game" + "\n");
        scanner.nextLine(); // Consume the newline character
        scanner.nextLine(); // Wait for the user to press Enter
    }


    // Main method for executing the Project.GeoTic program
    public static void main(String[] args) {
        // Create a new Scanner to take input from the user
        Scanner scanner = new Scanner(System.in);

        // Load existing user accounts from the file
        List<UserAccount> userAccounts = loadUserAccounts();
        UserAccount currentUser = null;

        System.out.println("GeoTic! \n");

        // Option to either login or create an account
        String option;

        // Main loop for user interaction
        do {

            System.out.print("Hello! Please enter 'A' to login or 'B' to create an account: ");
            option = scanner.nextLine();

            // Creating a new user account
            if (option.equalsIgnoreCase("B")) {
                System.out.println("\nCreate An Account: ");

                String newUsername;
                do {

                    System.out.print("Username: ");
                    newUsername = scanner.nextLine();

                    // Check if the username is already taken
                    if (isUsernameTaken(newUsername, userAccounts)) {
                        System.out.println("Username already taken. Please choose another one.");
                    }
                } while (isUsernameTaken(newUsername, userAccounts));

                System.out.print("Password: ");
                String newPassword = scanner.nextLine();

                System.out.print("\nPress Enter to create an account");
                scanner.nextLine();

                // Create a new user account
                UserAccount newUser = new UserAccount(newUsername, newPassword);

                // Add the new user to the list of user accounts
                userAccounts.add(newUser);

                // Save the updated user accounts to the file
                saveUserAccounts(userAccounts);

                System.out.println("Account created successfully!\n");

                // Set the current user to the newly created user
                currentUser = newUser;
            }
            // Logging into an existing user account
            else if (option.equalsIgnoreCase("A")) {
                boolean isValid = false;

                while (!isValid) {
                    System.out.println("\nLogin");
                    System.out.print("Username: ");
                    String enteredUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String enteredPassword = scanner.nextLine();

                    // Validate the entered credentials and get the user account
                    currentUser = isLoginValid(enteredUsername, enteredPassword, userAccounts, scanner);

                    // Check if the login was successful
                    isValid = currentUser != null;

                    if (isValid) {

                        System.out.println("Login successful. Welcome, " + enteredUsername + "!\n");
                    } else {
                        // Display an error message for invalid login information
                        System.out.println("Error: Invalid information. Please try again.");
                    }
                }
            }
            // Invalid option entered
            else {
                System.out.println("ERROR: Invalid option, please enter either A or B.\n");
            }
        } while (!option.equalsIgnoreCase("A") && !option.equalsIgnoreCase("B"));


        System.out.println("Welcome to GeoTic!!\n");

        // Main game loop: Play question game until the user reaches 3 points
        while (currentUser != null && currentUser.points < 3) {
            // Play the question game
            playQuestionGame(currentUser, userAccounts, scanner);

            // Check if the user has won Tic-Tac-Toe
            if (hasPlayerWon(currentUser.ticTacToeBoard, 'O')) {
                // Increase user's points and save the user accounts
                currentUser.points++;
                saveUserAccounts(userAccounts);
            }
        }

        // Check if the user has reached 3 points
        if (currentUser != null && currentUser.points == 3) {
            System.out.println("WOOO you have reached 3 points!!");
            System.out.println("You completed the game!! yay!\n");

            // Reset user's points and clear game-related data
            currentUser.points = 0;
            currentUser.ticTacToeBoard = new char[3][3];
            currentUser.answeredQuestions.clear();

            // Save the updated user account information
            saveUserAccounts(userAccounts);

            // Wait for the user to press Enter
            waitForEnterKey(scanner);

            // Play the question game again after reaching 3 points
            playQuestionGame(currentUser, userAccounts, scanner);
        }

        scanner.close();
    }

}
