package TaskList;

public class Event extends Deadline {
    protected String from;
    protected String by;

    public Event(String name, String from, String by) {
        super(name, by);
        this.by = by;
        this.from = from;
    }

    public Event(String name, String from, String by, boolean isDone) {
        super(name, by, isDone);
        this.by = by;
        this.from = from;
    }

    @Override
    public String toStorageString() {
        int done = this.isDone ? 0 : 1;
        return String.format("Event | %d | %s | %s | %s", done, this.name, this.from, this.by);
    }

    @Override
    public String toString() {
        char p = this.isDone ? 'X' : ' ';
        return String.format("[E] [%c] %s (from: %s to: %s)", p, this.name, this.from, this.by);
    }

}