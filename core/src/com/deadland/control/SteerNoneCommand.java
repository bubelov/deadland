package com.deadland.control;

import com.deadland.base.control.ActionCommand;
import com.deadland.model.vehicle.MovableObject;

/**
 * Created by linv3r on 24.09.14.
 */
public class SteerNoneCommand implements ActionCommand {

    private MovableObject obj;

    public SteerNoneCommand(MovableObject obj) {
        this.obj = obj;
    }

    @Override
    public void execute() {
        obj.steerNone();
    }

    @Override
    public void undo() {

    }
}
