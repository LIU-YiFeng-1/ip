package duke;

import duke.messages.Messages;
import duke.tasks.Parser;
import duke.tasks.TaskList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        Messages.printWelcomeMessage();
        Storage.loadData(taskList);
        decodingUserCommands(taskList);
    }

    /**
     * Decodes user input and execute the program accordingly.
     * 1. list - prints all tasks in the task list.
     * 2. done - sets the status of the desired task as done.
     * 3. delete - deletes a desired task based on task index.
     * 4. todo - adds a new ToDo task.
     * 5. event - adds a new Event task.
     * 6. deadline - adds a new Deadline task.
     * 7. find - prints all tasks containing the given find key word.
     * 8. save - saves all tasks available to Duke_output.txt.
     * 9. bye - exits the program and saves all tasks available to Duke_output.txt.
     * Program is always running until user inputs "bye".
     * p.s all other inputs will be ignored!
     *
     * @param taskList Task list which stores different types of tasks.
     */
    private static void decodingUserCommands(TaskList taskList) {
        String userInput;
        Scanner in = new Scanner(System.in);
        do {
            userInput = in.nextLine();
            Parser.parser(userInput, taskList);
        } while (Parser.getRunStatus());
    }
}


