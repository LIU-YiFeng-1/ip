package duke;

import duke.messages.Messages;
import duke.tasks.Parser;
import duke.tasks.TaskList;



import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static final String RECORD_PATH = "Duke_output.txt";//file path from root file

    public static void main(String[] args) throws IOException {
        TaskList taskList = new TaskList();

        Messages.printWelcomeMessage();
        Storage.loadData(taskList);
        decodingUserCommands(taskList);
    }

    /**
     * Reads user input and adds it to the task list accordingly
     * Based on the input, new task of different types (e.g todo, event, deadline)
     * will be created accordingly
     */
    private static void decodingUserCommands(TaskList taskList) throws IOException {
        String userInput;
    //using Parser.parser to make sense of user input and perform the task accordingly
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while (Parser.getRunStatus()) {
            Parser.parser(userInput, taskList);
            userInput = in.nextLine();
        }
    }
}


