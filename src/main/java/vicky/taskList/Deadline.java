package vicky.taskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Todo {
    protected LocalDateTime by;
    protected static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    protected static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }
    
    public Deadline(String name, LocalDateTime by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    public String deadlineDateTime() {
        return this.by.format(outputFormat);
    }

    @Override
    public String toStorageString() {
        int done = this.isDone ? 0 : 1;
        return String.format("Deadline | %d | %s | %s", done, this.name, this.deadlineDateTime());
    }

    @Override
    public String toString() {
        char p = this.isDone ? 'X' : ' ';
        return String.format("[D] [%c] %s (by: %s)", p, this.name, deadlineDateTime());
    }

}
