import java.util.Scanner;

public class Jeffybezos {
    public static void main(String[] args) {
        System.out.println("________________________________\n");
        System.out.println("Hello! I'm jeffybezos\n"
                + "What can I do for you?\n");
        System.out.println("________________________________\n");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println("________________________________\n");
                System.out.println(input);
                System.out.println("________________________________\n");
            }
        }
        System.out.println("THANK YOU FOR YOUR ATTENTION TO THIS MATTER!\n");
        System.out.println("________________________________\n");
    }
}
