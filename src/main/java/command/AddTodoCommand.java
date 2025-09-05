package command;

import TaskList.TaskList;
import TaskList.Todo;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

public class AddTodoCommand extends Command {

    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Todo todo = new Todo(this.description);
        tasks.addTask(todo);
        storage.save(tasks);
        return ui.showAddedTask(todo, tasks.len());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
