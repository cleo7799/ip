package vicky.tasklist;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import vicky.parser.Parser;

/**
 * Abstract class representing a task with a name and completion status.
 *
 * @author Rachel
 */
public abstract class Task {
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    protected String name;
    protected boolean isDone;

    /**
     * Constructor for Task class, initializes the Task with a name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Overloaded constructor for Task class, initializes the Task with a name and a completion status.
     *
     * @param name The name of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Changes the completion status of the task to true.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Changes the completion status of the task to false.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if the task description contains the input keyword.
     *
     * @param str The keyword.
     * @return true if the task description contains the input keyword.
     */
    public boolean contains(String str) {
        return this.name.toLowerCase().contains(str);
    }

    /**
     * Parses the storage string into a corresponding task object.
     *
     * @param s Storage string containing the details of a task.
     * @return The corresponding task based on the storage string.
     * @throws IllegalArgumentException if the storage string format is invalid.
     */
    public static Task fromStorageString(String s) throws IllegalArgumentException, DateTimeException {
        String[] temp = s.split("\\s\\|\\s");
        String taskType = temp[0];

        if (temp.length < 3) {
            throw new IllegalArgumentException("Missing data: Only " + temp.length + " fields received.");
        }

        String taskStatus = temp[1];
        String taskDescriptor = temp[2];
        boolean isDone;

        if (taskStatus.isEmpty()) {
            throw new IllegalArgumentException("Missing task status.");
        } else if (taskStatus.equals("0")) {
            isDone = true;
        } else if (taskStatus.equals("1")) {
            isDone = false;
        } else {
            throw new IllegalArgumentException("Corrupted task status value: " + taskStatus);
        }

        if (taskDescriptor.isEmpty()) {
            throw new IllegalArgumentException("Empty task descriptor");
        }

        switch (taskType) {
        case "Todo":
            return new Todo(taskDescriptor, isDone);
        case "Deadline":
            if (temp.length != 4 || temp[3].isEmpty()) {
                throw new IllegalArgumentException("Deadline task has missing values.");
            }
            LocalDateTime deadline = Parser.parseOutputString(temp[3]);

            return new Deadline(taskDescriptor, deadline, isDone);
        case "Event":
            if (temp.length != 5 || temp[3].isEmpty() || temp[4].isEmpty()) {
                throw new IllegalArgumentException("Event task has missing values.");
            }
            LocalDateTime start = parseOutputString(temp[3]);
            LocalDateTime end = parseOutputString(temp[4]);
            return new Event(taskDescriptor, start, end, isDone); //Fallthrough
        default:
            throw new IllegalArgumentException("Unknown task type: " + taskType); //Fallthrough
        }
    }

    /**
     * Parses a string into a LocalDateTime object based on the output format.
     *
     * @param s The input string to be parsed.
     * @return The parsed LocalDateTime object.
     * @throws DateTimeException if the input string is in an invalid format.
     */
    public static LocalDateTime parseOutputString(String s) throws DateTimeException {
        return LocalDateTime.parse(s, OUTPUT_FORMAT);

    }

    public abstract String toStorageString();

    @Override
    public abstract String toString();

}
