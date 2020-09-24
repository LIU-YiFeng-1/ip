package duke.messages;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

public class Messages {

    public static final String SAD_FACE_EMOJI = "\u2639 ";

    public static void printLine() {
        System.out.println("----------------------------------"
                + "-------------------------------------------");
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
        printLine();
    }

    public static void printGreeting() {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.print(greeting);
        printLine();
    }

    public static void printDoneLoadingMessage() {
        System.out.println("Done loading past record onto your task list!\n"
                + "Updating task status based on past record\n"
                + "updating...\n"
                + ".\n"
                + ".\n"
                + "Done updating! Please type \"List\" to check!");
        printLine();
    }

    public static void printImportingMessage() {
        System.out.println("Importing data from previous record"
                + System.lineSeparator()
                + "." + System.lineSeparator()
                + "." + System.lineSeparator()
                + "." + System.lineSeparator()
                + "." + System.lineSeparator()
                + "please wait :)");
        printLine();
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printGreeting();
        printAvailableCommands();
    }

    public static void printAvailableCommands() {
        System.out.println("Available Commands:\n"
                + "1. list\n"
                + "2. done (e.g done 1)\n"
                + "3. delete (e.g delete 1)\n"
                + "4. todo (e.g todo homework)\n"
                + "5. event (e.g event meeting /monday 2pm)\n"
                + "6. deadline (e.g deadline project /monday 2359)\n"
                + "7. bye\n"
                + "p.s all other inputs will be ignored!\n"
                + "Please enter your command:");
        printLine();
    }

    public static void printDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n"
                + "  ["
                + task.getType()
                + "]"
                + "["
                + task.getStatusIcon()
                + "] " + task.getDescription());
        printLine();
    }

    public static void printAllTasks(TaskList taskList) {
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println(i + 1 + ".[" + taskList.getTask(i).getType() + "]"
                    + "[" + taskList.getTask(i).getStatusIcon() + "] "
                    + taskList.getTask(i).getDescription());
        }
        printLine();
    }

    public static void printEmptyTodoError() {
        System.out.println(SAD_FACE_EMOJI + "OOPS!!! The description of a todo cannot be empty.");
        Messages.printLine();
    }

    public static void printEmptyEventError() {
        System.out.println(SAD_FACE_EMOJI + "OOPS!!! The description of an event cannot be empty.");
        Messages.printLine();
    }

    public static void printEmptyDeadlineError() {
        System.out.println(SAD_FACE_EMOJI + "OOPS!!! The description of a deadline cannot be empty.");
        Messages.printLine();
    }

    public static void printEmptyDeleteError() {
        System.out.println(SAD_FACE_EMOJI  + "OOPS!!! The task number to be deleted cannot be empty.");
        Messages.printLine();
    }
    public static void printEmptyDoneError() {
        System.out.println(SAD_FACE_EMOJI  + "OOPS!!! The task number to be done cannot be empty.");
        Messages.printLine();
    }


    public static void printTaskAddedMessage(TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [" + taskList.getTask(taskList.getSize()-1).getType() + "]"
                + "[" + taskList.getTask(taskList.getSize()-1).getStatusIcon() + "] "
                + taskList.getTask(taskList.getSize()-1).getDescription());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
        Messages.printLine();
    }

    public static void printTaskDeletedMessage(TaskList taskList, int taskNumberToDelete) {
        System.out.println("Got it. I've removed this task:");
        System.out.println("  [" + taskList.getTask(taskNumberToDelete - 1).getType()

                + "]" + "[" + taskList.getTask(taskNumberToDelete - 1).getStatusIcon() + "] " + taskList.getTask(taskNumberToDelete - 1).getDescription());
        taskList.deleteTask(taskNumberToDelete - 1);
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list. and deletion happened");
        Messages.printLine();
    }

    public static void printIndexOutOfBoundMessage() {
        System.out.println("The task number is out of bound! Please type \"list\"");
        Messages.printLine();
    }

    public static void printInvalidInput() {
        System.out.println(SAD_FACE_EMOJI + "OOPS!!! I'm sorry, but I don't know what that means :-(");
        printLine();
    }

    public static void printEmptyListMessage() {
        System.out.println("The list is empty");
        printLine();
    }

    public static void printTaskAlreadyDoneMessage() {
        System.out.println("Chill man, this task is completed!");
        Messages.printLine();
    }

    public static void printCommandFormatError() {
        System.out.println(SAD_FACE_EMOJI + "OOPS!!! Wrong input format. Please type help to see command examples");
        printLine();
    }
}
