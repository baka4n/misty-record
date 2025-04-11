package io.github.baka4n.misty.command;

import com.github.artbits.quickio.api.Collection;
import io.github.baka4n.misty.Database;
import io.github.baka4n.misty.io.Economy;
import io.github.baka4n.misty.io.Level;
import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;


public class StartXiuXianCommand {
    public static final String NOT_JOIN_MESSAGE = "你还没进入到飘渺界，请输入{#开始飘渺} 开始你的飘渺之旅吧";

    public static void onCommand(Group group, User user) {
        if (group == null || user == null) return;
        Database database = Database.groupDatabase.get(group.getId());
        Collection<Economy> collection = database.collection(Economy.class);
        Database levelDatabase = Database.groupLevelDatabase.get(group.getId());
        Collection<Level> levelCollection = levelDatabase.collection(Level.class);
        if (checkRegistry(group, user, collection)) return;
        Economy economy = Economy.DEFAULT.copy().uid(user.getId());
        Level level = Level.DEFAULT().uid(user.getId());
        group.sendMessage("%s 欢迎来到修仙世界.\n你的初始金额为%s飘渺币。".formatted(
                user.getNick(),
                economy.mistyCoin
        ));
        collection.save(economy);
        levelCollection.save(level);

    }

    public static boolean checkRegistry(Group group, User user, Collection<Economy> collection) {
        for (Economy economy : collection.findAll()) {
            if (economy.uid == user.getId()) {
                group.sendMessage("%s 你已经是一个修士了！".formatted(user.getNick()));
                return true;
            }
        }
        return false;
    }

}
