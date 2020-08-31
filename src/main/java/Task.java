public class Task {
    protected String description;
    protected boolean isDone;

    public  Task(){
    }
    public Task(String description){ //setting up the constructor
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon(){
        return (isDone ? "\u2713" : "\u2718"); //return tick or cross symbols
    }
    public String getType(){
        return "T";
    }
    public boolean makeAsDone(){
        return isDone = true;
    }
    public String getDescription(){
        return this.description;
    }
}