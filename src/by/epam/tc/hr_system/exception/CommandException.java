package by.epam.tc.hr_system.exception;

/**
 * Exception for controller (command) layer
 * 
 * @author Ivan Chernikau
 *
 */
public class CommandException extends Exception {

    private static final long serialVersionUID = 1L;

    public CommandException(String message) {
        super(message);
    }

    public CommandException(Exception e) {
        super(e);
    }

    public CommandException(String message, Exception e) {
        super(message, e);
    }
}
