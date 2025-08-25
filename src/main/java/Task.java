public class Task {
    protected String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}