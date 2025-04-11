package io.github.baka4n.misty.command;

import com.github.artbits.quickio.api.Collection;
import io.github.baka4n.misty.Database;
import io.github.baka4n.misty.Databases;
import io.github.baka4n.misty.io.Economy;
import io.github.baka4n.misty.io.Info;
import io.github.baka4n.misty.io.Level;
import io.github.baka4n.misty.utils.Utils;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;

import java.math.BigDecimal;

public class InfoCommand extends Utils {
    public static void onCommand(Group group, User user) {
        Databases database = Database.databases.get(group.getId());
        Databases.Collections all = database.all;
        if (!checkRegistry(user, all.economy)) {
            group.sendMessage(NOT_JOIN_MESSAGE);
            return;
        }


        Economy one = all.economy.findOne(e -> e.uid == user.getId());
        Level levelOne = all.level.findOne(e -> e.uid == user.getId());
        Info infoOne = all.info.findOne(e -> e.uid == user.getId());
        String message = """
                        %s,你的练气境界:%s， 炼体境界:%s, 状态: %s, %s
                        你的灵根属性: 金:%s, 木:%s, 水:%s, 火:%s, 土:%s,
                        你的五脏锻炼进度: 心: %s%%, 肝: %s%%, 脾: %s%%, 肺: %s%%, 肾:%s%%,
                        你的六腑锻炼进度: 小肠: %s%%, 胆: %s%%, 胃: %s%%, 大肠: %s%%, 膀胱:%s%%, 三焦:%s%%,
                        你拥有:%s飘渺币, %s下品灵石, %s中品灵石,%s上品灵石,%s极品灵石
                """.formatted(
                        user.getNick(),
                levelOne.levelStringQi(), levelOne.levelStringBody(),
                infoOne.isDeath(all.info) ? "死去": infoOne.deathCooldown != null ? "To Be Or Not To Be" : "活着",
                infoOne.deathCooldown != null ? "死亡时间：" + infoOne.deathCooldown : "",
                levelOne.gold.equals(BigDecimal.TEN) ? "先天满灵根" : levelOne.gold,
                levelOne.wood.equals(BigDecimal.TEN) ? "先天满灵根" : levelOne.wood,
                levelOne.water.equals(BigDecimal.TEN) ? "先天满灵根" : levelOne.water,
                levelOne.fire.equals(BigDecimal.TEN) ? "先天满灵根" : levelOne.fire,
                levelOne.earth.equals(BigDecimal.TEN) ? "先天满灵根" : levelOne.earth,
                levelOne.heart, levelOne.liver, levelOne.spleen, levelOne.lungs, levelOne.kidneys,
                levelOne.smallIntestine, levelOne.gall, levelOne.gastric, levelOne.largeIntestine, levelOne.bladder, levelOne.trifocals,
                one.mistyCoin, one.lowerSpiritStone, one.midSpiritStone, one.topSpiritStone, one.bestSpiritStone
        );
        group.sendMessage(message);
    }
}
