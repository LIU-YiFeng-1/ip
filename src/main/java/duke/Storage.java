package duke;

import duke.messages.Messages;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage class which deals with data loading and data saving.
 */
public class Storage {

    private static final int STATUS_DONE_INDICATOR = 1;
    private static final int TASK_DESCRIPTION_STARTING_INDEX = 8;
    private static final int TASK_STATUS_ENDING_INDEX = 5;
    private static final int TASK_STATUS_STARTING_INDEX = 4;
    public static final String DEADLINE_TASK_TYPE = "D";
    public static final String EVENT_TASK_TYPE = "E";
    public static final String RECORD_PATH = "Duke_output.txt";
    public static final String RESET_CONTENT_TO_BE_READ = "";
    public static final String TASK_DESCRIPTION_END_INDICATOR = "(";
    public static final String TODO_TASK_TYPE = "T";

    /**
     * Loads data from Duke_output.txt for past record tasks.
     *
     * @param taskList Task list which stores different types of tasks.
     * @throws IndexOutOfBoundsException if the current task index  > taskList.getSize()
     */
    public static void loadData(TaskList taskList) throws IndexOutOfBoundsException {
        File pastRecord = new File(RECORD_PATH);
        try {
            readingFiles(taskList, pastRecord);
        } catch (FileNotFoundException e) {
            Messages.printNoPastRecordMessage();
        }
    }

    /**
     * Reads data from Duke_output.txt for past record tasks.
     *
     * @param taskList   Task list which stores different types of tasks.
     * @param pastRecord File name to be read.
     * @throws FileNotFoundException If file is not present, continue.
     */
    private static void readingFiles(TaskList taskList, File pastRecord) throws FileNotFoundException {
        Scanner scanner = new Scanner(pastRecord);
        while (scanner.hasNextLine()) {
            String content = scanner.nextLine();
            checkingTaskType(taskList, content);
        }
    }

    /**
     * Reads data from Duke_output.txt and creating tasks accordingly.
     *
     * @param taskList   Task list which stores different types of tasks.
     * @param content    Content of next line to be read.
     */
    private static void checkingTaskType(TaskList taskList, String content) {
        int taskStatus;
        int taskDateStartIndex;
        String taskDescription;
        int taskDescriptionEndIndex;
        int taskDateEndIndex;
        String taskDate;
        if (content.startsWith(TODO_TASK_TYPE)) {
            taskDescription = content.substring(TASK_DESCRIPTION_STARTING_INDEX, content.length());
            taskList.addTask(new ToDo(taskDescription));
            taskStatus = Integer.parseInt(content.substring(TASK_STATUS_STARTING_INDEX, TASK_STATUS_ENDING_INDEX));
            if (taskStatus == STATUS_DONE_INDICATOR) {
                taskList.markDone(taskList.getSize() - 1);
            }
        } else if (content.startsWith(EVENT_TASK_TYPE)) {
            taskDescriptionEndIndex = getTaskDescriptionEndIndex(content);
            taskDateStartIndex = taskDescriptionEndIndex + 1;
            taskDateEndIndex = content.length() - 1;

            taskDescription = content.substring(TASK_DESCRIPTION_STARTING_INDEX, taskDescriptionEndIndex);
            taskDate = content.substring(taskDateStartIndex, taskDateEndIndex);
            taskList.addTask(new Event(taskDescription, taskDate));

            taskStatus = Integer.parseInt(content.substring(TASK_STATUS_STARTING_INDEX, TASK_STATUS_ENDING_INDEX));
            if (taskStatus == STATUS_DONE_INDICATOR) {
                taskList.markDone(taskList.getSize() - 1);
            }
        } else if (content.startsWith(DEADLINE_TASK_TYPE)) {
            taskDescriptionEndIndex = getTaskDescriptionEndIndex(content);
            taskDateStartIndex = taskDescriptionEndIndex + 1;
            taskDateEndIndex = content.length() - 1;

            taskDescription = content.substring(TASK_DESCRIPTION_STARTING_INDEX, taskDescriptionEndIndex);
            taskDate = content.substring(taskDateStartIndex, taskDateEndIndex);
            taskList.addTask(new Deadline(taskDescription, taskDate));

            taskStatus = Integer.parseInt(content.substring(TASK_STATUS_STARTING_INDEX, TASK_STATUS_ENDING_INDEX));
            if (taskStatus == STATUS_DONE_INDICATOR) {
                taskList.markDone(taskList.getSize() - 1);
            }
        } else {
            content = RESET_CONTENT_TO_BE_READ;
        }
    }

    /**
     * Saves available tasks in the task list to Duke_output.txt when COMMAND_SAVE is detected.
     *
     * @param taskList Task list which stores different types of tasks.
     * @throws IOException If failed to write the file.
     */
    public static void saveData(TaskList taskList) throws IOException {

        File file = new File(RECORD_PATH);
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        if (taskList.getSize() == 0) {
            bufferedWriter.close();
            Messages.printNoDataToSaveMessage();
        } else {
            for (int i = 0; i < taskList.getSize(); i++) {
                bufferedWriter.write(taskList.getTask(i).saveData() + "\n");
            }
            System.out.println("file saved");
            Messages.printLine();
        }
        bufferedWriter.close();
    }

    /**
     * Returns the index of the ending of the task description.
     *
     * @param content Saved input from Duke_output.txt.
     * @return taskDescriptionEndIndex Index of the ending of the task description.
     */
    private static int getTaskDescriptionEndIndex(String content) {
        int taskDescriptionEndIndex;
        taskDescriptionEndIndex = content.indexOf(TASK_DESCRIPTION_END_INDICATOR);
        return taskDescriptionEndIndex;
    }
}
