package se.lexicon.__springBoot_workshop_e_commerce.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException() {
        super("Customer is not found in the system.");
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
