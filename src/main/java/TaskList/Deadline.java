package TaskList;

public class Deadline extends Todo {
    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }
    
    public Deadline(String name, String by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    @Override
    public String toStorageString() {
        int done = this.isDone ? 0 : 1;
        return String.format("Deadline | %d | %s | %s", done, this.name, this.by);
    }

    @Override
    public String toString() {
        char p = this.isDone ? 'X' : ' ';
        return String.format("[D] [%c] %s (by: %s)", p, this.name, this.by);
    }

}
