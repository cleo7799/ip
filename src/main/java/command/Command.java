package command;

import TaskList.TaskList;
import TaskList.Task;
import TaskList.Todo;
import TaskList.Deadline;
import TaskList.Event;
import storage.Storage;
import ui.Ui;

import java.time.LocalDateTime;
import java.io.IOException;

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



