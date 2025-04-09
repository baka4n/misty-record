package io.github.baka4n.misty;

import com.google.auto.service.AutoService;
import io.github.baka4n.misty.command.BaseCommand;
import io.github.baka4n.misty.command.StartXiuXianCommand;
import io.github.baka4n.misty.io.Economy;
import kotlin.Lazy;
import kotlin.LazyKt;

import net.mamoe.mirai.console.permission.*;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.LinkedList;

@AutoService(JavaPlugin.class)
public class MistyRecordPlugin extends JavaPlugin {
    public static final MistyRecordPlugin INSTANCE = new MistyRecordPlugin();

    public static final Lazy<Permission> mistyRecordGroupPermission =
            LazyKt.lazy(() -> {
               try {
                   return PermissionService.getInstance().register(
                           INSTANCE.permissionId("misty-record-use-group"),
                           "修仙大陆群组权限",
                           INSTANCE.getParentPermission()
                   );
               } catch (PermissionRegistryConflictException e) {
                   throw new RuntimeException(e);
               }
            });

    public static boolean hasMistyUse(Group user) {
        PermitteeId pid = new AbstractPermitteeId.ExactGroup(user.getId());
        return PermissionService.hasPermission(pid, mistyRecordGroupPermission.getValue());
    }

    public static final LinkedList<BaseCommand> COMMANDS = new LinkedList<>();

    public static void registerCommand(BaseCommand command) {
        COMMANDS.add(command);
    }

    private MistyRecordPlugin() {
        super(new JvmPluginDescriptionBuilder("io.github.baka4n.misty-record", "1.0.0").info("EG").build());
    }

    @Override
    public void onEnable() {
        getLogger().info("Misty record plugin enabled");
        EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this);
        eventChannel.subscribeAlways(GroupMessageEvent.class, g -> {

            MessageChain message = g.getMessage();
            Group group = g.getGroup();
            Member user = g.getSender();
            long id = group.getId();
            if (!Database.groupDatabase.containsKey(id))
                Database.groupDatabase.put(id, new Database("misty/economy", group));
            String messageString = message.contentToString();
            switch (messageString.trim()) {
                case "开始修仙", "开始飘渺" -> StartXiuXianCommand.run(group, user);
            }
            if (messageString.startsWith("你好亮亮")) {
                getLogger().info("成功");
                group.sendMessage(messageString);
            }
            getLogger().info("debug" + messageString);
        });

//        eventChannel.subscribeAlways(FriendMessageEvent.class, g -> {
//            getLogger().info(g.getMessage().contentToString());
//        });

        mistyRecordGroupPermission.getValue();//registry permission
    }

    @Override
    public void onDisable() {
        Database.groupDatabase.forEach((aLong, database) -> database.close());
        getLogger().info("Misty record plugin disabled");
    }
}
