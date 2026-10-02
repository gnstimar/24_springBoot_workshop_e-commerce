package se.lexicon.__springBoot_workshop_e_commerce.exception;

public class DuplicateEntryException extends RuntimeException {
    public DuplicateEntryException(String message) {
        super(message);
    }
}
