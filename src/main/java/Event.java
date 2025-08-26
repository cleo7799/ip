public class Event extends Deadline {
    protected String from;
    protected String by;

    public Event(String name, String from, String by) {
        super(name, by);
        this.by = by;
        this.from = from;
    }

    @Override
    public String toString() {
        char p = this.done ? 'X' : ' ';
        return String.format("[E] [%c] %s (from: %s to: %s)", p, this.name, this.from, this.by);
    }

}