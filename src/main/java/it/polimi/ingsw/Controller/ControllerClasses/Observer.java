package it.polimi.ingsw.Controller.ControllerClasses;

public interface Observer<T> {
    public void update(T message);
}
