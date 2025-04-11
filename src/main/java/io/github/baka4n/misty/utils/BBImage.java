package io.github.baka4n.misty.utils;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * @author : baka4n
 * {@code @Date : 2025/04/11 19:07:01}
 */
@SuppressWarnings("UnusedReturnValue")
public class BBImage extends BufferedImage {

    public Graphics2D graphics;

    public BBImage(int width, int height,  Color color) {
        super(width, height, TYPE_INT_ARGB);
        graphics().setColor(color).fillRoundRect(0, 0, width, height, 80, 80);

    }

    public BBImage drawRect(int x, int y, int width, int height) {
        graphics.drawRect(x, y, width, height);
        return this;
    }
    public BBImage drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        graphics.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
        return this;
    }
    public BBImage fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        graphics.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
        return this;
    }


    public BBImage drawString(String str, int x, int y) {
        graphics.drawString(str, x, y);
        return this;
    }

    public BBImage drawCenterWidthString(String str, int y) {
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        Font font = graphics.getFont();
        Rectangle rec = font.getStringBounds(str, frc).getBounds();
        int width = rec.width;
        graphics.drawString(str, (this.getWidth() - width) / 2, y);
        return this;
    }

    public BBImage drawCenterHeightString(String str, int x) {
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        Font font = graphics.getFont();
        Rectangle rec = font.getStringBounds(str, frc).getBounds();
        int height = rec.height;
        graphics.drawString(str, x, (this.getHeight() - height) / 2);
        return this;
    }

    public BBImage drawCenterString(String str) {
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        Font font = graphics.getFont();
        Rectangle rec = font.getStringBounds(str, frc).getBounds();
        int height = rec.height;
        int width = rec.width;
        graphics.drawString(str, (this.getWidth() - width) / 2, (this.getHeight() - height) / 2);
        return this;
    }

    public BBImage setColor(Color color) {
        graphics.setColor(color);
        return this;
    }

    public BBImage setFontSize(int size) {
        Font font = graphics.getFont();
        graphics.setFont(new Font(font.getName(), font.getStyle(), size));
        return this;
    }

    public BBImage setFontStyle(int style) {
        Font font = graphics.getFont();
        graphics.setFont(new Font(font.getName(), style, font.getSize()));
        return this;
    }


    public BBImage graphics() {
        graphics = this.createGraphics();
        return this;
    }

}
