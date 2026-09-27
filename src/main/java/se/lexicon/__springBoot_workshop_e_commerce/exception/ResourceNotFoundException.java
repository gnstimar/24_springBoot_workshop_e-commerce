package se.lexicon.__springBoot_workshop_e_commerce.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
