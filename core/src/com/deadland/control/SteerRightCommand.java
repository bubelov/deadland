package com.deadland.control;

import com.deadland.base.control.ActionCommand;
import com.deadland.model.vehicle.MovableObject;

/**
 * Created by linv3r on 22.09.14.
 */
public class SteerRightCommand implements ActionCommand {

    private MovableObject obj;

    public SteerRightCommand(MovableObject obj) {
        this.obj = obj;
    }

    @Override
    public void execute() {
        obj.steerRight();
    }

    @Override
    public void undo() {
        obj.steerNone();
    }
}
