package vicky.command;

import vicky.tasklist.TaskList;
import vicky.storage.Storage;
import vicky.ui.Ui;

public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Checks if command should exit program.
     * @return boolean
     */
    public boolean isExit() {
        return false;
    }
}



