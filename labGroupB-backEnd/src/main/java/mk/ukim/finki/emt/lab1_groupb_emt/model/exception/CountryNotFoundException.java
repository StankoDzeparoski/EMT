package mk.ukim.finki.emt.lab1_groupb_emt.model.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(Long id) {
        super("A country with id %d does not exist.".formatted(id));
    }
    }
