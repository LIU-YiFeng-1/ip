package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final int MAX_CAPACITY = 100;

    public static void main(String[] args) {
        printWelcomeMessage();
        decodingUserCommands();
    }

    public static void printLine(){
        System.out.println("----------------------------------"
                +"-------------------------------------------");
    }
    public static void printBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void printGreeting(){
        String greeting ="Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.print(greeting);
        printLine();
    }

    /**
     * Reads user input and adds it to the task list accordingly
     * Based on the input, new task of different types (e.g todo, event, deadline)
     * will be created accordingly
     */
    private static void decodingUserCommands() {
        String input;
        int listSize = 0;
        ArrayList<String> list = new ArrayList<String>();
        Task[] tasks = new Task[MAX_CAPACITY];
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList(list, tasks);
            } else if (input.startsWith ("done")) {
                setDone(input, list, tasks);
            } else if (input.startsWith ("todo")) {
                listSize = setToDo (input, listSize, list, tasks);
            } else if (input.startsWith ("event")) {
                listSize = setEvent (input, listSize, list, tasks);
            } else if (input.startsWith ("deadline")) {
                listSize = setDeadline (input, listSize, list, tasks);
            } else if (input.startsWith ("delete")) {
                listSize = setDelete (input, listSize, list, tasks);
            } else {
                System.out.println("\u2639 " + "OOPS!!! I'm sorry, but I don't know what that means :-(");
                printLine();
            }
            input = in.nextLine();
        }
        printBye();
    }

    private static int setDelete (String input, int listSize, ArrayList<String> list, Task[] tasks) {
        try {
            if (list.size () == 0) {
                System.out.println ("The list is empty");
                printLine ();
            } else {
                String convertedDeleteInput;
                int taskNumberToDelete;
                convertedDeleteInput = input.replaceAll ("[^0-9]", "");//replace all non-number with space
                taskNumberToDelete = Integer.parseInt (convertedDeleteInput);
                if (taskNumberToDelete > list.size ()) {
                    System.out.println ("The task number is out of bound! Please type \"list\"");
                    printLine ();
                } else {
                    listSize = printDeleteMessage (listSize, list, tasks[taskNumberToDelete - 1], taskNumberToDelete);
                }
            }
        } catch (NumberFormatException n) {
            System.out.println("\u2639 " + "OOPS!!! The task number of delete cannot be empty.");
            printLine ();
        }
        return listSize;
    }

    private static int setDeadline (String input, int listSize, ArrayList<String> list, Task[] tasks) {
        try {
            input = input.replace ("deadline ", "");
            int positionIndex = input.indexOf ("/");
            String date = input.substring (positionIndex + 1, input.length ());
            input = input.substring (0, positionIndex);
            tasks[listSize] = new Deadline (input, date);
            list.add (tasks[listSize].getDescription ());
            listSize = printAddTaskMessage (listSize, list, tasks);
        } catch (StringIndexOutOfBoundsException s) {
            System.out.println("\u2639 " + "OOPS!!! The description of a deadline cannot be empty.");
            printLine ();
        }
        return listSize;
    }

    private static int printDeleteMessage (int listSize, ArrayList<String> list, Task task, int taskNumberToDelete) {
        System.out.println("Got it. I've removed this task:" );
        System.out.println("  ["
                + task.getType()
                + "]"
                + "[" + task.getStatusIcon()
                + "] "
                + list.get(taskNumberToDelete-1));
        list.remove (taskNumberToDelete-1);
        System.out.println("Now you have " + list.size() +" tasks in the list. and deletion happened");
        listSize--;
        printLine();
        return listSize;
    }

    private static int setEvent (String input, int listSize, ArrayList<String> list, Task[] tasks) {
        try {
            input = input.replace ("event ", "");
            int positionIndex = input.indexOf ("/");
            String date = input.substring (positionIndex + 1, input.length ());
            input = input.substring (0, positionIndex);
            tasks[listSize] = new Event (input, date);
            list.add (tasks[listSize].getDescription ());
            listSize = printAddTaskMessage (listSize, list, tasks);
        } catch (StringIndexOutOfBoundsException s) {
            System.out.println ("\u2639 " + "OOPS!!! The description of an event cannot be empty.");
            printLine ();
        }
        return listSize;
    }

    private static int setToDo (String input, int listSize, ArrayList<String> list, Task[] tasks) {
        if (input.equals ("todo") || input.equals ("todo ")) {
            System.out.println("\u2639 " + "OOPS!!! The description of a todo cannot be empty.");
            printLine ();
        } else {
            input = input.replace ("todo ", "");
            list.add (input);
            tasks[listSize] = new ToDo (input);
            listSize = printAddTaskMessage (listSize, list, tasks);
        }
        return listSize;
    }

    private static int printAddTaskMessage (int listSize, ArrayList<String> list, Task[] tasks) {
        System.out.println("Got it. I've added this task:" );
        System.out.println("  ["
                + tasks[listSize].getType()
                + "]"
                + "[" + tasks[listSize].getStatusIcon()
                + "] "
                + list.get(listSize));
        System.out.println("Now you have " + list.size() +" tasks in the list.");
        printLine();
        listSize++;
        return listSize;
    }

    private static void setDone(String input, ArrayList<String> list, Task[] tasks) {
        try {
            if (list.size () == 0) {
                System.out.println ("The list is empty");
                printLine ();
            } else {
                String convertedSetDoneInput;
                int taskNumberToBeDone;
                convertedSetDoneInput = input.replaceAll ("[^0-9]", "");//replace all non-number with space
                taskNumberToBeDone = Integer.parseInt (convertedSetDoneInput);
                if (taskNumberToBeDone > list.size ()) {
                    System.out.println ("The task number is out of bound! Please type \"list\"");
                    printLine ();
                } else if (tasks[taskNumberToBeDone - 1].isDone == true) {
                    System.out.println ("Chill man, this task is completed!");
                    printLine ();
                } else {
                    tasks[taskNumberToBeDone - 1].makeAsDone ();
                    printDoneMessage (tasks[taskNumberToBeDone - 1]);
                    printLine ();
                }
            }
        } catch (NumberFormatException n) {
            System.out.println("\u2639 " + "OOPS!!! The task number of done cannot be empty.");
            printLine ();
        }
    }

    private static void printList(ArrayList<String> list, Task[] tasks) {
        if (list.size()==0) {
            System.out.println("The list is empty");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1
                    + ".["
                    + tasks[i].getType()
                    + "]"
                    +"[" + tasks[i].getStatusIcon() + "] "
                    + list.get(i));
        }
        printLine();
    }

    private static void printDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n"
                + "  ["
                + task.getType()
                +  "]"
                + "["
                + task.getStatusIcon()
                + "] "
                + task.getDescription());
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printGreeting();
        System.out.println("Available Commands:\n"
                +"1. list\n"
                +"2. done (e.g done 1)\n"
                +"3. delete (e.g delete 1)\n"
                +"4. todo (e.g todo homework)\n"
                +"5. event (e.g event meeting /monday 2pm)\n"
                +"6. deadline (e.g deadline project /monday 2359)\n"
                +"7. bye\n"
                +"p.s all other inputs will be ignored!\n"
                +"Please enter your command:");
        printLine();
    }

}
