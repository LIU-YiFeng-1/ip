package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final int MAX_CAPACITY = 100;
    public static final int INDEX_PAST_RECORD_STATUS = 4;

    public static void main(String[] args) throws IOException {
        printWelcomeMessage();
        decodingUserCommands();
    }

    private static void printLine() {
        System.out.println("----------------------------------"
                +"-------------------------------------------");
    }

    private static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printGreeting() {
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
    private static void decodingUserCommands() throws IOException {
        String input;
        int listSize = 0;
        ArrayList<String> list = new ArrayList<String>();
        Task tasks[] = new Task[MAX_CAPACITY];

        //importing pastRecord before reading user commands
        printImportingMessage ();
        String pastRecordPath = "D:\\NUS\\Year 2 Sem 1\\CS2113T\\iP\\iPoutput.txt";
        File pastRecord = new File (pastRecordPath);
        try {
            Scanner scanner = new Scanner (pastRecord);
            while (scanner.hasNextLine ()) {
                listSize = importPastRecord (listSize, list, tasks, scanner);
            }
            printDoneLoadingMessage ();
        } catch (FileNotFoundException e) {
                System.out.println("There is no past record! You are a 1st time user");
                printLine ();
        }

        //Reading user commands after importing pastRecord
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList(list, tasks);
            } else if (input.startsWith ("done")) {
                setDone(input, list, tasks);
            } else if (input.startsWith ("todo")) {
                listSize = setTodo (input, listSize, list, tasks);
            } else if (input.startsWith ("event")) {
                listSize = setEvent (input, listSize, list, tasks);
            } else if (input.startsWith ("deadline")) {
                listSize = setDeadline (input, listSize, list, tasks);
            } else {
                System.out.println("\u2639 " + "OOPS!!! I'm sorry, but I don't know what that means :-(");
                printLine();
            }
            input = in.nextLine();
        }
        //when switching off the programme, export the tasks as output text and print bye message
        exportTasksAsOutputText (list, tasks);
        printBye();
    }

    private static int importPastRecord (int listSize, ArrayList<String> list, Task[] tasks, Scanner scanner) {
        String content = scanner.nextLine ();
        int pastTaskStatus = Integer.parseInt (content.substring (INDEX_PAST_RECORD_STATUS, INDEX_PAST_RECORD_STATUS+1));
        if (content.startsWith ("T")) { //pastRecord has a todo task and add the todo to the task list
            String todoDescription = "todo " + content.substring (INDEX_PAST_RECORD_STATUS + 4, content.length ());
            listSize = setTodo (todoDescription, listSize, list, tasks);
            if (pastTaskStatus == 1) { //if pastTaskStatus is 1, mark it done
                tasks[listSize - 1].makeAsDone ();
            }
        } else if (content.startsWith ("E")) { //pastRecord has a event task and add the event to the task list
            String eventDescription = "event " + content.substring (INDEX_PAST_RECORD_STATUS + 4, content.length () - 1);
            eventDescription = eventDescription.replace ("(", "/");
            listSize = setEvent (eventDescription, listSize, list, tasks);
            if (pastTaskStatus == 1) { //if pastTaskStatus is 1, mark it done
                tasks[listSize - 1].makeAsDone ();
            }
        } else if (content.startsWith ("D")){ //pastRecord has a deadline task and add the deadline to the task list
            String deadlineDescription = "event " + content.substring (INDEX_PAST_RECORD_STATUS + 4, content.length () - 1);
            deadlineDescription = deadlineDescription.replace ("(", "/");
            listSize = setEvent (deadlineDescription, listSize, list, tasks);
            if (pastTaskStatus == 1) { //if pastTaskStatus is 1, mark it done
                tasks[listSize - 1].makeAsDone ();
            }
        }
        content = "";
        return listSize;
    }

    private static void printDoneLoadingMessage () {
        System.out.println("Done loading past record onto your task list!\n"
                +"Updating task status based on past record\n"
                +"updating...\n"
                +".\n"
                +".\n"
                +"Done updating! Please type \"List\" to check!");
        printLine ();
    }

    private static void printImportingMessage () {
        System.out.println("Importing data from previous record"
                + System.lineSeparator()
                +"." + System.lineSeparator()
                +"." + System.lineSeparator()
                +"." + System.lineSeparator()
                +"." + System.lineSeparator()
                +"please wait :)");
        printLine ();
    }

    private static void exportTasksAsOutputText (ArrayList<String> list, Task[] tasks) throws IOException {
        //need to change the filePath as the user's filePath
        String filePath = "D:\\NUS\\Year 2 Sem 1\\CS2113T\\iP\\iPoutput.txt";
        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter (file.getAbsoluteFile ());
        BufferedWriter bufferedWriter = new BufferedWriter (fileWriter);
        if (list.size () == 0) {
            bufferedWriter.write ("");
        } else {
            for (int i = 0; i < list.size (); i++) {
                if (tasks[i].getStatusIcon () == "\u2713") {
                    bufferedWriter.write (tasks[i].getType () + " | " + "1" + " | " + list.get (i) + "\n");
                } else {
                    bufferedWriter.write (tasks[i].getType () + " | " + "0" + " | " + list.get (i) + "\n");
                }
            }
        }
        bufferedWriter.close ();
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

    private static int setTodo (String input, int listSize, ArrayList<String> list, Task[] tasks) {
        if (input.equals ("todo")) {
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
                String inputNumber;
                int taskNumber;
                inputNumber = input.replaceAll ("[^0-9]", "");//replace all non-number with space
                taskNumber = Integer.parseInt (inputNumber);
                if (taskNumber > list.size ()) {
                    System.out.println ("The task number is out of bound! Please type \"list\"");
                    printLine ();
                } else if (tasks[taskNumber - 1].isDone == true) {
                    System.out.println ("Chill man, this task is completed!");
                    printLine ();
                } else {
                    tasks[taskNumber - 1].makeAsDone ();
                    printDoneMessage (tasks[taskNumber - 1]);
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
                +"3. todo (e.g todo homework)\n"
                +"4. event (e.g event meeting /monday 2pm)\n"
                +"5. deadline (e.g deadline project /monday 2359)\n"
                +"6. bye\n"
                +"p.s all other inputs will be ignored!\n"
                +"Please enter your command:");
        printLine();
    }

}
