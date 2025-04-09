package io.github.baka4n.misty.command;

import com.github.artbits.quickio.api.Collection;
import io.github.baka4n.misty.Database;
import io.github.baka4n.misty.io.Economy;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;

public class StartXiuXianCommand {

    public static void run(Group group, User user) {
        Database database = Database.groupDatabase.get(group.getId());
        Collection<Economy> collection = database.collection(Economy.class);
        for (Economy economy : collection.findAll()) {
            if (economy.uid == user.getId()) {
                group.sendMessage("%s 你已经是一个修士了！".formatted(user.getNick()));
                return;
            }
        }
        Economy economy = Economy.DEFAULT.copy().uid(user.getId());
        group.sendMessage("%s 欢迎来到修仙世界.\n你的初始金额为%s飘渺币。".formatted(
                user.getNick(),
                economy.mistyCoin
        ));
        collection.save(economy);
    }
}
