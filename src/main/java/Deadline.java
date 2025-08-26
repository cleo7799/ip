public class Deadline extends Task {
    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        char p = this.done ? 'X' : ' ';
        return String.format("[D] [%c] %s (by: %s)", p, this.name, this.by);
    }

}
