package com.deadland.control;

import com.deadland.base.control.ActionCommand;
import com.deadland.model.vehicle.MovableObject;

/**
 * Created by linv3r on 24.09.14.
 */
public class StopCommand implements ActionCommand {

    private MovableObject obj;

    public StopCommand(MovableObject obj) {
        this.obj = obj;
    }

    @Override
    public void execute() {
        obj.stop();
    }

    @Override
    public void undo() {

    }
}
