package duke.tasks;

/**
 * Event class represents an event task which consists of
 * a task description, task date and a task status.
 */
public class Event extends Task {
    protected String date;

    /**
     * constructor for Event class.
     * Creates an Event task with the given task description and given task date,
     * and sets the task status to default: false / not done.
     *
     * @param description Description of the event being created.
     * @param date Date of the event.
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns the description of the event.
     *
     * @return event description.
     */
    @Override
    public String getDescription() {
        return description + " (" + date + ")";
    }

    /**
     * Returns the description of the event.
     *
     * @return event description.
     */
    @Override
    public String getDescriptionForFind() {
        return description + date ;
    }

    /**
     * Returns the respective task type.
     *
     * @return E Default task type for event.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Saves the task in a pre-determined format when writing to a file.
     *
     * @return the required format when writing to a file
     */
    @Override
    public String saveData() {
        return "E | " + (isDone? "1" : "0") + " | " + description + "(" + date + ")";
    }
}
