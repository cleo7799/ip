package command;

import TaskList.TaskList;
import storage.Storage;
import ui.Ui;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
