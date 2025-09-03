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
        int done = this.isDone ? 0 : 1;
        return String.format("Todo | %d | %s", done, this.name);
    }

    @Override
    public String toString() {
        char p = this.isDone ? 'X' : ' ';
        return String.format("[T] [%c] %s", p, this.name);
    }
}
