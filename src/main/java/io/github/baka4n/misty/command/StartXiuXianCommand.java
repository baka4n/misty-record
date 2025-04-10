package io.github.baka4n.misty.command;

import com.github.artbits.quickio.api.Collection;
import io.github.baka4n.misty.Database;
import io.github.baka4n.misty.MistyRecordPlugin;
import io.github.baka4n.misty.io.Economy;
import net.mamoe.mirai.console.command.Command;
import net.mamoe.mirai.console.command.CommandOwner;
import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JCompositeCommand;
import net.mamoe.mirai.console.command.java.JSimpleCommand;
import net.mamoe.mirai.console.permission.Permission;
import net.mamoe.mirai.console.permission.PermissionService;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class StartXiuXianCommand extends JSimpleCommand {


    public StartXiuXianCommand() {
        super(MistyRecordPlugin.INSTANCE, "开始飘渺", "开始修仙", "开始修真");
        setDescription("修仙的开始，一切的开始。");
        setPermission(MistyRecordPlugin.mistyRecordGroupPermission.getValue());
        setPrefixOptional(true);

    }

    @Handler
    public void onCommand(CommandSender sender) {

        if (!(sender.getSubject() instanceof Group group)) return;
        User user = sender.getUser();
        if (user == null) return;
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
