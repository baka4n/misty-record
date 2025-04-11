package io.github.baka4n.misty.command;

import com.github.artbits.quickio.api.Collection;
import io.github.baka4n.misty.Database;
import io.github.baka4n.misty.io.Economy;
import io.github.baka4n.misty.io.Level;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;

public class InfoCommand {
    public static void onCommand(Group group, User user) {
        Database database = Database.groupDatabase.get(group.getId());
        Collection<Economy> collection = database.collection(Economy.class);
        if (!StartXiuXianCommand.checkRegistry(group, user, collection)) {
            group.sendMessage(StartXiuXianCommand.NOT_JOIN_MESSAGE);
            return;
        }
        Database levelDatabase = Database.groupLevelDatabase.get(group.getId());
        Collection<Level> levelCollection = levelDatabase.collection(Level.class);
        Economy one = collection.findOne(e -> e.uid == user.getId());
        Level levelOne = levelCollection.findOne(e -> e.uid == user.getId());
        String message = """
                        %s,你的练气境界:%s， 炼体境界:%s
                        你的五脏锻炼进度: 心: %s%%, 肝: %s%%, 脾: %s%%, 肺: %s%%, 肾:%s%%,
                        你的六腑锻炼进度: 小肠: %s%%, 胆: %s%%, 胃: %s%%, 大肠: %s%%, 膀胱:%s%%, 三焦:%s%%,
                        你拥有:%s飘渺币, %s下品灵石, %s中品灵石,%s上品灵石,%s极品灵石
                """.formatted(
                        user.getNick(),
                levelOne.levelStringQi(), levelOne.levelStringBody(),
                levelOne.heart, levelOne.liver, levelOne.spleen, levelOne.lungs, levelOne.kidneys,
                levelOne.smallIntestine, levelOne.gall, levelOne.gastric, levelOne.largeIntestine, levelOne.bladder, levelOne.trifocals,
                one.mistyCoin, one.lowerSpiritStone, one.midSpiritStone, one.topSpiritStone, one.bestSpiritStone
        );
        group.sendMessage(message);
    }
}
