package io.github.baka4n.misty.command;

import com.freewayso.image.combiner.ImageCombiner;
import com.freewayso.image.combiner.enums.OutputFormat;
import com.freewayso.image.combiner.enums.ZoomMode;
import com.github.artbits.quickio.api.Collection;
import io.github.baka4n.misty.Database;
import io.github.baka4n.misty.Databases;
import io.github.baka4n.misty.MistyRecordPlugin;
import io.github.baka4n.misty.io.Economy;
import io.github.baka4n.misty.io.Info;
import io.github.baka4n.misty.io.Level;
import io.github.baka4n.misty.utils.BBImage;
import io.github.baka4n.misty.utils.Utils;
import lombok.SneakyThrows;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.*;
import net.mamoe.mirai.utils.ExternalResource;
import org.jetbrains.annotations.NotNull;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

public class InfoCommand extends Utils {
    @SneakyThrows
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

//        ForwardMessageBuilder level = new ForwardMessageBuilder(group);
//
//        level
//                .add(114514L, "飘渺宗主", new PlainText("你的练气境界: %s".formatted(levelOne.levelStringQi())))
//                .add(114514L, "飘渺宗主", new PlainText("你的炼体境界: %s".formatted(levelOne.levelStringBody())))
//        ;
//        ForwardMessage levelBuild = level.build();
//        ForwardMessageBuilder state = new ForwardMessageBuilder(group);
//        state.add(114514L, "飘渺宗主", new PlainText(infoOne.isDeath(all.info) ? "死去": infoOne.deathCooldown != null ? "To Be Or Not To Be" : "活着"))
//        ;
//        if (infoOne.deathCooldown != null) {
//            state.add(114514L, "飘渺宗主", new PlainText("死亡时间：" + infoOne.deathCooldown));
//        }
//        ForwardMessage stateBuild = state.build();
//        ForwardMessageBuilder spiritualPower = new ForwardMessageBuilder(group);
//        spiritualPower
//                .add(114514L, "飘渺宗主", new PlainText("金: %s".formatted(levelOne.goldSpiritualPower)))
//                .add(114514L, "飘渺宗主", new PlainText("木: %s".formatted(levelOne.woodSpiritualPower)))
//                .add(114514L, "飘渺宗主", new PlainText("水: %s".formatted(levelOne.waterSpiritualPower)))
//                .add(114514L, "飘渺宗主", new PlainText("火: %s".formatted(levelOne.fireSpiritualPower)))
//                .add(114514L, "飘渺宗主", new PlainText("土: %s".formatted(levelOne.dirtSpiritualPower)))
//                .add(114514L, "飘渺宗主", new PlainText("风: %s".formatted(levelOne.windSpiritualPower)))
//                .add(114514L, "飘渺宗主", new PlainText("雷: %s".formatted(levelOne.thunderSpiritualPower)))
//                .add(114514L, "飘渺宗主", new PlainText("山: %s".formatted(levelOne.mountainsSpiritualPower)))
//                .add(114514L, "飘渺宗主", new PlainText("泽: %s".formatted(levelOne.riversSpiritualPower)))
//                .add(114514L, "飘渺宗主", new PlainText("天: %s".formatted(levelOne.heavenSpiritualPower)))
//                .add(114514L, "飘渺宗主", new PlainText("阴: %s".formatted(levelOne.yinSpiritualPower)))
//                .add(114514L, "飘渺宗主", new PlainText("阳: %s".formatted(levelOne.yangSpiritualPower)))
//        ;
//        ForwardMessage spiritualPowerBuild = spiritualPower.build();
//        ForwardMessageBuilder five = new ForwardMessageBuilder(group);
//
//        five
//                .add(114514L, "飘渺宗主", new PlainText("心: %s%%".formatted(levelOne.heart)))
//                .add(114514L, "飘渺宗主", new PlainText("肝: %s%%".formatted(levelOne.liver)))
//                .add(114514L, "飘渺宗主", new PlainText("脾: %s%%".formatted(levelOne.spleen)))
//                .add(114514L, "飘渺宗主", new PlainText("肺: %s%%".formatted(levelOne.lungs)))
//                .add(114514L, "飘渺宗主", new PlainText("肾: %s%%".formatted(levelOne.kidneys)))
//        ;
//        ForwardMessage fiveBuild = five.build();
//        ForwardMessageBuilder six = new ForwardMessageBuilder(group);
//        six
//                .add(114514L, "飘渺宗主", new PlainText("小肠: %s%%".formatted(levelOne.smallIntestine)))
//                .add(114514L, "飘渺宗主", new PlainText("胆: %s%%".formatted(levelOne.gall)))
//                .add(114514L, "飘渺宗主", new PlainText("胃: %s%%".formatted(levelOne.gastric)))
//                .add(114514L, "飘渺宗主", new PlainText("大肠: %s%%".formatted(levelOne.largeIntestine)))
//                .add(114514L, "飘渺宗主", new PlainText("膀胱: %s%%".formatted(levelOne.bladder)))
//                .add(114514L, "飘渺宗主", new PlainText("三焦: %s%%".formatted(levelOne.trifocals)))
//        ;
//        ForwardMessage sixBuild = six.build();
//        ForwardMessageBuilder money = new ForwardMessageBuilder(group);
//        money
//                .add(114514L, "飘渺宗主", new PlainText("飘渺币: %s枚".formatted(one.mistyCoin)))
//                .add(114514L, "飘渺宗主", new PlainText("下品灵石: %s颗".formatted(one.lowerSpiritStone)))
//                .add(114514L, "飘渺宗主", new PlainText("中品灵石: %s颗".formatted(one.midSpiritStone)))
//                .add(114514L, "飘渺宗主", new PlainText("上品灵石: %s颗".formatted(one.topSpiritStone)))
//                .add(114514L, "飘渺宗主", new PlainText("极品灵石: %s颗".formatted(one.bestSpiritStone)))
//        ;
//        ForwardMessage moneyBuild = money.build();
//        ForwardMessageBuilder pack = new ForwardMessageBuilder(group);
//        pack.add(114514L, "飘渺宗主", new PlainText("a"));
//        ForwardMessage packBuild = pack.build();
        URL resource = InfoCommand.class.getResource("/bg.jpg");
        if (resource == null) {
            return;
        }
        BufferedImage read = ImageIO.read(resource);
//        int width = read.getWidth() / 2;
//        int height = read.getHeight() / 2;
//        read = new BBImage(width, height, Color.WHITE).graphics().drawImage(read,0, 0, width, height);
//        ;
        ImageCombiner combiner = new ImageCombiner(read, read.getWidth(), read.getHeight(), ZoomMode.Height, OutputFormat.JPG);

