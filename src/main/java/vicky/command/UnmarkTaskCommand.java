package vicky.command;

import vicky.taskList.TaskList;
import vicky.taskList.Task;
import vicky.storage.Storage;
import vicky.ui.Ui;

import java.io.IOException;

public class UnmarkTaskCommand extends Command {

    private int index;

    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            Task t = tasks.unmarkTask(this.index);
            storage.save(tasks);
            return ui.showUnmarkedTask(t);
        } catch (IndexOutOfBoundsException e) {
            return ui.showIndexOutOfBounds();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
