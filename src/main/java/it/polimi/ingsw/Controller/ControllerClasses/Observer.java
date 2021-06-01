package it.polimi.ingsw.Controller.ControllerClasses;

/**
 * Observer Interface contains a method to be called by an Observable when a new event occurs.
 * @param <T> Generic object used by the "update(T message)' method, has to be an instance of the same Class
 *           used by its Observable.
 */
public interface Observer<T> {
    /**
     * Called by an Observable when a new even occurs, executes a different method depending on the Class
     * that Overrides it.
     * @param message Generic object used when executing the method.
     */
    void update(T message);
}
