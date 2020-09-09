package duke;

public class Event extends Task {
    protected String date;

    public Event(String description, String date){
        super(description);
        this.date = date;
    }
    @Override
    public String getDescription(){
        return description + "(" + date + ")";
    }
    @Override
    public String getType(){
        return "E";
    }
}
