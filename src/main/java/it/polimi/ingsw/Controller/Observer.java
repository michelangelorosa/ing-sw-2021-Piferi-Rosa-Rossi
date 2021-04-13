package it.polimi.ingsw.Controller;

public interface Observer<T> {
    public void update(T message);
}
