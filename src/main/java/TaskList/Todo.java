package TaskList;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toStorageString() {
        return String.format("Todo|" + this.name + "|" + this.isDone);
    }

    @Override
    public String toString() {
        char p = this.isDone ? 'X' : ' ';
        return String.format("[T] [%c] %s", p, this.name);
    }
}
