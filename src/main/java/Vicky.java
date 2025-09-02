import java.util.Scanner;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import TaskList.TaskList;
import TaskList.Task;

public class Vicky {
    public static final String indent = "    ";
    static TaskList todoList = new TaskList();

    public static void main(String[] args) {

        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "data", "Vicky.txt");
        boolean directoryExists = Files.exists(path);
        File vicky = path.toFile();

        Scanner scanner = new Scanner(System.in);

        // Greeting
        printLine();
        System.out.println(indent + "Hello! I'm Vicky\n" + indent + "What can I do for you?");
        printLine();

        String command = scanner.next();
        while (!(command.equals("bye"))) {

            // print list
            if (command.equals("list")) {

                todoList.printList();

            } else if (command.equals("unmark")) {
                int index = scanner.nextInt() - 1;
                todoList.unmarkTask(index);

            } else if (command.equals("mark")) {
                int index = scanner.nextInt() - 1;
                todoList.markTask(index);

            } else if (command.equals("delete")) {
                int taskNumber = scanner.nextInt();
                todoList.deleteTask(taskNumber);

            // Adding a new task
            } else if (!command.isEmpty()) {
                String description = scanner.nextLine();

                if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    todoList.addTask(command, description);
                    printLine();

                } else {
                    //System.out.println(indent + command + description);
                    System.out.println(indent + "Walao eh chibai I don't know what that means :(");
                    printLine();
                }

            }

            command = scanner.next();
        }

        // Goodbye message
        System.out.println(indent + "Awwww :( Bye bye!!");
        printLine();

        scanner.close();

    }

    public static void printLine() {
        int length = 40;
        System.out.println(indent + "_".repeat(length));
    }

}


