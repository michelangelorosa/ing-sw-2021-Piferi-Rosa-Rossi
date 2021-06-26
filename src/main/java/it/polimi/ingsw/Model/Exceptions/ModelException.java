package it.polimi.ingsw.Model.Exceptions;

/**
 * The Model Exception is used for all the model related exceptions
 */
public class ModelException extends Exception {
    public ModelException(String errorMessage) {
        super(errorMessage);
    }
}
