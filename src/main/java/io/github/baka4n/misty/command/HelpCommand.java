package io.github.baka4n.misty.command;

import io.github.baka4n.misty.utils.BBImage;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import net.mamoe.mirai.utils.ExternalResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/11 18:09:46}
 */
public class HelpCommand {
    public static void onCommand(Group group) {
        BBImage bbImage = new BBImage(1280, 1280, Color.GRAY);
        bbImage
                .graphics()
                .setFontStyle(Font.PLAIN)
                .setFontSize(80)
                .setColor(Color.RED)
                .drawCenterWidthString(group.getName() + "告示", 100)
                .setFontSize(50)
                .setColor(Color.BLACK)
                .drawCenterWidthString("#开始飘渺/开始修仙-------------------开启修仙门", 200)
                .drawCenterWidthString("#飘渺面板/个人信息-------------------查看个人信息", 300)
        ;

        ByteArrayOutputStream bros = new ByteArrayOutputStream();
        try {
            ImageIO.write(bbImage, "png", bros);
        } catch (IOException ignored) {}
        MessageChain singleMessages = MessageUtils.newChain().plus(group.uploadImage(ExternalResource.create(bros.toByteArray())));
        group.sendMessage(singleMessages);
    }
}
