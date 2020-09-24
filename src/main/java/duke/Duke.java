package duke;

import duke.messages.Messages;
import duke.tasks.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final int MAX_CAPACITY = 100;
    public static final int INDEX_PAST_RECORD_STATUS = 4;
    public static final String RECORD_PATH = "iPoutput.txt";//file path from root file


    public static void main(String[] args) throws IOException {
        Messages.printWelcomeMessage();
        decodingUserCommands();
    }

    /**
     * Reads user input and adds it to the task list accordingly
     * Based on the input, new task of different types (e.g todo, event, deadline)
     * will be created accordingly
     */
    private static void decodingUserCommands() throws IOException {
        String userInput;
        TaskList taskList = new TaskList();
    //using Parser.parser to make sense of user input and perform the task accordingly
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while (Parser.getRunStatus()) {
            Parser.parser(userInput, taskList);
            userInput = in.nextLine();
        }
    }
}


