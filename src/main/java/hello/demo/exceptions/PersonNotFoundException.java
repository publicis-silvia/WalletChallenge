package hello.demo.exceptions;

public class PersonNotFoundException  extends RuntimeException {
    public PersonNotFoundException(Long id) {
        super("Could not find person " + id);
    }
}
