package com.deadland.control;

import com.deadland.base.control.ActionCommand;
import com.deadland.model.vehicle.MovableObject;

/**
 * Created by linv3r on 22.09.14.
 */
public class GoBackwardCommand implements ActionCommand {

    private MovableObject obj;

    public GoBackwardCommand(MovableObject obj) {
        this.obj = obj;
    }

    @Override
    public void execute() {
        obj.goBackward();
    }

    @Override
    public void undo() {
        obj.stop();
    }
}
