package com.deadland;

import com.deadland.base.control.ActionCommand;

import java.util.Collection;

/**
 * Created by linv3r on 24.09.14.
 */
public class CMHelper {

    public static Collection<ActionCommand> getCommands() {
        return ControlManager.instance.commands;
    }

    public static void addCommand(ActionCommand command) {
        ControlManager.instance.commands.add(command);
    }

    public static void clearCommands() {
        ControlManager.instance.commands.clear();
    }
}
