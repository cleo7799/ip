package TaskList;

import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String name;
    protected boolean isDone;
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");


    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }


    public static Task fromStorageString(String s) throws IllegalArgumentException{
        String[] temp = s.split("\\s\\|\\s");
        String taskType = temp[0];

        if (temp.length < 3) {
            System.out.println("length less than 3");
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
            //System.out.println(temp[0] + temp[1] + temp[2]);
            throw new IllegalArgumentException("Corrupted task status value: " + taskStatus); //+ taskStatus
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
            LocalDateTime deadline = parseOutputString(temp[3]);
            return new Deadline(taskDescriptor, deadline, isDone); //Fallthrough
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

    public static LocalDateTime parseOutputString(String s) throws DateTimeException {
        return LocalDateTime.parse(s, OUTPUT_FORMAT);

    }
    public abstract String toStorageString();

    @Override
    public abstract String toString();

}
