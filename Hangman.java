import java.util.*;

public class Hangman {

    private String word;

    private int livesLeft;

    private char[] filledLetters;

    private boolean previousAttempt;

    public Hangman(String word) {

        setWord(word);

        setLives();

        setFilledLetters(word);

    }

    void setWord(String word) {

        this.word = word;

    }

    void setLives() {

        this.livesLeft = 5;

    }

    void setFilledLetters(String word) {

        this.filledLetters = new char[word.length()];

        for (int i = 0; i < word.length(); i++) {

            this.filledLetters[i] = '_';

        }

    }

    public void setPreviousAttempt(boolean previousAttempt) {

        this.previousAttempt = previousAttempt;

    }

    public String getWord() {

        return this.word;

    }

    public int getLivesLeft() {

        return this.livesLeft;

    }

    public char[] getFilledLetters() {

        return this.filledLetters;

    }

    public boolean getPreviousAttempt() {

        return this.previousAttempt;

    }

    public static Hangman getNewHangman(String word) {

        return new Hangman(word);

    }

    public void decrementLives() {

        this.livesLeft--;

    }

    public char drawGame() {

        System.out.println(this.livesLeft == 5 || this.getPreviousAttempt()
                ? "You have " + this.getLivesLeft() + " lives left\n"
                : "Your guess was wrong. You have " + this.getLivesLeft() + " lives left\n");

        for (int i = 0; i < filledLetters.length; i++) {

            System.out.print(this.getFilledLetters()[i]);

        }

        boolean sameInput = false;

        char letter = ' ';

        System.out.println("\n\nPlease enter a character to make your guess: ");

        while (!sameInput) {

            letter = Console.s.nextLine().charAt(0);

            if (this.wasAlreadyGuessed(letter)) {

                System.out.println("\nLetter already inputted. Please try with a new letter.");

            } else {

                sameInput = true;

            }

        }

        return letter;

    }

    public boolean foundLetter(char c) {

        for (int i = 0; i < this.word.length(); i++) {

            if (this.getWord().charAt(i) == c) {

                return true;

            }

        }

        return false;

    }

    public ArrayList<Integer> getIndices(char c) {

        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < this.getWord().length(); i++) {

            if (this.getWord().charAt(i) == c) {

                indices.add(i);

            }

        }

        return indices;

    }

    public void updateLettersFound(ArrayList<Integer> indices, char c) {

        for (int i = 0; i < filledLetters.length; i++) {

            for (int j = 0; j < indices.size(); j++) {

                if (i == indices.get(j)) {

                    this.filledLetters[i] = c;

                }

            }

        }

    }

    public boolean shouldContinue() {

        String currentWord = String.valueOf(this.getFilledLetters());

        return this.getLivesLeft() != 0 && !currentWord.equals(this.getWord());

    }

    public boolean hasWon() {

        String currentWord = String.valueOf(this.getFilledLetters());

        return currentWord.equals(this.word);

    }

    public boolean wasAlreadyGuessed(char c) {

        for (int i = 0; i < this.getFilledLetters().length; i++) {

            if (this.getFilledLetters()[i] == c) {

                return true;

            }

        }

        return false;

    }

}
