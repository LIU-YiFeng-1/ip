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
     * The task date is also converted to LocalDate format.
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
     * Format of return is MMM D YYYY.
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
     * Enables finding of the key word in the format of MMM D YYYY.
     *
     * @return formatted date for deadline.
     */
    public String getFormattedDateForFind() {
        return formattedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
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
     * Returns the description of the deadline.
     * Based on the user input, the switch statement converts the month object into string for easy finding.
     *
     * @return deadline description and deadline date.
     */
    @Override
    public String getDescriptionForFind() {
        String monthInString;
        String dayInString;
        String yearInString;

        dayInString = Integer.toString(formattedDate.getDayOfMonth());
        yearInString = Integer.toString(formattedDate.getYear());
        switch (formattedDate.getMonthValue()) {
        case 1:
            monthInString = "jan";
            break;
        case 2:
            monthInString = "feb";
            break;
        case 3:
            monthInString = "mar";
            break;
        case 4:
            monthInString = "apr";
            break;
        case 5:
            monthInString = "may";
            break;
        case 6:
            monthInString = "jun";
            break;
        case 7:
            monthInString = "jul";
            break;
        case 8:
            monthInString = "aug";
            break;
        case 9:
            monthInString = "sep";
            break;
        case 10:
            monthInString = "oct";
            break;
        case 11:
            monthInString = "nov";
            break;
        default:
            monthInString = "dec";
            break;
        }

        return description + monthInString + dayInString + yearInString;
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
