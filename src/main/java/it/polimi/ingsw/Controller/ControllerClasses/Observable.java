package it.polimi.ingsw.Controller.ControllerClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable Class defines functionality and methods needed for a Class to be observed by a number of Observers.
 * <p><b>Attributes:</b></p>
 * <ul>
 *     <li>List&lt;Observer&lt;T&gt;&gt; "observers": contains all observers of an Observable object</li>
 * </ul>
 * @param <T> Generic Object used for the notify(T message) method.
 */
public class Observable<T> {
    private final List<Observer<T>> observers = new ArrayList<>();

    /**
     * Adds an Observer object to the list of all observers.
     * @param observer Object (which implements Observer) to be added to the list of observers.
     */
    public void addObserver(Observer<T> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    /**
     * Removes an Observer object from the list of all observers.
     * @param observer Object (which implements Observer) to be removed from the list of observers.
     */
    public void removeObserver(Observer<T> observer) {
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    /**
     * Notifies all Observers. Calls the "update(T message)" method of each Observer inside the List of Observers.
     * @param message Generic object used by both Observable Class and Observer Interface to pass an object parameter used by the overridden "notify()" and "update()" methods.
     */
    public void notify(T message) {
        synchronized (observers) {
            for(Observer<T> observer : observers) {
                observer.update(message);
            }
        }
    }

    /**
     * Removes all Observers of an Observable object.
     */
    public void clearObservers() {
        this.observers.clear();
    }

    /**
     * Checks if an Observable object contains a given Observer.
     * @param observer Observer to be searched.
     * @return true if the List of Observers contains the Observer.
     */
    public boolean containsObserver(Observer<T> observer) {
        return observers.contains(observer);
    }
}
