package com.deadland.base.control;

/**
 * Created by linv3r on 22.09.14.
 */
public interface ActionCommand {
    void execute();

    void undo();
}
