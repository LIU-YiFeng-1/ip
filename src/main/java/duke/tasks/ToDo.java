package duke.tasks;

/**
 * ToDo class represents a todo task which consists of
 * a task description and a task status.
 */
public class ToDo extends Task {

    /**
     * constructor for ToDo class.
     * Creates a ToDo task with the given task description
     * and sets the task status to default: false / not done.
     *
     * @param description Description of the task being created.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Saves the task in a pre-determined format when writing to a file.
     *
     * @return the required format when writing to a file
     */
    @Override
    public String saveData() {
        return "T | " + (isDone? "1" : "0") + " | " + description;
    }

    /**
     * Returns the description of the todo only.
     *
     * @return todo description.
     */
    public String getTaskDescription() {
        return description;
    }

    /**
     * Returns an empty content as todo has no date.
     *
     * @return empty content.
     */
    public String getFormattedDate() {
        return "";
    }

    /**
     * Returns an empty content as todo has no date.
     *
     * @return empty content.
     */
    public String getFormattedDateForFind() {
        return "";
    }
}
