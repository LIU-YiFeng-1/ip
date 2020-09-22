package duke.exceptions;

public class NoEventTimeException extends DukeExceptions {
    public NoEventTimeException () {
        super();
    }

    @Override
    public void printErrorMessage(String command) {
        System.out.println ("\u2639 " + "OOPS!!! The time description of an event cannot be empty.");
    }
}


