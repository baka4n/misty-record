package io.github.baka4n.misty.command;

import io.github.baka4n.misty.Database;
import io.github.baka4n.misty.Databases;
import io.github.baka4n.misty.io.Economy;
import io.github.baka4n.misty.io.Info;
import io.github.baka4n.misty.io.Level;
import io.github.baka4n.misty.utils.Utils;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;


public class StartXiuXianCommand extends Utils {
    public static void onCommand(Group group, User user) {
        if (group == null || user == null) return;
        Databases database = Database.databases.get(group.getId());
        Databases.Collections all = database.all;
        if (checkRegistry(user, all.economy)) {
            group.sendMessage("%s 你已经是一个修士了！".formatted(user.getNick()));
            return;
        }
        Economy economy = Economy.DEFAULT.copy().uid(user.getId());
        group.sendMessage("%s 欢迎来到修仙世界.\n你的初始金额为%s飘渺币。".formatted(
                user.getNick(),
                economy.mistyCoin
        ));
        all.economy.save(economy);
        all.level.save(Level.DEFAULT().uid(user.getId()));
        all.info.save(Info.DEFAULT().uid(user.getId()));
    }



}
