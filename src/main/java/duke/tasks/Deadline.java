package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class represents a deadline task which consists of
 * a task description, task date and a task status.
 */
public class Deadline extends Task {
    protected String date;
    protected LocalDate formattedDate;

    /**
     * constructor for Deadline class.
     * Creates an Deadline task with the given task description and given task date,
     * and sets the task status to default: false / not done.
     *
     * @param description Description of the deadline being created.
     * @param date Date of the deadline.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        this.formattedDate = LocalDate.parse(date);
    }

    /**
     * Returns the description of the deadline only.
     *
     * @return deadline description.
     */
    @Override
    public String getTaskDescription() {
        return description + " (";
    }

    /**
     * Returns a formatted date in a different format from the input.
     * Format of return is MMM DD YYYY.
     *
     * @return deadline date.
     */
    @Override
    public String getFormattedDate() {
        String newDate;
        newDate = formattedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return newDate + ")";
    }

    /**
     * Returns the description and date of the deadline.
     *
     * @return deadline description.
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
        return description + formattedDate;
    }

    /**
     * Returns the respective task type.
     *
     * @return D Default task type for deadline.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Saves the task in a pre-determined format when writing to a file.
     *
     * @return the required format when writing to a file
     */
    @Override
    public String saveData() {
        return "D | " + (isDone? "1" : "0") + " | " + description + "(" + date + ")";
    }
}
