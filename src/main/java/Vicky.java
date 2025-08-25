import java.util.Scanner;

public class Vicky {
    public static final String indent = "    ";
    private static Task[] todo_list = new Task[100];
    private static int counter = 0;

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

        String command = scanner.nextLine();
        while (!(command.equals("bye"))) {

            if (command.equals("list")) {

                System.out.println(indent + "Here's your todo list:");
                for (int i = 1; i <= counter; i++) {
                    System.out.printf(indent + "%d. %s\n", i, todo_list[i - 1]);
                }
                print_line();

            } else if (!command.isEmpty()) {
                System.out.println(indent + "added: " + command);
                print_line();
                Task t = new Task(command);
                Vicky.todo_list[counter] = t;
                Vicky.counter++;

            }

            command = scanner.nextLine();
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


