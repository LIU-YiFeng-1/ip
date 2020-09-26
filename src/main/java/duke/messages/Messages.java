package duke.messages;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Messages class which deals with user interaction.
 */
public class Messages {

    public static final String SAD_FACE_EMOJI = "\u2639 ";

    /** Prints a dotted line to separate the content. */
    public static void printLine() {
        System.out.println("----------------------------------"
                + "-------------------------------------------");
    }

    /** Prints a good bye message to the user. */
    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
        printLine();
    }

    /** Prints a greeting message to the user. */
    public static void printGreeting() {
        System.out.println("Hello! I'm Duke");
        printImportingMessage();
        printDoneLoadingMessage();
        System.out.println("Type [help] to view available commands!");;
        printLine();
    }

    /** Prints a message to inform the user that the past record has been loaded. */
    public static void printDoneLoadingMessage() {
        System.out.println("Done loading past record onto your task list!\n"
                + "Updating task status based on past record\n"
                + "updating...\n"
                + ".\n"
                + ".\n"
                + ".\n"
                + ".\n"
                + "Done updating! Please type \"List\" to check!");
        printLine();
    }

    /** Prints a data loading message to the user. */
    public static void printImportingMessage() {
        System.out.println("Importing data from previous record"
                + System.lineSeparator()
                + ".\n"
                + ".\n"
                + ".\n"
                + ".\n"
                + "please wait :)");
        printLine();
    }

    /** Prints a welcome message to the user. */
    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printGreeting();
    }

    /** Prints all available commands to the user. */
    public static void printAvailableCommands() {
        System.out.println("Available Commands:\n"
                + "1. list\n"
                + "2. done (e.g done 1)\n"
                + "3. delete (e.g delete 1)\n"
                + "4. todo (e.g todo homework)\n"
                + "5. event (e.g event meeting /at YYYY-MM-DD)\n"
                + "6. deadline (e.g deadline project /by YYYY-MM-DD)\n"
                + "7. find (e.g find monday)\n"
                + "8. save\n"
                + "9. bye\n"
                + "p.s all other inputs will be ignored!\n"
                + "Please enter your command:");
        printLine();
    }

    /**
     * Prints a task done message to the user.
     *
     * @param task Task which to be marked done.
     */
    public static void printDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n"
                + "  ["
                + task.getType()
                + "]"
                + "["
                + task.getStatusIcon()
                + "] " + task.getTaskDescription()
                + task.getFormattedDate());
        printLine();
    }

    /**
     * Prints all stored tasks in the task list.
     *
     * @param taskList TaskList which contains all stored tasks.
     */
    public static void printAllTasks(TaskList taskList) {
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println(i + 1 + ".[" + taskList.getTask(i).getType() + "]"
                    + "[" + taskList.getTask(i).getStatusIcon() + "] "
                    + taskList.getTask(i).getTaskDescription()
                    + taskList.getTask(i).getFormattedDate());
        }
        printLine();
    }


    /**
     * Prints all stored tasks which contain the key word.
     *
     * @param taskList TaskList which contains all stored tasks.
     * @param keyword User defined key word which is to be searched through all tasks.
     */
    public static void printFoundTasks(TaskList taskList, String keyword) {
        int numberOfFoundTasks = 0;
        System.out.println("Searching for matching tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i).getDescriptionForFind().contains(keyword)) {
                numberOfFoundTasks++;
                int taskIndex = i+1;
                System.out.println(numberOfFoundTasks + ".[" + taskList.getTask(i).getType() + "]"
                        + "[" + taskList.getTask(i).getStatusIcon() + "] "
                        + taskList.getTask(i).getTaskDescription()
                        + taskList.getTask(i).getFormattedDate()
                        + " [task index: " + taskIndex + "]");
            }
        }
        System.out.println(System.lineSeparator() +
                "To perform done or delete command, refer to the task index number\n");

        if (numberOfFoundTasks == 0) {
            System.out.println(SAD_FACE_EMOJI + "There is no matching task with" + keyword);
        }
        printLine();
    }

    /** Prints an error message when user input empty ToDo task. */
    public static void printEmptyTodoError() {
        System.out.println(SAD_FACE_EMOJI + "OOPS!!! The description of a todo cannot be empty.");
        Messages.printLine();
    }

    /** Prints an error message when user input empty Event task. */
    public static void printEmptyEventError() {
        System.out.println(SAD_FACE_EMOJI + "OOPS!!! The description / date of an event cannot be empty.");
        Messages.printLine();
    }

    /** Prints an error message when user input empty Deadline task. */
    public static void printEmptyDeadlineError() {
        System.out.println(SAD_FACE_EMOJI + "OOPS!!! The description / date of a deadline cannot be empty.");
        Messages.printLine();
    }

    /** Prints an error message when user did not input task number to be deleted. */
    public static void printEmptyDeleteError() {
        System.out.println(SAD_FACE_EMOJI  + "OOPS!!! The task number to be deleted cannot be empty.");
        Messages.printLine();
    }

    /** Prints an error message when user did not input task number to be marked as done. */
    public static void printEmptyDoneError() {
        System.out.println(SAD_FACE_EMOJI  + "OOPS!!! The task number to be done cannot be empty.");
        Messages.printLine();
    }

    /** Prints an error message when user did not input find key word. */
    public static void printEmptyFindError() {
        System.out.println(SAD_FACE_EMOJI + "OOPS!!! The find keyword cannot be empty.");
        Messages.printLine();
    }

    /**
     * Prints a message to indicate a task is added successfully.
     *
     * @param taskList TaskList which contains all stored tasks.
     */
    public static void printTaskAddedMessage(TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [" + taskList.getTask(taskList.getSize()-1).getType() + "]"
                + "[" + taskList.getTask(taskList.getSize()-1).getStatusIcon() + "] "
                + taskList.getTask(taskList.getSize()-1).getTaskDescription()
                + taskList.getTask(taskList.getSize()-1).getFormattedDate());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
        Messages.printLine();
    }

    /**
     * Prints a message to indicate a task is added successfully.
     *
     * @param taskList TaskList which contains all stored tasks.
     * @param taskNumberToDelete Index of task to be deleted.
     */
    public static void printTaskDeletedMessage(TaskList taskList, int taskNumberToDelete) {
        System.out.println("Got it. I've removed this task:");
        System.out.println("  [" + taskList.getTask(taskNumberToDelete - 1).getType() + "]"
                + "[" + taskList.getTask(taskNumberToDelete - 1).getStatusIcon() + "] "
                + taskList.getTask(taskNumberToDelete - 1).getTaskDescription()
                + taskList.getTask(taskNumberToDelete - 1).getFormattedDate());
        taskList.deleteTask(taskNumberToDelete - 1);
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
        Messages.printLine();
    }

    /**
     * Prints an error message when the index of task to be deleted
     * or marked as done is larger than the size of the list.
     */
    public static void printIndexOutOfBoundMessage() {
        System.out.println("The task number is out of bound! Please type \"list\"");
        Messages.printLine();
    }

    /**
     * Prints an error message when the user input is different from the available commands.
     * Available commands are as follows:
     * 1. list
     * 2. done (e.g done 1)
     * 3. delete (e.g delete 1)
     * 4. todo (e.g todo homework)
     * 5. event (e.g event meeting /at YYYY-MM-DD)
     * 6. deadline (e.g deadline project /by YYYY-MM-DD)
     * 7. find (e.g find monday)
     * 8. save
     * 9. bye
     */
    public static void printInvalidInput() {
        System.out.println(SAD_FACE_EMOJI + "OOPS!!! I'm sorry, but I don't know what that means :-(");
        printLine();
    }

    /** Prints a message to indicate that there is no task in the list. */
    public static void printEmptyListMessage() {
        System.out.println("The list is empty");
        printLine();
    }

    /** Prints a message to indicate that the task to be marked as done is already done. */
    public static void printTaskAlreadyDoneMessage() {
        System.out.println("Chill man, this task is completed!");
        Messages.printLine();
    }

    /** Prints an error message when user input is not following the correct command format. */
    public static void printCommandFormatError() {
        System.out.println(SAD_FACE_EMOJI + "OOPS!!! Wrong input format. Please type HELP to see command examples");
        printLine();
    }

    /** Prints a message when Duke_output.txt has no content. */
    public static void printNoPastRecordMessage() {
        System.out.println("There is no past record! You are a 1st time user");
        Messages.printLine();
    }

    /** Prints an error message when user input is not following the correct date format. */
    public static void printWrongDateFormat() {
        System.out.println(SAD_FACE_EMOJI + "OOPS!!! Wrong date format. Please type HELP to see command examples");
        printLine();
    }
}
