import java.util.*;

public class Main {

    public static boolean doContinue = true;
    public static boolean doRunning = true;
    public static int attemptCounter = 0;
    public static ArrayList<Hangman> hangmen = new ArrayList<>();

    public static void main(String[] args) {

        while (doContinue) {

            initHangman();

            doRunning = true;

            while (doRunning) {

                loopHangman();

            }

        }

        displayScore(hangmen);

    }

    public static void initHangman() {

        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "HANGMAN\n\nWelcome to Hangman! To continue, please enter a word. Make sure to not let others see the word: \n");
        String word = scanner.nextLine();

        hangmen.add(Hangman.getNewHangman(word));

        Hangman.clearAndReturn();

    }

    public static void loopHangman() {

        char currentChar = hangmen.get(attemptCounter).drawGame();

        boolean foundLetter = hangmen.get(attemptCounter).foundLetter(currentChar);

        if (foundLetter) {

            hangmen.get(attemptCounter).updateLettersFound(hangmen.get(attemptCounter).getIndices(currentChar),
                    currentChar);
            hangmen.get(attemptCounter).setPreviousAttempt(true);

        } else {

            hangmen.get(attemptCounter).decrementLives();
            hangmen.get(attemptCounter).setPreviousAttempt(false);

        }

        if (!hangmen.get(attemptCounter).shouldContinue()) {

            Hangman.clearAndReturn();

            doRunning = false;

            boolean incorrectInput = true;

            Scanner s = new Scanner(System.in);

            while (incorrectInput) {

                if (hangmen.get(attemptCounter).hasWon()) {

                    for (int i = 0; i < hangmen.get(attemptCounter).getFilledLetters().length; i++) {

                        System.out.print(hangmen.get(attemptCounter).getFilledLetters()[i]);

                    }

                    System.out.println("\n\nCongratulations, you have won!");

                } else {

                    for (int i = 0; i < hangmen.get(attemptCounter).getFilledLetters().length; i++) {

                        System.out.print(hangmen.get(attemptCounter).getFilledLetters()[i]);

                    }

                    System.out.println("\n\nYou have lost. The correct word was: "
                            + hangmen.get(attemptCounter).getWord());

                }

                System.out.println("Do you wish to continue? y/n");

                while (incorrectInput) {

                    char c = s.nextLine().charAt(0);

                    switch (c) {

                        case 'y':
                            doRunning = false;
                            incorrectInput = false;
                            attemptCounter++;
                            break;
                        case 'n':
                            doRunning = false;
                            doContinue = false;
                            incorrectInput = false;
                            break;
                        default:
                            System.out.println("\nInvalid input. Please try again");
                            break;
                    }

                }

            }

        }

        Hangman.clearAndReturn();

    }

    public static void displayScore(ArrayList<Hangman> hangmen){

        int attemptNumber = 0;

        System.out.println("Result Screen\n");

        for(Hangman hangman : hangmen){

            System.out.println("Attempt counter: " + (attemptNumber + 1));
            System.out.println("Word to guess: " + hangman.getWord());
            System.out.print("Letters guessed: ");

            for(int i = 0; i < hangman.getFilledLetters().length; i++){

                System.out.print(hangman.getFilledLetters()[i]);

            }

            System.out.print("\nResult: ");
            System.out.println(hangman.hasWon() ? "won\n" : "lost\n");

            attemptNumber++;

        }

    }

}