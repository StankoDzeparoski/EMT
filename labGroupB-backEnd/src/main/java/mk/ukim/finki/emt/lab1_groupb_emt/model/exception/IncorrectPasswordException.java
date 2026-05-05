package mk.ukim.finki.emt.lab1_groupb_emt.model.exception;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super("The password is incorrect.");
    }
}
