import java.util.Scanner;

public class Tronalddump {
    public static void main(String[] args) {
        System.out.println("________________________________\n");
        System.out.println("GOOD MORNING! I AM TRONALD DUMP! GOD BLESS AMERICA\n"
                + "WHAT CAN I, THIS BEAUTIFUL MAN, DO FOR YOU?\n");
        System.out.println("________________________________\n");
        String[] list = new String[100];
        int index = 0;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(String.format("%d. %s",i + 1,list[i]));
                }
            } else {
                list[index++] = input;
                System.out.println("________________________________\n");
                String formatted = String.format("YOU SAID: %s", input.toUpperCase());
                System.out.println(formatted);
                System.out.println("________________________________\n");
            }
        }
        System.out.println("THANK YOU FOR YOUR ATTENTION TO THIS MATTER!\n");
        System.out.println("________________________________\n");
    }
}
