package vicky.ui;

import vicky.taskList.Task;
import vicky.taskList.TaskList;

/**
 * Represents the main user interface window of the application,
 * providing a graphical interface for user interaction and displaying messages.
 *
 * @author Rachel Wong
 */
public class Ui {

    /** Indentation used for printing formatted messages */
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
        return printLine() + "\n" + INDENT + "Hello! I'm Vicky\n" + INDENT + "What can I do for you?" + printLine();
    }

    /**
     * Returns a goodbye message.
     */
    public String showGoodbye() {
        return INDENT + "Awwww :( Bye bye!!" + printLine();
    }

    /**
     * Returns an error message.
     *
     * @param message is the error message to be shown to user.
     */
    public String showError(String message) {
        return INDENT + "\uD83D\uDCA9 OH SHIT!!! " + message + printLine();
    }

    /**
     * Returns a loading error message to be shown to user.
     *
     * @param message is the error message.
     * @return loading error message.
     */
    public String showLoadingError(String message) {
        return INDENT + "Failed to load tasks: " + message + "\n" + INDENT + "Starting with empty list." + printLine();
    }

    /**
     * Returns a string message displaying the task added.
     *
     * @param t task that was added.
     * @param counter number of tasks in the task list.
     * @return message.
     */
    public String showAddedTask(Task t, int counter) {
        return INDENT + "Ok! I've added this task:\n" + INDENT + INDENT + t.toString() + "\n" +
                INDENT + "Now you have " + counter + " tasks in your list. :)" + printLine();
    }

    /**
     * Returns a message displaying the task list.
     *
     * @param tasks task list to be displayed.
     * @return message.
     */
    public String showList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return INDENT + "You have no tasks in your list!" + printLine();
        } else {
            return INDENT + "Here's your todo list:\n" + tasks.toString() + printLine();
        }
    }

    /**
     * Returns a message displaying the marked task.
     *
     * @param task task to be marked.
     * @return message.
     */
    public String showMarkedTask(Task task) {
        return INDENT + "YAY! I've marked this task as done:\n" + INDENT + task + printLine();
    }

    /**
     * Returns a message displaying the unmarked task.
     *
     * @param task task to be unmarked.
     * @return message.
     */
    public String showUnmarkedTask(Task task) {
        return INDENT + "OK, I've marked this task as not done yet:\n" + INDENT + task + printLine();
    }

    /**
     * Returns a message displaying the deleted task.
     *
     * @param task task to be deleted.
     * @param counter number of tasks currently in the task list.
     * @return message.
     */
    public String showDeleteTask(Task task, int counter) {
        return INDENT + "Noted with many thanks! I've removed this task:\n" +
                INDENT + INDENT + task.toString() + "\n" +
                INDENT + "You now have " + counter + " tasks left!" +
                printLine();
    }

    /**
     * Returns message telling user that the index is out of bounds.
     *
     * @return message.
     */
    public String showIndexOutOfBounds() {
        return INDENT + "Nah cuh your list too short. Try again hoe!" + printLine();
    }

}
