public class Task {
    protected String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        char p = this.done ? 'X' : ' ';
        return String.format("[T] [%c] %s", p, this.name);
    }

}
