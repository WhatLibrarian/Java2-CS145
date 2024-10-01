import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    // Constants
    public static final int MAX_NUMBER = 100;  // Maximum number for guessing
    public static final String BOLD = "\033[1m";
    public static final String UNDERLINE = "\033[4m";
    public static final String RESET = "\033[0m";
    public static void main(String[] args) {
       
        Scanner console = new Scanner(System.in);
        introduction();  // Game introduction
        
        int totalGames = 0;
        int totalGuesses = 0;
        int bestGame = Integer.MAX_VALUE;
        String playAgain;

        do {
            // Play one game
            int guessesThisGame = playOneGame(console);
            totalGames++;
            totalGuesses += guessesThisGame;

            if (guessesThisGame < bestGame) {
                bestGame = guessesThisGame;
            }

            // Ask if the user wants to play again with input validation
            playAgain = getValidYesNoResponse(console, "Do you want to play again? ");
        } while (playAgain.toLowerCase().startsWith("y"));

        // Report overall statistics
        reportResults(totalGames, totalGuesses, bestGame);
    }

    // Method to introduce the game to the user
    public static void introduction() {
        System.out.println("Welcome to the Guessing Game!");
        System.out.println("I am thinking of a number between 1 and " + MAX_NUMBER + ".");
        System.out.println("Try to guess it, and I'll tell you if your guess is too high or too low.");
    }

    // Method to play one game of guessing with input validation
    public static int playOneGame(Scanner console) {
        Random rand = new Random();
        int targetNumber = rand.nextInt(MAX_NUMBER) + 1; // Random number between 1 and MAX_NUMBER
        int guess = -1;
        int numGuesses = 0;

        System.out.println("\nI'm thinking of a number...");

        while (guess != targetNumber) {
            guess = getValidIntInput(console, "Your guess? ");
            numGuesses++;

            if (guess < targetNumber) {
                System.out.println("It's higher.");
            } else if (guess > targetNumber) {
                System.out.println("It's lower.");
            } else {
                if (numGuesses == 1) {
                    System.out.println("You got it right in 1 guess!");
                } else {
                    System.out.println("You got it right in " + numGuesses + " guesses.");
                }
            }
        }

        return numGuesses;  // Return the number of guesses made in this game
    }

    // Method to report overall results to the user
    public static void reportResults(int totalGames, int totalGuesses, int bestGame) {
        System.out.println("\nOverall results:");
        System.out.println("    Total games   = " + totalGames);
        System.out.println("    Total guesses = " + totalGuesses);
        System.out.printf("    Guesses/game  = %.1f\n", (double) totalGuesses / totalGames);
        System.out.println("    Best game     = " + bestGame + " guesses");
    }

    // Method to validate integer input
    public static int getValidIntInput(Scanner console, String prompt) {
        System.out.print(prompt);
        System.out.print(BOLD + UNDERLINE);
        while (!console.hasNextInt()) {
            System.out.print(RESET);
            System.out.print(prompt);
            System.out.println("\nInvalid input. Please enter an integer.");
            console.next();  // Clear the invalid input 
            System.out.print(prompt);
            System.out.print(BOLD + UNDERLINE);
        }    
        System.out.print(RESET);
        return console.nextInt();

    }

    // Method to validate "yes" or "no" response
    public static String getValidYesNoResponse(Scanner console, String prompt) {
        System.out.print(prompt);
        System.out.print(BOLD + UNDERLINE);
        String response = console.next().toLowerCase();
        System.out.print(RESET);
        while (!response.startsWith("y") && !response.startsWith("n")) {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            System.out.print(prompt);
            System.out.print(BOLD + UNDERLINE);
            response = console.next().toLowerCase();
            System.out.print(RESET);
        }
        return response;
    }
}
