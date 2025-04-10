package io.github.baka4n.misty.command;

import com.github.artbits.quickio.api.Collection;
import io.github.baka4n.misty.Database;
import io.github.baka4n.misty.io.Economy;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;

public class InfoCommand {
    public static void run(Group group, User user) {
        Database database = Database.groupDatabase.get(group.getId());
        Collection<Economy> collection = database.collection(Economy.class);
    }
}
