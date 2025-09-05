package vicky.command;

import vicky.taskList.TaskList;
import vicky.taskList.Deadline;
import vicky.storage.Storage;
import vicky.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {

    private String description;
    private LocalDateTime dateTime;

    public AddDeadlineCommand(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Deadline deadline = new Deadline(this.description, this.dateTime);
        tasks.addTask(deadline);
        storage.save(tasks);
        return ui.showAddedTask(deadline, tasks.len());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
