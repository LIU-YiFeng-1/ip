package duke.exceptions;

import duke.Duke;

public class EmptyTaskDescriptionException extends DukeExceptions {
    public EmptyTaskDescriptionException() {
        super();
    }

    @Override
    public void printErrorMessage(String command) {
        System.out.println ("\u2639 " + "OOPS!!! The task description cannot be empty.");
    }
}
