import TaskList.Task;
import TaskList.TaskList;

public class Ui {

    public static final String INDENT = "    ";

    /**
     * Returns a line.
     */
    public static String printLine() {
        return "\n" + INDENT + "__________________________________________________________________________\n";
    }

    /**
     * Returns a welcome message.
     */
    public String showGreeting() {
        return printLine() + INDENT + "Hello! I'm Vicky\n" + INDENT + "What can I do for you?" + printLine();
    }

    /**
     * Returns a goodbye message.
     */
    public String showGoodbye() {
        return INDENT + "Awwww :( Bye bye!!" + printLine();
    }

    /**
     * Returns an error message.
     * @param message
     */
    public String showError(String message) {
        return INDENT + "\uD83D\uDCA9 OH SHIT!!! " + message + "\n" + printLine();
    }

    /**
     * Prints task added.
     */
    public String showAddedTask(Task t, int counter) {
        return INDENT + "Ok! I've added this task:\n" + INDENT + INDENT + t.toString() + "\n" +
                INDENT + "Now you have " + counter + " tasks in your list. :)\n" + printLine();
    }

    /**
     * Prints list.
     */
    public String showList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "You have no tasks in your list!\n";
        } else {
            return printLine() +  "Here's your todo list:\n" + tasks.toString() + "\n" + printLine();
        }
    }

    /**
     * Prints marked task.
     */
    public String showMarkedTask(Task task) {
        return INDENT + "YAY! I've marked this task as done:\n" + INDENT + task + "\n" + printLine();
    }

    /**
     * Prints unmarked task.
     */
    public String showUnmarkedTask(Task task) {
        return INDENT + "OK, I've marked this task as not done yet:\n" + INDENT + task + "\n" + printLine();
    }

    /**
     * Prints deleted task.
     * @param task
     * @param counter
     * @return
     */
    public String showDeleteTask(Task task, int counter) {
        return INDENT + "Noted with many thanks! I've removed this task:\n" +
                INDENT + INDENT + task.toString() + "\n" +
                INDENT + "You now have " + counter + " tasks left!\n" +
                printLine();
    }

    /**
     * Prints index out of bounds message.
     * @return
     */
    public String showIndexOutOfBounds() {
        return INDENT + "Nah cuh your list too short. Try again hoe!\n";
    }

}
