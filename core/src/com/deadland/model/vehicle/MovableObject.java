package com.deadland.model.vehicle;

/**
 * Created by linv3r on 16.09.14.
 */
public interface MovableObject {
    void goForward();

    void goBackward();

    void stop();

    void steerRight();

    void steerLeft();

    void steerNone();
}
