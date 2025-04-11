package io.github.baka4n.misty.command;

import io.github.baka4n.misty.Database;
import io.github.baka4n.misty.Databases;
import io.github.baka4n.misty.io.Economy;
import io.github.baka4n.misty.io.Info;
import io.github.baka4n.misty.io.Level;
import io.github.baka4n.misty.io.json.BaseConfig;
import io.github.baka4n.misty.utils.Utils;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;

import java.time.LocalDateTime;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/11 20:00:50}
 */
public class RestartCommand extends Utils {
    public static void onCommand(Group group, User user) {
        Databases database = Database.databases.get(group.getId());
        Databases.Collections all = database.all;
        if (!checkRegistry(user, all.economy)) {
            group.sendMessage(NOT_JOIN_MESSAGE);
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        Info one = all.info.findOne(i -> i.uid == user.getId());
        if (deathCancel(group, user, one, now)) return;
        if (one.restartCooldown != null && one.restartCooldown.plusSeconds(BaseConfig.BASE_CONFIG.RESTART_COOLDOWN.get()).isAfter(now)) {
            group.sendMessage("%s, 你正在🦌，你还不能这么做！".formatted(user.getNick()));
            return;
        }
        group.sendMessage("“啊啊啊，我命由我不由天。”——%s。你的状态些许有了改变。".formatted(
                user.getNick()
        ));
        all.economy.delete(e -> e.uid == user.getId());
        all.level.delete(l -> l.uid == user.getId());
        all.info.delete(i -> i.uid == user.getId());
        all.economy.save(Economy.DEFAULT.copy().uid(user.getId()));
        all.level.save(Level.DEFAULT().uid(user.getId()));
        all.info.save(Info.DEFAULT().uid(user.getId()).restartCooldown(now));
    }


}
