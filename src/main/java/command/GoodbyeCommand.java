package command;

import TaskList.TaskList;
import storage.Storage;
import ui.Ui;

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
