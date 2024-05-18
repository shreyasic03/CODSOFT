import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxAttempts = 10; // Maximum number of attempts allowed
        int score = 0; // Player's score
        int rounds = 0; // Number of rounds played
        while (true) {
            int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
            int attempts = 0; // Number of attempts for the current round
            System.out.println("\nRound " + (++rounds));
            System.out.println(
                    "I'm thinking of a number between 1 and 100. You have " + maxAttempts + "attempts to guess it.");
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;
                if (guess == randomNumber) {
                    System.out
                            .println("Congratulations! You guessed the number correctly in " + attempts + " attempts.");
                    score += maxAttempts - attempts + 1; // Update the score
                    break;
                } else if (guess < randomNumber) {
                    System.out.println("Your guess is too low. Try again.");
                } else {
                    System.out.println("Your guess is too high. Try again.");
                }
            }
            if (attempts == maxAttempts) {
                System.out.println("Sorry, you ran out of attempts. The number was " + randomNumber + ".");
            }
            System.out.println("Your current score is: " + score);
            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.next();
            if (!playAgain.equalsIgnoreCase("yes")) {
                System.out.println("Thanks for playing! Your final score is: " + score);
                break;
            }
        }
        scanner.close();
    }
}
