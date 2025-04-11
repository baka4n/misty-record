package io.github.baka4n.misty.command;

import io.github.baka4n.misty.Database;
import io.github.baka4n.misty.Databases;
import io.github.baka4n.misty.io.Info;
import io.github.baka4n.misty.utils.Utils;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;

import java.time.LocalDateTime;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/11 22:34:04}
 */
public class SuicidedCommand extends Utils {
    public static void onCommand(Group group, User user) {
        Databases databases = Database.databases.get(group.getId());
        Databases.Collections all = databases.all;
        if (!checkRegistry(user, all.economy)) {
            group.sendMessage(NOT_JOIN_MESSAGE);
            return;
        }
        Info one = all.info.findOne(i -> i.uid == user.getId());
        if (!one.isDeath(all.info)) {
            group.sendMessage("护照盖满漂泊的印章，灵魂终要回到出生的地方。");
            all.info.save(one.death(true).deathCooldown(LocalDateTime.now()));
        }
    }
}
