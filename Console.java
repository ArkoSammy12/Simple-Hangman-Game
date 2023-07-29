import java.util.*;

public class Console {

    public static Scanner s = new Scanner(System.in);

    public static void clearAndReturn() {

        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            // Handle exceptions as needed
            e.printStackTrace();

        }

        System.out.print("\u001B[H");

    }

}