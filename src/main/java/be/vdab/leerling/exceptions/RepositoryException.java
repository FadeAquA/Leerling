package be.vdab.leerling.exceptions;

public class RepositoryException extends RuntimeException {

    public RepositoryException(Exception cause) {
        super(cause);
    }
}
