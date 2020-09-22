package duke.exceptions;

public abstract class DukeExceptions extends Exception {
    public DukeExceptions() {
        super();
    }

    public abstract void printErrorMessage(String command);
}
