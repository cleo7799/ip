package vicky.command;

import vicky.taskList.TaskList;
import vicky.storage.Storage;
import vicky.ui.Ui;

public class GoodbyeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
