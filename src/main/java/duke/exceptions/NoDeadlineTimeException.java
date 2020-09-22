package duke.exceptions;

public class NoDeadlineTimeException extends DukeExceptions {
    public NoDeadlineTimeException() {
        super();
    }

    @Override
    public void printErrorMessage(String command) {
        System.out.println ("\u2639 " + "OOPS!!! The time description of a deadline cannot be empty.");
    }
}
