package com.deadland.control;

import com.deadland.base.control.ActionCommand;
import com.deadland.model.vehicle.MovableObject;

/**
 * Created by linv3r on 22.09.14.
 */
public class SteerLeftCommand implements ActionCommand {

    private MovableObject obj;

    public SteerLeftCommand(MovableObject obj) {
        this.obj = obj;
    }

    @Override
    public void execute() {
        obj.steerLeft();
    }

    @Override
    public void undo() {
        obj.steerNone();
    }
}
