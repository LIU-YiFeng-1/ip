package duke.tasks;

import java.util.ArrayList;

/**
 * TaskList class represents a list of tasks.
 */

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * constructor for TaskList class.
     * Creates a taskList consists of an array of Task.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Adds a given type of task to the task list.
     * Types of task available:
     * 1.ToDo
     * 2.Event
     * 3.Deadline
     *
     * @param t Any type of above-mentioned task.
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Removes a specific task from the task list.
     *
     * @param index Index of the task to be removed.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Marks a specific task from the task list as done.
     *
     * @param index Index of the task to be marked as done.
     */
    public void markDone(int index) {
        taskList.get(index).makeAsDone();
    }

    /**
     * Returns the total number of tasks available in the task list.
     *
     * @return number of tasks in the task list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns a specific task from the task list as done.
     *
     * @param index Index of the task to be returned.
     * @return the task at the given index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }
}
