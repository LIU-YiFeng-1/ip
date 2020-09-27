package duke.tasks;

import java.time.format.DateTimeFormatter;

/**
 * Task class represents a task which consists of a task description
 * and a task status isDone.
 */
public class Task {

    public static final String STATUS_DONE = "\u2713";
    public static final String STATUS_UNDONE = "\u2718";
    protected String description;
    public boolean isDone;

    /**
     * constructor for Task class.
     * Creates a Task with the given task description
     * and sets the task status to default: false / not done.
     *
     * @param description Description of the task being created.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task status.
     *
     * @return isDone Tick symbol when isDone is true and cross symbol when isDone is false.
     */
    public String getStatusIcon() {
        return (isDone ? STATUS_DONE : STATUS_UNDONE); //return tick or cross symbols
    }

    /**
     * Returns the respective task type.
     * Will be overridden by different task type.
     * Returns T is task is todo type.
     * Returns E if task is event type.
     * Returns D if task is deadline type.
     *
     * @return T Default task type for task.
     */
    public String getType() {
        return "T";
    }

    /**
     * Returns the task status as done.
     *
     * @return isDone Task status is updated to done.
     */
    public boolean makeAsDone() {
        return isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return description Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the description of the task.
     *
     * @return description Task description.
     */
    public String getDescriptionForFind() {
        return description;
    }

    /**
     * Saves the task in a pre-determined format when writing to a file.
     * Will be overridden by the type of tasks.
     *
     * @return null
     */
    public String saveData() {
        return null;
    }

    /**
     * Returns the description of the task only.
     * Overridden in ToDo, Event and Deadline classes.
     *
     * @return task description.
     */
    public String getTaskDescription() {
        return null;
    }

    /**
     * Returns a formatted date in a different format from the input.
     * Format of return is MMM DD YYYY.
     * Overridden in ToDo, Event and Deadline classes.
     *
     * @return formatted date.
     */
    public String getFormattedDate() {
        return null;
    }

    /**
     * Returns the formatted date in a different format from the input.
     * Enable finding of the key word in the format of MMM DD YYYY.
     * Overridden in ToDo, Event and Deadline classes.
     *
     * @return formatted date.
     */
    public String getFormattedDateForFind() {
        return null;
    }
}
