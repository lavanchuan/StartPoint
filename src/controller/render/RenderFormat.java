package controller.render;

import java.awt.Graphics;

public class RenderFormat {

    public RenderFormat() {
    }

    public static void render(Graphics g, String content, int width, int x, int y) {
        String[] items = content.split(" ");
        String line;
        int counter = 0;
        int i = 0;
        while (i < items.length) {
            line = "";

            do {
                line += items[i++] + " ";

                if (i >= items.length) {
                    break;
                }
            } while (g.getFontMetrics().getStringBounds(line + items[i], g).getWidth() < width);

            g.drawString(line, x, y + counter * g.getFont().getSize());
            counter++;
        }
    }

    public static void renderCenter(String text, int x, int y, Graphics g) {
        g.drawString(text,
                x - (int) g.getFontMetrics().getStringBounds(text, g).getWidth() / 2,
                y);
    }
}
