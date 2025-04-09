package io.github.baka4n.misty.command;

import io.github.baka4n.misty.Database;
import io.github.baka4n.misty.io.Economy;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;

public class StartXiuXianCommand extends BaseCommand {
    @Override
    protected boolean checkRun(String line, int index, Group group, User user) {
        if (index == 1) {
            return false;
        }
        if (line.equals("开始飘渺") || line.equals("开始修仙")) {
            Database database = Database.groupDatabase.get(group.getId());
            Economy one = database.collection(Economy.class).findOne(e -> e.uid == user.getId());
            if (one == null) {
                Economy economy = database.setOrCreate(Economy.class, e -> e.uid == user.getId(), e -> {
                }, Economy.DEFAULT.copy());
                group.sendMessage("%s 欢迎来到修仙世界.\n你的初始金额为%s飘渺币。".formatted(
                        user.getNick(),
                        economy.mistyCoin
                ));
            }
        } else {
            group.sendMessage("你已经在飘渺界了。");
        }
        return super.checkRun(line, index, group, user);
    }
}
