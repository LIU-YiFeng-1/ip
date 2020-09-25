package duke.tasks;

import duke.Storage;
import duke.messages.Messages;
import java.io.IOException;

/**
 * Parser class which helps to decode and execute the user command.
 */
public class Parser {
    public static final int LENGTH_FOR_LIST_COMMAND = 4;
    public static final int LENGTH_FOR_BYE_COMMAND = 3;
    public static final String EMPTY_INPUT = "";

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
        int taskIndex;

        convertedUserInput = userInput.trim().split(" ");
        taskType = convertedUserInput[0];
        try {
            switch (taskType.toLowerCase()) {
            case "list":
                if (userInput.trim().length() > LENGTH_FOR_LIST_COMMAND) {
                    Messages.printInvalidInput();
                } else if (taskList.getSize() == 0) {
                    Messages.printEmptyListMessage();
                } else {
                    Messages.printAllTasks(taskList);
                }
                break;
            case "bye":
                if (userInput.trim().length() > LENGTH_FOR_BYE_COMMAND) {
                    Messages.printInvalidInput();
                } else {
                    Storage.saveData(taskList);
                    Messages.printBye();
                    isRun = false;
                }
                break;
            case "todo":
                taskDescription = userInput.toLowerCase().trim().replace("todo", "");
                if (taskDescription.trim().equals(EMPTY_INPUT)) {
                    Messages.printEmptyTodoError();
                } else {
                    taskList.addTask(new ToDo(taskDescription));
                    Messages.printTaskAddedMessage(taskList);
                }
                break;
            case "event":
                taskDescription = userInput.toLowerCase().trim().replace("event", "");
                if (taskDescription.contains("/")) {
                    int splitterIndex;
                    splitterIndex = taskDescription.indexOf("/");
                    String correctedTaskDescription;
                    correctedTaskDescription = taskDescription.substring(0, splitterIndex);
                    taskDate = taskDescription.substring(splitterIndex + 1, taskDescription.length());
                    if (correctedTaskDescription.trim().equals(EMPTY_INPUT) || taskDate.trim().equals(EMPTY_INPUT)) {
                        Messages.printEmptyEventError();
                    } else {
                        try {
                            taskList.addTask(new Event(correctedTaskDescription, taskDate));
                            Messages.printTaskAddedMessage(taskList);
                        } catch (StringIndexOutOfBoundsException s) {
                            Messages.printEmptyEventError();
                        }
                    }
                } else {
                    Messages.printCommandFormatError();
                }
                break;
            case "deadline":
                taskDescription = userInput.toLowerCase().trim().replace("deadline ", "");
                if (taskDescription.contains("/")) {
                    int splitterIndex;
                    splitterIndex = taskDescription.indexOf("/");
                    String correctedTaskDescription;
                    correctedTaskDescription = taskDescription.substring(0, splitterIndex);
                    taskDate = taskDescription.substring(splitterIndex + 1, taskDescription.length());
                    if (correctedTaskDescription.trim().equals(EMPTY_INPUT) || taskDate.trim().equals(EMPTY_INPUT)) {
                        Messages.printEmptyEventError();
                    } else {
                        try {
                            taskList.addTask(new Deadline(correctedTaskDescription, taskDate));
                            Messages.printTaskAddedMessage(taskList);
                        } catch (StringIndexOutOfBoundsException s) {
                            Messages.printEmptyEventError();
                        }
                    }
                } else {
                    Messages.printCommandFormatError();
                }
                break;
            case "delete":
                try {
                    if (taskList.getSize() == 0) {
                        Messages.printEmptyListMessage();
                    } else {
                        taskIndex = getTaskIndex(convertedUserInput[1]);
                        if (taskIndex > taskList.getSize()) {
                            Messages.printIndexOutOfBoundMessage();
                        } else {
                            Messages.printTaskDeletedMessage(taskList, taskIndex);
                        }
                    }
                } catch (NumberFormatException n) {
                    Messages.printEmptyDeleteError();
                }
                break;
            case "done":
                try {
                    if (taskList.getSize() == 0) {
                        Messages.printEmptyListMessage();
                    } else {
                        taskIndex = getTaskIndex(convertedUserInput[1]);
                        if (taskIndex > taskList.getSize()) {
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
            case "find":
                System.out.println("this is finding some keyword, not implemented yet.");
                Messages.printLine();
                break;
            case "help":
                Messages.printAvailableCommands();
                break;
            case "save":
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

    public static String getTaskDate(String input) {
        String[] output;
        String correctedTaskDate;
        output = input.trim().split("/");
        correctedTaskDate = output[1].trim();
        return correctedTaskDate;
    }

    public static int getTaskIndex(String input) {
        String convertedInput;
        int index;
        convertedInput = input.replaceAll("[^0-9]", "");//replace all non-number with space
        index = Integer.parseInt(convertedInput);
        return index;
    }

    public static boolean getRunStatus() {
        return isRun;
    }

}
