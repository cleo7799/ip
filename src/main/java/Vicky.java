import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import TaskList.TaskList;
import storage.Storage;
import TaskList.Task;

public class Vicky {
    public static final String indent = "    ";

    public static void main(String[] args) {

        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "data", "Vicky.txt");
        Storage storage = new Storage(path);
        Scanner scanner = null;

        try {
            scanner = new Scanner(System.in);
            storage.init();
            TaskList todoList = new TaskList(storage.load(), storage);

            greet();

            String command = scanner.next();
            while (!(command.equals("bye"))) {

                switch (command) {
                    case "list":
                        todoList.printList();
                        break;
                    case "unmark":
                        int index = scanner.nextInt() - 1;
                        todoList.unmarkTask(index);
                        break;
                    case "mark":
                        index = scanner.nextInt() - 1;
                        todoList.markTask(index);
                        break;
                    case "delete":
                        int taskNumber = scanner.nextInt();
                        todoList.deleteTask(taskNumber);
                        break;
                    case "todo", "deadline", "event":
                        String description = scanner.nextLine();
                        todoList.addTask(command, description);
                        printLine();
                        break;
                    default:
                        description = scanner.nextLine();
                        System.out.println(indent + "Walao eh chibai I don't know what that means :(");
                        printLine();
                        break;
                }
                command = scanner.next();
            }
            goodbye();

        } catch (IOException e) {
            System.err.println("Failed to initialize storage: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

    }

    public static void printLine() {
        int length = 40;
        System.out.println(indent + "_".repeat(length));
    }

    public static void greet() {
        // Greeting
        printLine();
        System.out.println(indent + "Hello! I'm Vicky\n" + indent + "What can I do for you?");
        printLine();
    }

    public static void goodbye() {
        // Goodbye message
        System.out.println(indent + "Awwww :( Bye bye!!");
        printLine();
    }
}


