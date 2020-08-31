public class Deadline extends Task{
    protected String date;
    protected String description;

    public Deadline(String description, String date){
        this.description = description;
        this.date = date;
    }
    @Override
    public String getDescription(){
        return this.description + "(" + this.date + ")";
    }
    @Override
    public String getType(){
        return "D";
    }
}
