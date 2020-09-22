package duke.messages;

import duke.tasks.Task;
import java.util.ArrayList;

public class Messages {
    public static void printLine() {
        System.out.println("----------------------------------"
                + "-------------------------------------------");
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
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
    }

    public static void printList(ArrayList<String> list, Task[] tasks) {
        if (list.size() == 0) {
            System.out.println("The list is empty");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1
                    + ".["
                    + tasks[i].getType()
                    + "]"
                    + "["
                    + tasks[i].getStatusIcon()
                    + "] "
                    + list.get(i));
        }
        printLine();
    }
}
