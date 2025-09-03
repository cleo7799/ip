package TaskList;

import java.util.ArrayList;
import java.io.IOException;
import storage.Storage;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class TaskList {
    public static final String indent = "    ";
    private final Storage storage;
    final ArrayList<Task> tasks;

    private static int counter;

    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
        counter = tasks.size();
    }

    public int numberOfTasks() {
        return counter;
    }

    /**
     * Prints task list.
     */
    public void printList() {
        System.out.println(indent + "Here's your todo list:");
        for (int i = 1; i <= counter; i++) {
            System.out.printf(indent + "%d. %s\n", i, tasks.get(i - 1));
        }
        printLine();
    }

    /**
     * Unmarks task at specified index and prints specified task.
     */
    public void unmarkTask(int index) {
        if (index < counter) {
            tasks.get(index).unmark();
            System.out.println(indent + "OK, I've marked this task as not done yet:");
            System.out.println(indent + tasks.get(index));
        } else {
            System.out.println(indent + "CHIBAI THE INDEX OUT OF BOUNDS LA");
        }
        printLine();
        this.autoSave();
    }

    /**
     * Marks task at specified index and prints specified task.
     */
    public void markTask(int index) {
        if (index < counter) {
            tasks.get(index).mark();
            System.out.println(indent + "YAY! I've marked this task as done:");
            System.out.println(indent + tasks.get(index));
        } else {
            System.out.println(indent + "CHIBAI THE INDEX OUT OF BOUNDS LA");
        }

        printLine();
        this.autoSave();
    }

    /**
     * Deletes specified task from tasks
     * If the specified index is out of bounds, an error message will be printed
     * @param i Task number in the task list
     */
    public void deleteTask(int i) {
        if (i > counter) {
            System.out.println(indent + "Nah cuh your list too short. Try again hoe!");
        } else {
            Task t = tasks.remove(i - 1);
            counter--;
            System.out.println(indent + "Noted with many thanks! I've removed this task:");
            System.out.println(indent + indent + t.toString());
            System.out.println(indent + "You now have " + counter + " tasks left!");
            printLine();
        }
        this.autoSave();
    }

    /**
     * Adds the specified task to the list.
     * If the description is missing information,
     * an error message will be printed to inform them
     * if their task description or time is missing
     * @param taskType Type of task to add
     * @param description Task description and task timeframe
     */
    public void addTask(String taskType, String description) {
        Task t = null;
        boolean hasDescription;
        boolean hasTime;
        if (taskType.equals("todo")) {

            hasDescription = !description.isEmpty();
            hasTime = true;
            if (hasDescription) {
                t = new Todo(description);
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
            tasks.add(t);
            counter++;
            System.out.println(indent + "Now you have " + counter + " tasks in your list. :)");
        } else if (hasDescription){
            System.out.println(indent + "Oi you never gimme the time!");
        } else {
            System.out.println(indent + "Oi your task cannot be empty!");
        }
        this.autoSave();
        //Vicky.printLine();
    }

    private void autoSave() {
        try {
            this.storage.saveAllTasks(tasks);
        } catch (IOException e) {
            System.err.println("Failed to save edit: " + e.getMessage());
        }
    }

    /**
     * Prints a line.
     */
    public static void printLine() {
        int length = 40;
        System.out.println(indent + "_".repeat(length));
    }

}
