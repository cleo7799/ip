package command;

import TaskList.TaskList;
import TaskList.Task;
import storage.Storage;
import ui.Ui;

import java.io.IOException;

public class MarkTaskCommand extends Command {

    private int index;

    public MarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            Task t = tasks.markTask1(this.index);
            storage.save(tasks);
            return ui.showMarkedTask(t);
        } catch (IndexOutOfBoundsException e) {
            return ui.showIndexOutOfBounds();
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
