import java.io.IOException;
import java.util.Scanner;

import TaskList.TaskList;
import TaskList.InvalidTaskException;
import TaskList.InvalidInputException;
import command.Command;
import storage.Storage;
import ui.Ui;

public class Vicky {
    public static final String indent = "    ";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Vicky() {
        this.ui = new Ui();
        this.storage = new Storage();

        try {
            this.tasks = new TaskList(this.storage.load(), this.storage);
        } catch (IOException e) {
            System.err.println(ui.showLoadingError(e.getMessage()));
            this.tasks = new TaskList(); // i think your program may automatically handle error...
        }
    }

    public void run() {
        System.out.print(ui.showGreeting());
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = scanner.nextLine();
            try {
                Command command = Parser.parse(fullCommand);
                String output = command.execute(tasks, ui, storage);
                System.out.print(output);
                isExit = command.isExit();

            } catch (InvalidInputException e) {
                System.err.print(ui.showError(e.getMessage() + "Please enter a valid command!"));
            } catch (InvalidTaskException e) {
                System.err.print(ui.showError(e.getMessage()));
            } catch (Exception e) {
                System.err.print(ui.showError(e.getMessage()));
            }
        }
    }

    public static void main(String[] args) {
        new Vicky().run();
    }

    /*
    public static void main(String[] args) {

        Ui ui = new Ui();
        Storage storage = new Storage();
        Scanner scanner = null;

        try {
            scanner = new Scanner(System.in);
            storage.init();
            TaskList todoList = new TaskList(storage.load(), storage);

            System.out.println(ui.showGreeting());

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
                        System.out.println(ui.showError("Bitch I don't know what that means. :("));
                        break;
                }
                command = scanner.next();
            }
            System.out.println(ui.showGoodbye());

        } catch (IOException e) {
            System.err.println(ui.showError("Failed to initialize storage: " + e.getMessage()));
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


     */
}


