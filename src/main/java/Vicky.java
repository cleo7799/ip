import java.util.ArrayList;
import java.util.Scanner;

public class Vicky {
    public static final String indent = "    ";
    static ArrayList<Task> todo_list = new ArrayList<>();
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

        // Greeting
        printLine();
        System.out.println(indent + "Hello! I'm Vicky\n" + indent + "What can I do for you?");
        printLine();

        String command = scanner.next();
        while (!(command.equals("bye"))) {

            // print list
            if (command.equals("list")) {

                System.out.println(indent + "Here's your todo list:");
                for (int i = 1; i <= counter; i++) {
                    System.out.printf(indent + "%d. %s\n", i, todo_list.get(i - 1));
                }
                printLine();

            } else if (command.equals("unmark")) {
                int index = scanner.nextInt() - 1;
                if (index < counter) {
                    todo_list.get(index).unmark();
                    System.out.println(indent + "OK, I've marked this task as not done yet:");
                    System.out.println(indent + todo_list.get(index));
                } else {
                    System.out.println(indent + "CHIBAI THE INDEX OUT OF BOUNDS LA");
                }
                printLine();

            } else if (command.equals("mark")) {
                int index = scanner.nextInt() - 1;
                if (index < counter) {
                    todo_list.get(index).mark();
                    System.out.println(indent + "YAY! I've marked this task as done:");
                    System.out.println(indent + todo_list.get(index));
                } else {
                    System.out.println(indent + "CHIBAI THE INDEX OUT OF BOUNDS LA");
                }

                printLine();
            } else if (command.equals("delete")) {
                int taskNumber = scanner.nextInt();
                deleteTask(taskNumber);

            // Adding a new task
            } else if (!command.isEmpty()) {
                String description = scanner.nextLine();

                if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    Vicky.addTask(command, description);

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
    /**
     * Adds the specified task to the list.
     * If the description is missing information,
     * an error message will be printed to inform them
     * if their task description or time is missing
     * @param taskType Type of task to add
     * @param description Task description and task timeframe
     */
    private static void addTask(String taskType, String description) {
        Task t = null;
        boolean hasDescription;
        boolean hasTime;
        if (taskType.equals("todo")) {

            hasDescription = !description.isEmpty();
            hasTime = true;
            if (hasDescription) {
                t = new Task(description);
            }

        } else if (taskType.equals("deadline")) {

            String[] temp = description.split(" /by ");
            int len = temp.length;
            if (len == 2) {
                String task = temp[0];
                String by = temp[1];
                hasDescription = !task.isEmpty();
                hasTime = !by.isEmpty();
                if (hasDescription && hasTime) {
                    t = new Deadline(task, by);
                }
            } else {
                hasDescription = !description.isEmpty();
                hasTime = false;
            }

        } else {
            String[] temp = description.split(" /from ");
            int len = temp.length;
            if (len == 2) {
                String task = temp[0];
                hasDescription = !task.isEmpty();
                String[] duration = temp[1].split(" /to ");
                int len2 = duration.length;
                if (len2 == 2) {
                    String from = duration[0];
                    String by = duration[1];
                    hasTime = !by.isEmpty() && !from.isEmpty();
                    if (hasDescription && hasTime) {
                        t = new Event(task, from, by);
                    }
                } else {
                    hasTime = false;
                }

            } else {
                hasDescription = !description.isEmpty();
                hasTime = false;
            }
        }
        if (t != null && hasDescription && hasTime) {
            System.out.println(indent + "Ok! I've added this task:\n" + indent + indent + t.toString());
            Vicky.todo_list.add(t);
            Vicky.counter++;
            System.out.println(indent + "Now you have " + Vicky.counter + " tasks in your list. :)");
        } else if (hasDescription){
            System.out.println(indent + "Oi you never gimme the time!");
        } else {
            System.out.println(indent + "Oi your task cannot be empty!");
        }
        printLine();

    }

    /**
     * Deletes specified task from todo_list
     * If the specified index is out of bounds, an error message will be printed
     * @param i Task number in the todo list
     */
    private static void deleteTask(int i) {
        if (i > counter) {
            System.out.println(indent + "Nah cuh your list too short. Try again hoe!");
        } else {
            Task t = todo_list.remove(i - 1);
            counter--;
            System.out.println(indent + "Noted with many thanks! I've removed this task:");
            System.out.println(indent + indent + t.toString());
            System.out.println(indent + "You now have " + counter + " tasks left!");
            printLine();
        }
    }

}


