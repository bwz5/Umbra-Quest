import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Character {
    int x ; // x position of the character
    int y ; // y position of the character

    int health;
    int mana;

    int maxMana = 5;
    String direction = "down";

    int phase = 1;

    public Character(){
        x = 250;
        y = 250;
        health = 3;
        mana = 5;

    }

    public void addMana(){
        if (mana < maxMana){
            mana += 1;
        }
    }

    public void paintCharacter(Graphics g){
        try { // paint the character img
            String filePath = "img/knight" + this.direction + this.phase + ".png";
            BufferedImage myPicture = ImageIO.read(new File(filePath));
            ImageIcon imageIcon = new ImageIcon(myPicture);
            Image image = imageIcon.getImage(); // transform it

            g.drawImage(image,x,y,55,55,null );

        } catch (Exception ignored){}

        //paint the health bars
        int currentX = 15;
        int currentY = 435;
        for (int i = 0; i < health; i++){
            try { // paint the character img
                String filePath = "img/heart0.png";
                BufferedImage myPicture = ImageIO.read(new File(filePath));
                ImageIcon imageIcon = new ImageIcon(myPicture);
                Image image = imageIcon.getImage(); // transform it
                g.drawImage(image, currentX, currentY, 55, 55, null);
            } catch (Exception ignored){}
            currentX += 50;
        }
        currentX = 30;
        currentY = 415;
        for (int i = 0; i < mana; i++){
            try { // paint the character img
                String filePath = "img/mana0.png";
                BufferedImage myPicture = ImageIO.read(new File(filePath));
                ImageIcon imageIcon = new ImageIcon(myPicture);
                Image image = imageIcon.getImage(); // transform it
                g.drawImage(image, currentX, currentY, 25, 25, null);
            } catch (Exception ignored){}
            currentX += 25;
        }

    }

}
