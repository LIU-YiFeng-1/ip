package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public void markDone(int index) {
        taskList.get(index).makeAsDone();
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }
}
