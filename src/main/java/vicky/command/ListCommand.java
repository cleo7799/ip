package vicky.command;

import vicky.taskList.TaskList;
import vicky.storage.Storage;
import vicky.ui.Ui;

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
