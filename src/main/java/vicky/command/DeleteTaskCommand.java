package vicky.command;

import vicky.taskList.TaskList;
import vicky.taskList.Task;
import vicky.storage.Storage;
import vicky.ui.Ui;

import java.io.IOException;

public class DeleteTaskCommand extends Command {

    private int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            Task t = tasks.deleteTask1(this.index);
            storage.save(tasks);
            return ui.showDeleteTask(t, tasks.len());
        } catch (IndexOutOfBoundsException e) {
            return ui.showIndexOutOfBounds();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
