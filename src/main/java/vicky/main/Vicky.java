package vicky.main;

import java.io.IOException;
import java.util.Scanner;

import vicky.parser.Parser;
import vicky.taskList.TaskList;
import vicky.exception.InvalidTaskException;
import vicky.exception.InvalidInputException;
import vicky.command.Command;
import vicky.storage.Storage;
import vicky.ui.Ui;

public class Vicky {
    public static final String indent = "    ";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Vicky() {
        this.ui = new Ui();
        this.storage = new Storage();

        try {
            this.storage.init();
            this.tasks = new TaskList(this.storage.load(), this.storage);
        } catch (IOException e) {
            System.err.println(ui.showLoadingError(e.getMessage()));
            this.tasks = new TaskList();
        }
    }

    public void run() {
        System.out.print(ui.showGreeting());
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = scanner.nextLine();
            try {
                Command command = Parser.parse(fullCommand);
                String output = command.execute(tasks, ui, storage);
                System.out.print(output);
                isExit = command.isExit();

            } catch (InvalidInputException e) {
                System.err.print(ui.showError(e.getMessage() + "Please enter a valid command!"));
            } catch (InvalidTaskException e) {
                System.err.print(ui.showError(e.getMessage()));
            } catch (Exception e) {
                System.err.print(ui.showError(e.getMessage()));
            }
        }
    }

    public static void main(String[] args) {
        new Vicky().run();
    }

}


