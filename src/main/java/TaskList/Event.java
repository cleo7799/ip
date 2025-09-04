package TaskList;

import java.time.LocalDate;
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

    public String startDateTime() {
        return this.from.format(outputFormat);
    }

    public String endDateTime() {
        return this.by.format(outputFormat);
    }

    public boolean sameDate() {
        return this.from.toLocalDate().equals(this.by.toLocalDate());
    }

    public String endTime() {
        return this.by.toLocalTime().format(timeFormat);
    }

    @Override
    public String toStorageString() {
        int done = this.isDone ? 0 : 1;
        return String.format("Event | %d | %s | %s | %s", done, this.name, this.startDateTime(), this.endDateTime());
    }

    @Override
    public String toString() {
        char p = this.isDone ? 'X' : ' ';
        String end = this.sameDate() ? this.endTime() : this.endDateTime();
        return String.format("[E] [%c] %s (from %s to %s)", p, this.name, this.startDateTime(), end);
    }

}