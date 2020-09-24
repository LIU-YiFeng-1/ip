package duke.tasks;

public class Task {
    public static final String STATUS_DONE = "\u2713";
    public static final String STATUS_UNDONE = "\u2718";
    protected String description;
    public boolean isDone;

    public Task(String description) { //setting up the constructor
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? STATUS_DONE : STATUS_UNDONE); //return tick or cross symbols
    }

    public String getType() {
        return "T";
    }

    public boolean makeAsDone() {
        return isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public String saveData() {
        return null;
    }
}
