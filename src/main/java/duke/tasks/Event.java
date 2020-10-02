package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class represents an event task which consists of
 * a task description, task date and a task status.
 */
public class Event extends Task {
    protected String date;
    protected LocalDate formattedDate;

    /**
     * constructor for Event class.
     * Creates an Event task with the given task description and given task date,
     * and sets the task status to default: false / not done.
     * The task date is also converted to LocalDate format.
     *
     * @param description Description of the event being created.
     * @param date Date of the event.
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
        this.formattedDate = LocalDate.parse(date);
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
     * Returns the description of the event only.
     *
     * @return event description.
     */
    @Override
    public String getTaskDescription() {
        return description + " (";
    }

    /**
     * Returns a formatted date in a different format from the input.
     * Format of return is MMM D YYYY.
     *
     * @return event date.
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
     * @return formatted date for event.
     */
    public String getFormattedDateForFind() {
        return formattedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the description of the event.
     * Based on the user input, the switch statement converts the month object into string for easy finding.
     *
     * @return event description.
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
