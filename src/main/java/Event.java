public class Event extends Task{
    protected String date;
    protected String description;

    public Event(String description, String date){
        this.description = description;
        this.date = date;
    }
    @Override
    public String getDescription(){
        return this.description + "(" + this.date + ")";
    }
    @Override
    public String getType(){
        return "E";
    }
}
