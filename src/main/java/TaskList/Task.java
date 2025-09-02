package TaskList;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }


    public static Task fromStorageString(String s) throws IllegalArgumentException{
        String[] temp = s.split("|");
        String taskType = temp[0];
        if (temp.length < 3) {
            throw new IllegalArgumentException("Missing data: Only " + temp.length + " fields received.");
        }
        boolean isDone;
        if (temp[2].equals("true")) {
            isDone = true;
        } else if (temp[2].equals("false")) {
            isDone = false;
        } else {
            throw new IllegalArgumentException("isDone variable of task is wrong.");
        }

        switch (taskType) {
        case "Todo":
            return new Todo(temp[1], isDone);
        case "Deadline":
            return new Deadline(temp[1], temp[2], isDone); //Fallthrough
        case "Event":
            return new Event(temp[1], temp[2], temp[3], isDone); //Fallthrough
        default:
            throw new IllegalArgumentException("Unknown task type: " + taskType); //Fallthrough
        }
    }

    public abstract String toStorageString();

    @Override
    public abstract String toString();

}
