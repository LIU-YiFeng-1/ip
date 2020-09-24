package duke.tasks;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String saveData() {
        return "T | " + (isDone? "1" : "0") + " | " + description;
    }
}
