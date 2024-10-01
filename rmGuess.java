import java.util.Scanner;
import java.util.Random;

public class rmGuess {

   public static void main(String[] args) {

      

      // declaring of the variables for this Method
      int totalGames = 0; // starts game count at 0
      int totalGuesses = 0; // starts guess count at 0
      int avgCount = Integer.MAX_VALUE; // math.... school of google

      intro();
      System.out.println();

      boolean newGame = true;

      while (newGame) {
         // Generating a random number each time
         Random myRand = new Random();
         int upperbound = 100; // https://www.educative.io/answers/how-to-generate-random-numbers-in-java I
                               // chose the most easy method.
         int int_random = myRand.nextInt(upperbound) + 1; // sets the upperbound.
         // System.out.println("Random integer " + int_random); // used for testing
         // purposes
         int count = 0; // starts each game at a count of 0
         Scanner userGuess = new Scanner(System.in); // initiates new Scanner
    
         count = singleGame(int_random, count, userGuess, upperbound);//single game method

         totalGames++; // adds another count to the the number of Games
         totalGuesses += count; // running tally of all guesses across games
         avgCount = Math.min(avgCount, count); // I totally used the google to do the math.

         System.out.print("Do you want to play again? (Y/N): "); // play again?

         String gameInquiry = userGuess.next(); // takes in users choice as a String
         char firstChar = gameInquiry.charAt(0); // strips it to a y or n
         // System.out.println("The first character is: " +firstChar); //checking
         // firsChar in dev mode
         String finalString = String.valueOf(firstChar); // turns it back into a string
         newGame = finalString.equalsIgnoreCase("Y"); // starts a new game for y or Y.
         // newGame = firstChar.equ38alsIgnoreCase("Y"); // this won't work because it
         // 'deferences' the char
         // newGame = convertCharToBoolean(firstChar); ChatGPT suggested this but no...
         // cannot find symbol
         System.out.println();

      } // end of newGame loop

      statistics(totalGames, totalGuesses, avgCount); // Report statistics Method

   } // end of main Method

   public static void intro() {
      System.out.println("This program allows you to play a guessing game.");
      System.out.println("I'm thinking of a number between 1 and");
      System.out.println("100 and will allow you to guess until");
      System.out.println("you get it. For each guess, I will tell you");
      System.out.println("whether the right answer is higher or lower");
      System.out.println("than your guess.");
   } // end of Intro Method

   // public static void playGame (int playerGuess, int int_random, int count){ }
   // // end of playGame Method
   // This was a fail

   public static void statistics(int totalGames, int totalGuesses, int avgCount) // have to accept those integers as parameters.
   {
      double averageCount = (double) totalGuesses / totalGames; // creates a double for the average number of guesses
      System.out.println("Overall results:");
      System.out.println("\ttotal games\t= " + totalGames);
      System.out.println("\ttotal guesses\t= " + totalGuesses);
      System.out.printf("\tguesses/game\t= %.1f%n", averageCount);
      System.out.println("\tbest game\t= " + avgCount);
   } // end of statistics Method

   public static int singleGame(int int_random, int count, Scanner userGuess, int upperbound) {
        System.out.println("I'm thinking of a number between 1 and 100...");
         while (true) {
            int checkInt = 0;
            int playerGuess = 0;
            do {
                System.out.print("Your guess? ");
                playerGuess = userGuess.nextInt();

                if (playerGuess > upperbound) {
                    System.out.println("Enter a valid input!");
                    checkInt++;

                } else if (playerGuess < 1) {
                    System.out.println("Enter a valid input!");
                    checkInt++;
                } else {
                    checkInt = 0;
                }
            } while (checkInt > 0);
            count++;

            // playGame(playerGuess, int_random, count);
            if (playerGuess == int_random) {
               if (count == 1) {
                  System.out.println("You got it right in 1 guess!");
               } else {
                  System.out.println("Correct");
                  System.out.println("You got it right in " + count + " guesses!");
               }
               break;
            } // had working break here end of if loop count
            else if (playerGuess > int_random) {
               System.out.println("Its lower.");
            } else {
               System.out.println("It's higher.");
            }
         } // end of while true loop
         return count;
   }//end of single game method

} // ends class