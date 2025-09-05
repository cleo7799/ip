package vicky.command;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vicky.taskList.*;
import vicky.storage.Storage;
import vicky.ui.Ui;
import java.time.LocalDateTime;
import java.io.IOException;

public class AddEventCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private AddEventCommand addEventCommand;
    private LocalDateTime start;
    private LocalDateTime end;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();

        start = LocalDateTime.of(2025, 9, 5, 10, 0); // Start time: 2025-09-05 10:00
        end = LocalDateTime.of(2025, 9, 5, 12, 0);   // End time: 2025-09-05 12:00
        addEventCommand = new AddEventCommand("Team Meeting", start, end);
    }

    @Test
    public void testExecute() throws IOException {
        String result = addEventCommand.execute(taskList, ui, storage);
        assertEquals(1, taskList.len());
        Event event = (Event) taskList.getTask(0);

        assertEquals("    Ok! I've added this task:\n        " +
                "[E] [ ] Team Meeting (from 05-09-2025 10:00 to 12:00)\n    " +
                "Now you have 1 tasks in your list. :)" +
                "\n    __________________________________________________________________________\n", result);
    }

    @Test
    public void testExecuteThrowsIOException() throws IOException {
        Storage faultyStorage = new Storage() {
            @Override
            public void save(TaskList taskList) throws IOException {
                throw new IOException("Error saving tasks");
            }
        };
        assertThrows(IOException.class, () -> addEventCommand.execute(taskList, ui, faultyStorage));
    }

    @Test
    public void testIsExit() {
        assertFalse(addEventCommand.isExit());
    }
}
