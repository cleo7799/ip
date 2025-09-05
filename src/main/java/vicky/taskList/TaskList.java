package vicky.taskList;

import java.util.ArrayList;

import vicky.storage.Storage;

import java.time.format.DateTimeFormatter;

public class TaskList {
    public static final String indent = "    ";
    private final Storage storage;
    final ArrayList<Task> tasks;
    private static int counter;
    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
        counter = tasks.size();
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.storage = new Storage();
        counter = 0;
    }

    public int len() {
        return counter;
    }

    public boolean isEmpty() {
        return counter == 0;
    }

    public boolean hasTask(int number) {
        return number <= counter;
    }

    /**
     * PLEASE ADD CHECKS THAT i IS SMALLER THAN COUNTER
     * @param i
     * @return
     */
    public Task getTask(int i) {

        return this.tasks.get(i);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 1; i <= counter; i++) {
            result = result + String.format(indent + "%d. %s\n", i, tasks.get(i - 1));
        }
        return result;
    }

    /**
     * Unmarks task at specified index and prints specified task.
     */
    public Task unmarkTask1(int index) throws IndexOutOfBoundsException{
        if (index < counter) {
            tasks.get(index).unmark();
            return tasks.get(index);
        } else {
            throw new IndexOutOfBoundsException("CHIBAI THE INDEX OUT OF BOUNDS LA");
        }
    }

    /**
     * Marks task at specified index and prints specified task.
     */
    public Task markTask1(int index) throws IndexOutOfBoundsException{
        if (index < counter) {
            tasks.get(index).mark();
            return tasks.get(index);
        } else {
            throw new IndexOutOfBoundsException("CHIBAI THE INDEX OUT OF BOUNDS LA");
        }
    }

    /**
     * Adds Task into tasks ArrayList.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
        counter++;
    }

    /**
     * Deletes specified task from tasks
     * If the specified index is out of bounds, an error message will be printed
     * @param i Task number in the task list
     */
    public Task deleteTask1(int i) throws IndexOutOfBoundsException{
        if (i > counter) {
            throw new IndexOutOfBoundsException("Nah cuh your list too short. Try again hoe!");
        } else {
            Task t = tasks.remove(i - 1);
            counter--;
            return t;
        }
    }
}
