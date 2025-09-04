import TaskList.*;
import TaskList.Todo;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser class interprets user input
 */
public class Parser {

    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static LocalDateTime parseInputString(String s) throws DateTimeException {
        return LocalDateTime.parse(s, INPUT_FORMAT);
    }

    public static Command parse(String fullCommand) throws InvalidTaskException, DateTimeException,
            DateTimeParseException {
        String words[] = fullCommand.split(" ", 2);
        String command = words[0];
        String arguments = words.length > 1 ? words[1] : "";

        switch (command.toLowerCase()) {
            case "todo":
                if (arguments.isEmpty()) {
                    throw new InvalidTaskException("Missing todo description.");
                }
                return new AddTodoCommand(arguments); // Fallthrough
            case "deadline":
                String[] temp = arguments.split(" /by ");
                if (temp.length != 2) {
                    throw new InvalidTaskException("Invalid deadline task.");
                } else {
                    String description = temp[0];
                    String by = temp[1];
                    if (description.isEmpty()) {
                        throw new InvalidTaskException("Missing deadline description.");
                    }
                    if (by.isEmpty()) {
                        throw new InvalidTaskException("Missing deadline time.");
                    }
                    LocalDateTime dateTime = parseInputString(by);

                    return new AddDeadlineCommand(description, dateTime);
                }
            case "event":
                temp = arguments.split(" /from ");
                if (temp.length != 2) {
                    throw new InvalidTaskException("Invalid event task.");
                } else {
                    String description = temp[0];
                    if (description.isEmpty()) {
                        throw new InvalidTaskException("Missing event description.");
                    }
                    String[] duration = temp[1].split(" /to ");
                    if (duration.length != 2) {
                        throw new InvalidTaskException("Invalid event time.");
                    } else {
                        String from = duration[0];
                        String by = duration[1];
                        if (from.isEmpty()) {
                            throw new InvalidTaskException("Missing event start time.");
                        }
                        if (by.isEmpty()) {
                            throw new InvalidTaskException("Missing event end time.");
                        }
                        LocalDateTime start = parseInputString(from);
                        LocalDateTime end = parseInputString(by);
                        return new AddEventCommand(description, start, end);
                    }
                }
            default:
                throw new InvalidTaskException("Invalid task time.");
        }
    }

    public static Command parseTask(String command, String arguments) throws InvalidTaskException, DateTimeException,
            DateTimeParseException {
        switch (command.toLowerCase()) {
            case "todo":
                if (arguments.isEmpty()) {
                    throw new InvalidTaskException("Missing todo description.");
                }
                return new AddTodoCommand(arguments); // Fallthrough
            case "deadline":
                String[] temp = arguments.split(" /by ");
                if (temp.length != 2) {
                    throw new InvalidTaskException("Invalid deadline task.");
                } else {
                    String description = temp[0];
                    String by = temp[1];
                    if (description.isEmpty()) {
                        throw new InvalidTaskException("Missing deadline description.");
                    }
                    if (by.isEmpty()) {
                        throw new InvalidTaskException("Missing deadline time.");
                    }
                    LocalDateTime dateTime = parseInputString(by);

                    return new AddDeadlineCommand(description, dateTime);
                }
            case "event":
                temp = arguments.split(" /from ");
                if (temp.length != 2) {
                    throw new InvalidTaskException("Invalid event task.");
                } else {
                    String description = temp[0];
                    if (description.isEmpty()) {
                        throw new InvalidTaskException("Missing event description.");
                    }
                    String[] duration = temp[1].split(" /to ");
                    if (duration.length != 2) {
                        throw new InvalidTaskException("Invalid event time.");
                    } else {
                        String from = duration[0];
                        String by = duration[1];
                        if (from.isEmpty()) {
                            throw new InvalidTaskException("Missing event start time.");
                        }
                        if (by.isEmpty()) {
                            throw new InvalidTaskException("Missing event end time.");
                        }
                        LocalDateTime start = parseInputString(from);
                        LocalDateTime end = parseInputString(by);
                        return new AddEventCommand(description, start, end);
                    }
                }
            default:
                throw new InvalidTaskException("Invalid task time.");
        }
    }


}