        combiner.combine();
        combiner.save("./combiner.png");
        MessageChain chain = MessageUtils.newChain().plus(group.uploadImage(ExternalResource.create(combiner.getCombinedImageStream().readAllBytes())));
        group.sendMessage(chain);
//        group.sendMessage(new PlainText("飘渺信息"));
//        group.sendMessage(new PlainText("修为: "));
//        group.sendMessage(levelBuild);
//        group.sendMessage(new PlainText("状态: "));
//        group.sendMessage(stateBuild);
//        group.sendMessage(new PlainText("灵力: "));
//        group.sendMessage(spiritualPowerBuild);
//        group.sendMessage(new PlainText("五脏锻炼进度: "));
//        group.sendMessage(fiveBuild);
//        group.sendMessage(new PlainText("六腑锻炼进度: "));
//        group.sendMessage(sixBuild);
//        group.sendMessage(new PlainText("经济条件: "));
//        group.sendMessage(moneyBuild);
//        group.sendMessage(new PlainText("背包: "));
//        group.sendMessage(packBuild);
        
//        ForwardMessageBuilder builder = new ForwardMessageBuilder(group);
//        builder
//
//                .add(114514L, "飘渺宗主", new PlainText("你好, %s".formatted(user.getNick())))
//                .add(114514L, "飘渺宗主", new PlainText("修为: "))
//                .add(114514L, "飘渺宗主", levelBuild)
//                .add(114514L, "飘渺宗主", new PlainText("状态: "))
//                .add(114514L, "飘渺宗主", stateBuild)
//                .add(114514L, "飘渺宗主", new PlainText("灵力: "))
//                .add(114514L, "飘渺宗主", spiritualPowerBuild)
//                .add(114514L, "飘渺宗主", new PlainText("五脏锻炼进度: "))
//                .add(114514L, "飘渺宗主", fiveBuild)
//                .add(114514L, "飘渺宗主", new PlainText("六腑锻炼进度: "))
//                .add(114514L, "飘渺宗主", sixBuild)
//                .add(114514L, "飘渺宗主", new PlainText("经济条件: "))
//                .add(114514L, "飘渺宗主", moneyBuild)
//                .add(114514L, "飘渺宗主", new PlainText("背包: "))
//                .add(114514L, "飘渺宗主", packBuild)
//
//        ;
//        ForwardMessage builderBuild = builder.build();
//
//
//        group.sendMessage(builderBuild);


//        String message = """
//                        %s,你的练气境界: %s， 炼体境界: %s, 状态: %s, %s,
//                        你的灵力：金: %s, 木: %s, 水: %s, 火: %s, 土: %s, 风: %s, 雷: %s, 山: %s, 泽: %s, 天: %s, 阴: %s, 阳: %s;
//                        你的灵根属性: 金: %s, 木: %s, 水: %s, 火: %s, 土: %s;
//                        你的五脏锻炼进度: 心: %s%%, 肝: %s%%, 脾: %s%%, 肺: %s%%, 肾:%s%%;
//                        你的六腑锻炼进度: 小肠: %s%%, 胆: %s%%, 胃: %s%%, 大肠: %s%%, 膀胱: %s%%, 三焦: %s%%;
//                        你拥有:%s枚飘渺币, %s颗下品灵石, %s颗中品灵石,%s颗上品灵石,%s颗极品灵石;
//                        背包信息:
//                """.formatted(
//                        user.getNick(),
//                levelOne.levelStringQi(), levelOne.levelStringBody(),
//                infoOne.isDeath(all.info) ? "死去": infoOne.deathCooldown != null ? "To Be Or Not To Be" : "活着",
//                infoOne.deathCooldown != null ? "死亡时间：" + infoOne.deathCooldown : "",
//                levelOne.goldSpiritualPower, levelOne.woodSpiritualPower,
//                levelOne.waterSpiritualPower, levelOne.fireSpiritualPower,
//                levelOne.dirtSpiritualPower, levelOne.windSpiritualPower,
//                levelOne.thunderSpiritualPower, levelOne.mountainsSpiritualPower,
//                levelOne.riversSpiritualPower, levelOne.heavenSpiritualPower,
//                levelOne.yinSpiritualPower, levelOne.yangSpiritualPower,
//                levelOne.gold.equals(BigDecimal.TEN) ? "先天满灵根" : levelOne.gold,
//                levelOne.wood.equals(BigDecimal.TEN) ? "先天满灵根" : levelOne.wood,
//                levelOne.water.equals(BigDecimal.TEN) ? "先天满灵根" : levelOne.water,
//                levelOne.fire.equals(BigDecimal.TEN) ? "先天满灵根" : levelOne.fire,
//                levelOne.dirt.equals(BigDecimal.TEN) ? "先天满灵根" : levelOne.dirt,
//                levelOne.heart, levelOne.liver, levelOne.spleen, levelOne.lungs, levelOne.kidneys,
//                levelOne.smallIntestine, levelOne.gall, levelOne.gastric, levelOne.largeIntestine, levelOne.bladder, levelOne.trifocals,
//                one.mistyCoin, one.lowerSpiritStone, one.midSpiritStone, one.topSpiritStone, one.bestSpiritStone
//        );
//        group.sendMessage(message);
    }
}
