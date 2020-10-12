package duke.tasks;

import duke.Storage;
import duke.messages.Messages;
import java.io.IOException;
import java.time.format.DateTimeParseException;


/**
 * Parser class which helps to decode and execute the user command.
 */
public class Parser {

    public static final int EMPTY_LIST_SIZE = 0;
    public static final int LENGTH_FOR_BYE_COMMAND = 3;
    public static final int LENGTH_FOR_LIST_COMMAND = 4;
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_SAVE = "save";
    public static final String COMMAND_TODO = "todo";
    public static final String EMPTY_INPUT = "";
    public static final String EMPTY_SPACE = " ";
    public static final String SPLITTER_FOR_DEADLINE_TASK_DESCRIPTION_AND_DATE = "/by";
    public static final String SPLITTER_FOR_EVENT_TASK_DESCRIPTION_AND_DATE = "/at";
    public static final String SPLITTER_FOR_TASK_DESCRIPTION_AND_DATE = "/";

    private static boolean isRun = true;

    /**
     * Parsers the user input and execute the program accordingly.
     * 1. If the user input is "list",
     * - prints an empty list message if there is no task available,
     * - else program prints all tasks available in the task list.
     * 2. If the user input is "bye",
     * - tasks available are saved to "Duke_output.txt" and prints bye message.
     * 3. If the user input contains "todo",
     * - prints empty todo error message if no task description,
     * - else program adds a new ToDo task and print task added message.
     * 4. If the user input contains "event",
     * - prints empty event error message if no task description or no task date,
     * - else program adds a new Event task and print task added message.
     * 5. If the user input contains "deadline",
     * - prints empty deadline error message if no task description or no task date,
     * - else program adds a new Deadline task and print task added message.
     * 6. If the user input contains "delete",
     * - prints empty delete error message if no task index,
     * - or prints empty list message if there is no task available for deletion,
     * - or prints index out of bound message if the given index is larger than the task list size,
     * - else program removes the task at the given index and print task deleted message.
     * 7. If the user input contains "done",
     * - prints empty done error message if no task index,
     * - or prints empty list message if there is no task available to be marked as done,
     * - or prints task already done message if the task at the given index is already done,
     * - or prints index out of bound message if the given index is larger than the task list size,
     * - else program marks the task at the given index as done and print task done message.
     * 8. If the user input contains "find",
     * - prints an empty find message if there is no find keyword,
     * - or prints a message to indicate no matching task,
     * - else program prints all tasks containing the keyword.
     * 9. If the user input is "help",
     * - prints all available commands for the program.
     * 10. If the user input is "save",
     * - tasks available are saved to "Duke_output.txt" and prints file saved message.
     * All other inputs are treated as invalid input and program prints invalid input error message.
     *
     * @param userInput Input commands from the user.
     * @param taskList TaskList which is used to store a list of tasks.
     */
    public static void parser(String userInput, TaskList taskList) {
        String[] convertedUserInput;
        String taskType;
        String taskDescription;
        String taskDate;
        String findKeyWord;
        int taskIndex;

        convertedUserInput = userInput.trim().split(EMPTY_SPACE);
        taskType = convertedUserInput[0];
        try {
            switch (taskType.toLowerCase()) {
            case COMMAND_LIST:
                if (userInput.trim().length() > LENGTH_FOR_LIST_COMMAND) {
                    Messages.printInvalidInput();
                } else if (taskList.getSize() == EMPTY_LIST_SIZE) {
                    Messages.printEmptyListMessage();
                } else {
                    Messages.printAllTasks(taskList);
                }
                break;
            case COMMAND_BYE:
                if (userInput.trim().length() > LENGTH_FOR_BYE_COMMAND) {
                    Messages.printInvalidInput();
                } else {
                    Storage.saveData(taskList);
                    Messages.printBye();
                    isRun = false;
                }
                break;
            case COMMAND_TODO:
                taskDescription = userInput.toLowerCase().trim().replace(COMMAND_TODO, EMPTY_INPUT);
                if (taskDescription.trim().equals(EMPTY_INPUT)) {
                    Messages.printEmptyTodoError();
                } else {
                    taskList.addTask(new ToDo(taskDescription.trim()));
                    Messages.printTaskAddedMessage(taskList);
                }
                break;
            case COMMAND_EVENT:
                taskDescription = userInput.toLowerCase().trim().replace(COMMAND_EVENT, EMPTY_INPUT);
                if (taskDescription.contains(SPLITTER_FOR_EVENT_TASK_DESCRIPTION_AND_DATE)) {
                    int splitterIndex;
                    splitterIndex = taskDescription.indexOf(SPLITTER_FOR_TASK_DESCRIPTION_AND_DATE);
                    String correctedTaskDescription;
                    correctedTaskDescription = taskDescription.substring(0, splitterIndex);
                    taskDate = taskDescription.substring(splitterIndex + 3, taskDescription.length());
                    if (correctedTaskDescription.trim().equals(EMPTY_INPUT) || taskDate.trim().equals(EMPTY_INPUT)) {
                        Messages.printEmptyEventError();
                    } else {
                        try {
                            taskList.addTask(new Event(correctedTaskDescription.trim(), taskDate.trim()));
                            Messages.printTaskAddedMessage(taskList);
                        } catch (StringIndexOutOfBoundsException a) {
                            Messages.printEmptyEventError();
                        } catch (DateTimeParseException d) {
                            Messages.printWrongDateFormat();
                        }
                    }
                } else {
                    Messages.printCommandFormatError();
                }
                break;
            case COMMAND_DEADLINE:
                taskDescription = userInput.toLowerCase().trim().replace(COMMAND_DEADLINE, EMPTY_INPUT);
                if (taskDescription.contains(SPLITTER_FOR_DEADLINE_TASK_DESCRIPTION_AND_DATE)) {
                    int splitterIndex;
                    splitterIndex = taskDescription.indexOf(SPLITTER_FOR_TASK_DESCRIPTION_AND_DATE);
                    String correctedTaskDescription;
                    correctedTaskDescription = taskDescription.substring(0, splitterIndex);
                    taskDate = taskDescription.substring(splitterIndex + 3, taskDescription.length());
                    if (correctedTaskDescription.trim().equals(EMPTY_INPUT) || taskDate.trim().equals(EMPTY_INPUT)) {
                        Messages.printEmptyDeadlineError();
                    } else {
                        try {
                            taskList.addTask(new Deadline(correctedTaskDescription.trim(), taskDate.trim()));
                            Messages.printTaskAddedMessage(taskList);
                        } catch (StringIndexOutOfBoundsException s) {
                            Messages.printEmptyDeadlineError();
                        } catch (DateTimeParseException d) {
                            Messages.printWrongDateFormat();
                        }
                    }
                } else {
                    Messages.printCommandFormatError();
                }
                break;
            case COMMAND_DELETE:
                try {
                    if (taskList.getSize() == EMPTY_LIST_SIZE) {
                        Messages.printEmptyListMessage();
                    } else {
                        taskIndex = getTaskIndex(convertedUserInput[1]);
                        if (taskIndex > taskList.getSize() || taskIndex <= 0) {
                            Messages.printIndexOutOfBoundMessage();
                        } else {
                            Messages.printTaskDeletedMessage(taskList, taskIndex);
                        }
                    }
                } catch (NumberFormatException n) {
                    Messages.printEmptyDeleteError();
                }
                break;
            case COMMAND_DONE:
                try {
                    if (taskList.getSize() == EMPTY_LIST_SIZE) {
                        Messages.printEmptyListMessage();
                    } else {
                        taskIndex = getTaskIndex(convertedUserInput[1]);
                        if (taskIndex > taskList.getSize() || taskIndex <= 0) {
                            Messages.printIndexOutOfBoundMessage();
                        } else if (taskList.getTask(taskIndex - 1).isDone) {
                            Messages.printTaskAlreadyDoneMessage();
                        } else {
                            taskList.getTask(taskIndex - 1).makeAsDone();
                            Messages.printDoneMessage(taskList.getTask(taskIndex - 1));
                        }
                    }
                } catch (NumberFormatException n) {
                    Messages.printEmptyDoneError();
                }
                break;
            case COMMAND_FIND:
                findKeyWord = userInput.toLowerCase().trim().replace(COMMAND_FIND, EMPTY_INPUT);
                if (findKeyWord.trim().equals(EMPTY_INPUT)) {
                    Messages.printEmptyFindError();
                } else {
                    Messages.printFoundTasks(taskList, findKeyWord.trim());
                }
                break;
            case COMMAND_HELP:
                Messages.printAvailableCommands();
                break;
            case COMMAND_SAVE:
                Storage.saveData(taskList);
                break;
            default:
                Messages.printInvalidInput();
                break;
            }
        } catch (ArrayIndexOutOfBoundsException | IOException a) {
            Messages.printCommandFormatError();
        }
    }

    /**
     * Returns a task index which will be used for task deletion or marking the task done.
     *
     * @param input User input for the program.
     * @return index Task index which is used for task deletion or marking done.
     */
    public static int getTaskIndex(String input) {
        int index = Integer.parseInt(input);
        return index;
    }

    /**
     * Returns the status of the program.
     * The program will keep taking user input unless COMMAND_BYE is detected.
     *
     * @return isRun Status tracker for the program.
     */
    public static boolean getRunStatus() {
        return isRun;
    }

}
