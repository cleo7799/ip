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
     * The method splits the command string into the command type and its arguments,
     * then processes the command accordingly, returning the corresponding Command object.
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
                return parseUnmark(arguments);
            case "mark":
                return parseMark(arguments);
            case "delete":
                return parseDelete(arguments);
            case "todo", "deadline", "event":
                return parseTask(command, arguments);
            case "find":
                return parseFind(arguments);
            case "clear":
                return new ClearAllTasksCommand();
            case "love":
                return parseLove(arguments);
            case "bye":
                return parseBye(arguments);
            default:
                throw new InvalidInputException("Bitch I don't know what that means. :( ");
        }

    }

    /**
     * Parses the "unmark" command arguments and returns an UnmarkTaskCommand object.
     * The method checks that the arguments contain a valid task number and returns a command to unmark the task.
     *
     * @param arguments The arguments string containing the task number to be unmarked.
     * @return The corresponding UnmarkTaskCommand object.
     * @throws InvalidTaskException if the task number is missing or invalid.
     */
    private static UnmarkTaskCommand parseUnmark(String arguments) throws InvalidTaskException {
        if (arguments.isEmpty()) {
            throw new InvalidTaskException("Unmark requires a task number!");
        }
        try {
            int num = Integer.parseInt(arguments) - 1;
            return new UnmarkTaskCommand(num);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException("Invalid task number!");
        }
    }

    /**
     * Parses the "mark" command arguments and returns a MarkTaskCommand object.
     * The method checks that the arguments contain a valid task number and returns a command to mark the task.
     *
     * @param arguments The arguments string containing the task number to be marked.
     * @return The corresponding MarkTaskCommand object.
     * @throws InvalidTaskException if the task number is missing or invalid.
     */
    private static MarkTaskCommand parseMark(String arguments) throws InvalidTaskException {
        if (arguments.isEmpty()) {
            throw new InvalidTaskException("Mark requires a task number!");
        }
        try {
            int num = Integer.parseInt(arguments) - 1;
            return new MarkTaskCommand(num);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException("Invalid task number!");
        }
    }

    /**
     * Parses the "delete" command arguments and returns a DeleteTaskCommand object.
     * The method checks that the arguments contain a valid task number and returns a command to delete the task.
     *
     * @param arguments The arguments string containing the task number to be deleted.
     * @return The corresponding DeleteTaskCommand object.
     * @throws InvalidTaskException if the task number is missing or invalid.
     */
    private static DeleteTaskCommand parseDelete(String arguments) throws InvalidTaskException {
        if (arguments.isEmpty()) {
            throw new InvalidTaskException("Delete requires a task number!");
        }
        try {
            int num = Integer.parseInt(arguments) - 1;
            return new DeleteTaskCommand(num);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException("Invalid task number!");
        }
    }

    /**
     * Parses the "find" command arguments and returns a FindTasksCommand object.
     * The method checks that the arguments contain a valid keyword and returns a command to find tasks based on the keyword.
     *
     * @param arguments The arguments string containing the keyword to search for.
     * @return The corresponding FindTasksCommand object.
     * @throws InvalidTaskException if the keyword is missing or invalid.
     */
    private static FindTasksCommand parseFind(String arguments) throws InvalidTaskException {
        if (arguments.isEmpty()) {
            throw new InvalidTaskException("Find requires a keyword!");
        }
        return new FindTasksCommand(arguments);
    }

    /**
     * Parses the "love" command arguments and returns a LoveCommand object.
     * The method constructs a command that expresses love for the specified target or the default "you".
     *
     * @param arguments The arguments string containing the person or entity to express love for.
     * @return The corresponding LoveCommand object with the target to love.
     */
    private static LoveCommand parseLove(String arguments) {
        if (arguments.isEmpty()) {
            return new LoveCommand("you");
        }
        return new LoveCommand(arguments);
    }

    /**
     * Parses the "bye" command arguments and returns a GoodbyeCommand or DesperateGoodbyeCommand object.
     * The method checks whether arguments are provided to determine the type of goodbye command.
     *
     * @param arguments The arguments string containing a possible additional detail for the goodbye message.
     * @return The corresponding GoodbyeCommand or DesperateGoodbyeCommand object based on the arguments.
     */
    private static Command parseBye(String arguments) {
        if (arguments.isEmpty()) {
            return new GoodbyeCommand();
        }
        return new DesperateGoodbyeCommand();
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
                return parseTodoTask(arguments);
            case "deadline":
                return parseDeadlineTask(arguments);
            case "event":
                return parseEventTask(arguments);
            default:
                throw new InvalidTaskException("Invalid task.");
        }
    }
    //CHECKSTYLE.ON: Indentation

    /**
     * Parses the arguments for a "todo" task and returns an AddTodoCommand object.
     * This method checks that the description is not empty and constructs a command to add the todo task.
     *
     * @param arguments The arguments string containing the description of the todo task.
     * @return The corresponding AddTodoCommand object.
     * @throws InvalidTaskException if the todo description is missing or invalid.
     */
    private static AddTodoCommand parseTodoTask(String arguments) throws InvalidTaskException {
        if (arguments.isEmpty()) {
            throw new InvalidTaskException("Missing todo description.");
        }
        return new AddTodoCommand(arguments);
    }

    /**
     * Parses the arguments for a "deadline" task and returns an AddDeadlineCommand object.
     * This method checks that both the description and deadline time are provided and constructs a command to add the deadline task.
     *
     * @param arguments The arguments string containing the description and deadline time of the task.
     * @return The corresponding AddDeadlineCommand object with the parsed description and deadline time.
     * @throws InvalidTaskException if the description or deadline time is missing or invalid.
     * @throws DateTimeException if date-time parsing fails.
     * @throws DateTimeParseException if the date-time format is incorrect.
     */
    private static AddDeadlineCommand parseDeadlineTask(String arguments) throws InvalidTaskException, DateTimeException,
            DateTimeParseException {
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
    }

    /**
     * Parses the arguments for an "event" task and returns an AddEventCommand object.
     * This method checks that both the description and event start and end times are provided and constructs a command to add the event task.
     *
     * @param arguments The arguments string containing the description, start time, and end time of the event.
     * @return The corresponding AddEventCommand object with the parsed description, start time, and end time.
     * @throws InvalidTaskException if the description, start time, or end time is missing or invalid.
     * @throws DateTimeException if date-time parsing fails.
     * @throws DateTimeParseException if the date-time format is incorrect.
     */
    private static AddEventCommand parseEventTask(String arguments) throws InvalidTaskException, DateTimeException,
            DateTimeParseException {
        String[] temp = arguments.split(" /from ");
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
    }
}
