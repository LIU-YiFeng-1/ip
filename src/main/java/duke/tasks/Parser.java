package duke.tasks;

import duke.Storage;
import duke.messages.Messages;

import java.io.IOException;

public class Parser {
    public static final int LENGTH_FOR_LIST_COMMAND = 4;
    public static final int LENGTH_FOR_BYE_COMMAND = 3;
    public static final String EMPTY_TASK_DESCRIPTION = " ";

    private static boolean isRun = true;

    public static void parser(String userInput, TaskList taskList) throws ArrayIndexOutOfBoundsException {
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
                taskDescription = userInput.toLowerCase().replace("todo ", "");
                if (taskDescription.trim().equals(EMPTY_TASK_DESCRIPTION)) {
                    Messages.printEmptyTodoError();
                } else {
                    taskList.addTask(new ToDo(taskDescription));
                    Messages.printTaskAddedMessage(taskList);
                }
                break;
            case "event":
                taskDescription = userInput.toLowerCase().replace("event ", "");
                if (taskDescription.contains("/")) {
                    int splitterIndex;
                    splitterIndex = taskDescription.indexOf("/");
                    String correctedTaskDescription;
                    correctedTaskDescription = taskDescription.substring(0, splitterIndex);
                    taskDate = taskDescription.substring(splitterIndex + 1, taskDescription.length());
                    try {
                        taskList.addTask(new Event(correctedTaskDescription, taskDate));
                        Messages.printTaskAddedMessage(taskList);
                    } catch (StringIndexOutOfBoundsException s) {
                        Messages.printEmptyEventError();
                    }
                } else {
                    Messages.printCommandFormatError();
                }
                break;
            case "deadline":
                taskDescription = userInput.toLowerCase().replace("deadline ", "");
                if (taskDescription.contains("/")) {
                    int splitterIndex;
                    splitterIndex = taskDescription.indexOf("/");
                    String correctedTaskDescription;
                    correctedTaskDescription = taskDescription.substring(0, splitterIndex);
                    taskDate = taskDescription.substring(splitterIndex + 1, taskDescription.length());
                    try {
                        taskList.addTask(new Deadline(correctedTaskDescription, taskDate));
                        Messages.printTaskAddedMessage(taskList);
                    } catch (StringIndexOutOfBoundsException s) {
                        Messages.printEmptyEventError();
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
