package duke.tasks;

public class Event extends Task {
    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getDescription() {
        return description + "(" + date + ")";
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String saveData() {
        return "E | " + (isDone? "1" : "0") + " | " + description + "(" + date + ")";
    }
}
