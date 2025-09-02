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


