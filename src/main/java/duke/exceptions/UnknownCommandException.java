package duke.exceptions;

public class UnknownCommandException extends DukeExceptions {
    public UnknownCommandException() {
        super();
    }

    @Override
    public void printErrorMessage(String command) {
        System.out.println("\u2639 " + "OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}