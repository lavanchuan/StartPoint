package view;

import controller.render.RenderFormat;
import setting.Setting;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class UserInterface {

    GamePanel gp;

    ArrayList<String> msgs;
    ArrayList<Integer> times;
    int timeShowOneMsgSecond = 5;

    public UserInterface(GamePanel gp) {

        this.gp = gp;
        msgs = new ArrayList<String>();
        times = new ArrayList<Integer>();
    }

    public void addMessae(String message) {
        this.msgs.add(message);
        this.times.add(timeShowOneMsgSecond * Setting.FPS);
    }

    public boolean onMessage = true;

    public void renderMessage(Graphics g) {
        int width = (int) (this.gp.getWidth() * 0.3);
        int height = (int) (this.gp.getHeight() * 0.5);
        int yMessage = (int) (this.gp.getHeight() * 0.5);
        int margin = (int) (this.gp.getWidth() * 0.01);
        int x = margin;
        int y = yMessage;
        int space = 20;
        g.setColor(Color.CYAN);
        g.setFont(new Font("Times New Roman", Font.PLAIN, space));
        for(int i = 0; i < this.msgs.size(); i++){
            g.drawString(msgs.get(i), x, y + i * space);
        }
    }
    
    /*** CHARACTER DIE ***/
    public boolean onAlertDie = false;
    public String causeDie = "";
    public String directive = "Press enter key to go to menu screen";
    void renderAlertDie(Graphics g){
        int x, y;
        int space = 50;
        x = this.gp.getWidth()/2;
        y = this.gp.getHeight()/2;
        
        // cause of die
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Times New Roman", Font.BOLD, (int)(space * 1.5)));
        RenderFormat.renderCenter(Setting.getAlertDie(""), x, y, g);
        
        g.setFont(new Font("Times New Roman", Font.BOLD, (int)(space * 2)));
        RenderFormat.renderCenter(causeDie, x, y + space * 2, g);
        
        // directive
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("Times New Roman", Font.BOLD, space));
        y = this.gp.getHeight() - 2 * space;
        RenderFormat.renderCenter(directive, x, y, g);
    }

    public void render(Graphics g) {
        if (onMessage) {
            renderMessage(g);
        }
        
        if(onAlertDie){
            renderAlertDie(g);
        }
    }

    public void updateTimeMessage() {
        for (int i = 0; i < times.size(); i++) {
            this.times.set(i, this.times.get(i) - 1);
        }
    }

    public void updateMessage() {
        for (int i = 0; i < times.size(); i++) {
            if(this.times.get(i) <= 0){
                this.msgs.remove(i);
                this.times.remove(i);
            }
        }
    }

    public void update() {
        updateMessage();
        updateTimeMessage();
    }
}
