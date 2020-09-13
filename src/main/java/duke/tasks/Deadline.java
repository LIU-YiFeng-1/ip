package duke.tasks;

public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date){
        super(description);
        this.date = date;
    }
    @Override
    public String getDescription(){
        return description + "(" + date + ")";
    }
    @Override
    public String getType(){
        return "D";
    }
}
