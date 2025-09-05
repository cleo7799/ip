package vicky.command;

import java.io.IOException;
import java.time.LocalDateTime;

import vicky.tasklist.Event;
import vicky.tasklist.TaskList;

import vicky.ui.Ui;

import vicky.storage.Storage;

public class AddEventCommand extends Command {

    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    public AddEventCommand(String description, LocalDateTime start, LocalDateTime end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Event event = new Event(this.description, this.start, this.end);
        tasks.addTask(event);
        storage.save(tasks);
        return ui.showAddedTask(event, tasks.len());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
