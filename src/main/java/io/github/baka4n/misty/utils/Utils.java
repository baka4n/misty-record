package io.github.baka4n.misty.utils;

import com.github.artbits.quickio.api.Collection;
import io.github.baka4n.misty.io.Economy;
import io.github.baka4n.misty.io.Info;
import io.github.baka4n.misty.io.json.BaseConfig;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;

import java.time.LocalDateTime;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/11 22:30:23}
 */
public class Utils {

    public static final String NOT_JOIN_MESSAGE = "你还没进入到飘渺界，请输入{#开始飘渺} 开始你的飘渺之旅吧";
    public static boolean deathCancel(Group group, User user, Info one, LocalDateTime now) {
        if (one.deathCooldown != null && one.deathCooldown.plusSeconds(BaseConfig.BASE_CONFIG.DEATH_COOLDOWN.get()).isAfter(now)) {
            group.sendMessage("%s 已逝。待归来时。".formatted(user.getNick()));
            return true;
        }
        return false;
    }

    public static boolean checkRegistry(User user, Collection<Economy> collection) {
        for (Economy economy : collection.findAll()) {
            if (economy.uid == user.getId()) {
                return true;
            }
        }
        return false;
    }
}
