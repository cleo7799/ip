package vicky.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import vicky.command.AddDeadlineCommand;
import vicky.command.AddEventCommand;
import vicky.command.AddTodoCommand;
import vicky.command.ClearAllTasksCommand;
import vicky.command.Command;
import vicky.command.DeleteTaskCommand;
import vicky.command.DesperateGoodbyeCommand;
import vicky.command.FindTasksCommand;
import vicky.command.GoodbyeCommand;
import vicky.command.ListCommand;
import vicky.command.LoveCommand;
import vicky.command.MarkTaskCommand;
import vicky.command.UnmarkTaskCommand;
import vicky.exception.InvalidInputException;
import vicky.exception.InvalidTaskException;

/**
 * Class responsible for parsing user input and managing commands.
 *
 * @author Rachel Wong
 */
public class Parser {

    /** Input date-time format for parsing user input. */
    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
    /** Output date-time format for displaying parsed data. */
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Parses a string into a LocalDateTime object based on the input format.
     *
     * @param s The input string to be parsed.
     * @return The parsed LocalDateTime object.
     * @throws DateTimeException if the input string is in an invalid format.
     */
    public static LocalDateTime parseInputString(String s) throws DateTimeException {
        return LocalDateTime.parse(s, INPUT_FORMAT);
    }

    /**
     * Parses a string into a LocalDateTime object based on the output format.
     *
     * @param s The input string to be parsed.
     * @return The parsed LocalDateTime object.
     * @throws DateTimeException if the input string is in an invalid format.
     */
    public static LocalDateTime parseOutputString(String s) throws DateTimeException {
        return LocalDateTime.parse(s, OUTPUT_FORMAT);
    }

    //CHECKSTYLE.OFF: Indentation
    /**
     * Parses a full command string into a corresponding Command object.
     *
     * @param fullCommand The full user command string.
     * @return The corresponding Command object based on the parsed command.
     * @throws InvalidTaskException if the task in the command is invalid.
     * @throws InvalidInputException if the input arguments are invalid.
     * @throws DateTimeException if date-time parsing fails.
     * @throws DateTimeParseException if the date-time format is incorrect.
     */
    public static Command parse(String fullCommand) throws InvalidTaskException, InvalidInputException,
            DateTimeException, DateTimeParseException {
        String[] words = fullCommand.split(" ", 2);
        String command = words[0];
        String arguments = words.length > 1 ? words[1] : "";

        switch (command.toLowerCase()) {
            case "list":
                return new ListCommand(); //Fallthrough
            case "unmark":
                assert !arguments.isEmpty() : "Arguments should not be empty.";
                if (arguments.isEmpty()) {
                    throw new InvalidTaskException("Unmark requires a task number!");
                }
                try {
                    int num = Integer.parseInt(arguments) - 1;
                    return new UnmarkTaskCommand(num);
                } catch (NumberFormatException e) {
                    throw new InvalidTaskException("Invalid task number!");
                }
                //Fallthrough
            case "mark":
                assert !arguments.isEmpty() : "Arguments should not be empty.";
                if (arguments.isEmpty()) {
                    throw new InvalidTaskException("Mark requires a task number!");
                }
                try {
                    int num = Integer.parseInt(arguments) - 1;
                    return new MarkTaskCommand(num);
                } catch (NumberFormatException e) {
                    throw new InvalidTaskException("Invalid task number!");
                }
                //Fallthrough
            case "delete":
                assert !arguments.isEmpty() : "Arguments should not be empty.";
                if (arguments.isEmpty()) {
                    throw new InvalidTaskException("Delete requires a task number!");
                }
                try {
                    int num = Integer.parseInt(arguments) - 1;
                    return new DeleteTaskCommand(num);
                } catch (NumberFormatException e) {
                    throw new InvalidTaskException("Invalid task number!");
                }
                //Fallthrough
            case "todo", "deadline", "event":
                return parseTask(command, arguments);
                //Fallthrough
            case "find":
                assert !arguments.isEmpty() : "Arguments should not be empty.";
                if (arguments.isEmpty()) {
                    throw new InvalidTaskException("Find requires a keyword!");
                }
                return new FindTasksCommand(arguments);
            case "clear":
                return new ClearAllTasksCommand();
            case "love":
                assert !arguments.isEmpty() : "Arguments should not be empty.";
                if (arguments.isEmpty()) {
                    return new LoveCommand("you");
                }
                return new LoveCommand(arguments);

            case "bye":
                assert !arguments.isEmpty() : "Arguments should not be empty.";
                if (arguments.isEmpty()) {
                    return new GoodbyeCommand();
                }
                return new DesperateGoodbyeCommand();
                //Fallthrough
            default:
                throw new InvalidInputException("Bitch I don't know what that means. :(");
        }

    }


    /**
     * Parses an arguments string into a corresponding task Command object.
     *
     * @param command The command string specifying the task type.
     * @param arguments The arguments string of the task.
     * @return The corresponding Command object based on the parsed command.
     * @throws InvalidTaskException if the task in the command is invalid.
     * @throws InvalidInputException if the input arguments are invalid.
     * @throws DateTimeException if date-time parsing fails.
     * @throws DateTimeParseException if the date-time format is incorrect.
     */
    public static Command parseTask(String command, String arguments) throws InvalidTaskException, DateTimeException,
            DateTimeParseException {
        switch (command.toLowerCase()) {
            case "todo":
                assert !arguments.isEmpty() : "Arguments should not be empty.";
                if (arguments.isEmpty()) {
                    throw new InvalidTaskException("Missing todo description.");
                }
                return new AddTodoCommand(arguments); // Fallthrough
            case "deadline":
                String[] temp = arguments.split(" /by ");
                assert temp.length == 2 : "Temp length should be 2.";
                if (temp.length != 2) {
                    throw new InvalidTaskException("Invalid deadline task.");
                } else {
                    String description = temp[0];
                    String by = temp[1];

                    assert !description.isEmpty() : "Description should not be empty.";

                    if (description.isEmpty()) {
                        throw new InvalidTaskException("Missing deadline description.");
                    }

                    assert !by.isEmpty() : "By should not be empty.";
                    if (by.isEmpty()) {
                        throw new InvalidTaskException("Missing deadline time.");
                    }
                    LocalDateTime dateTime = parseInputString(by);

                    return new AddDeadlineCommand(description, dateTime);
                }
            case "event":
                temp = arguments.split(" /from ");

                assert temp.length == 2 : "Temp length should be 2.";
                if (temp.length != 2) {
                    throw new InvalidTaskException("Invalid event task.");
                } else {
                    String description = temp[0];

                    assert !description.isEmpty() : "Description should not be empty.";
                    if (description.isEmpty()) {
                        throw new InvalidTaskException("Missing event description.");
                    }
                    String[] duration = temp[1].split(" /to ");
                    assert duration.length == 2 : "Duration length should be 2.";
                    if (duration.length != 2) {
                        throw new InvalidTaskException("Invalid event time.");
                    } else {
                        String from = duration[0];
                        String by = duration[1];
                        assert !from.isEmpty() : "From should not be empty.";
                        assert !by.isEmpty() : "By should not be empty.";

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
    //CHECKSTYLE.ON: Indentation
}
