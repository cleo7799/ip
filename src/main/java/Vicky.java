import java.util.Scanner;

public class Vicky {
    public static final String indent = "    ";

    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

         */
        Scanner scanner = new Scanner(System.in);
        print_line();
        System.out.println(indent + "Hello! I'm Vicky\n" + indent + "What can I do for you?");
        print_line();

        String command = scanner.next();
        while (!(command.equals("bye"))) {
            if (!command.isEmpty()) {
                System.out.println(indent + command);
                print_line();
            }
            command = scanner.next();
        }

        System.out.println(indent + "Bye! Hope to see you again soon!");
        print_line();

        scanner.close();

    }

    public static void print_line() {
        int length = 40;
        System.out.println(indent + "_".repeat(length));
    }
}
