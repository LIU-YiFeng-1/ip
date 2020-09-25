package duke;

import duke.messages.Messages;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.io.*;
import java.util.Scanner;

import static duke.Duke.RECORD_PATH;

public class Storage {
    private static final int TASK_STATUS_STARTING_INDEX = 4;
    private static final int TASK_STATUS_ENDING_INDEX = 5;
    private static final int TASK_DESCRIPTION_STARTING_INDEX = 8;
    public static final String STATUS_DONE = "\u2713";

    public static void loadData(TaskList taskList) {
        String taskDescription;
        String taskDate;
        int taskStatus; // 1 is done, 0 is not done
        int taskDescriptionEndIndex;
        int taskDateStartIndex;
        int taskDateEndIndex;

        File pastRecord = new File(RECORD_PATH);
        try {
            Scanner scanner = new Scanner(pastRecord);
            while (scanner.hasNextLine()) {
                String content = scanner.nextLine();
                taskStatus = Integer.parseInt(content.substring(TASK_STATUS_STARTING_INDEX, TASK_STATUS_ENDING_INDEX));

                if (content.startsWith("T")) {
                    taskDescription = content.substring(TASK_DESCRIPTION_STARTING_INDEX, content.length());
                    taskList.addTask(new ToDo(taskDescription));
                    if (taskStatus == 1) { //if pastTaskStatus is 1, mark it done
                        taskList.markDone(taskList.getSize());
                    }
                } else if (content.startsWith("E")) { //pastRecord has a event task and add the event to the task list

                    taskDescriptionEndIndex = getTaskDescriptionEndIndex(content);
                    taskDateStartIndex = taskDescriptionEndIndex + 1;
                    taskDateEndIndex = content.length() - 1;

                    taskDescription = content.substring(TASK_DESCRIPTION_STARTING_INDEX, taskDescriptionEndIndex);
                    taskDate = content.substring(taskDateStartIndex, taskDateEndIndex);
                    taskList.addTask(new Event(taskDescription, taskDate));

                    taskStatus = Integer.parseInt(content.substring(TASK_STATUS_STARTING_INDEX, TASK_STATUS_ENDING_INDEX));
                    if (taskStatus == 1) { //if pastTaskStatus is 1, mark it done
                        taskList.markDone(taskList.getSize()-1);
                    }
                } else if (content.startsWith("D")) { //pastRecord has a deadline task and add the deadline to the task list
                    taskDescriptionEndIndex = getTaskDescriptionEndIndex(content);
                    taskDateStartIndex = taskDescriptionEndIndex + 1;
                    taskDateEndIndex = content.length() - 1;

                    taskDescription = content.substring(TASK_DESCRIPTION_STARTING_INDEX, taskDescriptionEndIndex);
                    taskDate = content.substring(taskDateStartIndex, taskDateEndIndex);
                    taskList.addTask(new Deadline(taskDescription, taskDate));

                    taskStatus = Integer.parseInt(content.substring(TASK_STATUS_STARTING_INDEX, TASK_STATUS_ENDING_INDEX));
                    if (taskStatus == 1) { //if pastTaskStatus is 1, mark it done
                        taskList.markDone(taskList.getSize()-1);
                    }
                }
                content = "";
            }
        } catch (FileNotFoundException e) {
            System.out.println("There is no past record! You are a 1st time user");
            Messages.printLine();
        }
    }

    public static void saveData(TaskList taskList) throws IOException {

        File file = new File(RECORD_PATH);
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        if (taskList.getSize() == 0) {
            bufferedWriter.close();
            System.out.println("file close without saving as there is nothing to save!");
        } else {
            for (int i = 0; i < taskList.getSize(); i++) {
                if (taskList.getTask(i).getStatusIcon().equals(STATUS_DONE)) {
                    bufferedWriter.write(taskList.getTask(i).saveData() + "\n");
                } else {
                    bufferedWriter.write(taskList.getTask(i).saveData() + "\n");
                }
            }
            System.out.println("file saved");
            Messages.printLine();
        }
        bufferedWriter.close();
    }

    private static int getTaskDescriptionEndIndex(String content) {
        int taskDescriptionEndIndex;
        taskDescriptionEndIndex = content.indexOf("(");
        return taskDescriptionEndIndex;
    }
}
