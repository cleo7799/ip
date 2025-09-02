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
        return String.format("Deadline|" + this.name + "|" + this.isDone + "|" + this.by);
    }

    @Override
    public String toString() {
        char p = this.isDone ? 'X' : ' ';
        return String.format("[D] [%c] %s (by: %s)", p, this.name, this.by);
    }

}
