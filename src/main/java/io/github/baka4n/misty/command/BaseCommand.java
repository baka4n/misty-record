package io.github.baka4n.misty.command;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;

public class BaseCommand {
    public BaseCommand() {}

    public void run(String command, Group group, User user) {
        if (!command.startsWith("$")) return;
        var commandLines = command.trim().split(" ");
        if (commandLines.length == 0) return;
        for (int i = 0; i < commandLines.length; i++) {
            if (!checkRun(commandLines[i].trim(), i, group, user)) {
                break;
            }
        }
    }

    protected boolean checkRun(String line, int index, Group group, User user) {
        return false;
    }
}
