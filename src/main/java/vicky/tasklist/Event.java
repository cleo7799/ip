package vicky.tasklist;

import java.time.LocalDateTime;

public class Event extends Deadline {
    protected LocalDateTime from;
    protected LocalDateTime by;


    public Event(String name, LocalDateTime from, LocalDateTime by) {
        super(name, by);
        this.by = by;
        this.from = from;
    }

    public Event(String name, LocalDateTime from, LocalDateTime by, boolean isDone) {
        super(name, by, isDone);
        this.by = by;
        this.from = from;
    }

    public String getStartDateTime() {
        return this.from.format(outputFormat);
    }

    public String getEndDateTime() {
        return this.by.format(outputFormat);
    }

    public boolean isSameDate() {
        return this.from.toLocalDate().equals(this.by.toLocalDate());
    }

    public String getEndTime() {
        return this.by.toLocalTime().format(timeFormat);
    }

    @Override
    public String toStorageString() {
        int done = this.isDone ? 0 : 1;
        return String.format("Event | %d | %s | %s | %s", done, this.name, this.getStartDateTime(), this.getEndDateTime());
    }

    @Override
    public String toString() {
        char p = this.isDone ? 'X' : ' ';
        String end = this.isSameDate() ? this.getEndTime() : this.getEndDateTime();
        return String.format("[E] [%c] %s (from %s to %s)", p, this.name, this.getStartDateTime(), end);
    }

}