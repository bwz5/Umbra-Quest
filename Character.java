import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class Character {
    int x ; // x position of the character
    int y ; // y position of the character

    int health;

    AbilityBar abilityBar = new AbilityBar();
    int mana;

    boolean collectedKey = false;
    int maxMana = 5;
    String direction = "down";

    Image manaImage;

    Image healthImage;

    Image playerImage;

    Inventory inventory;

    int phase = 1;

    public Character(){
        x = 250;
        y = 250;
        health = 5;
        mana = 5;

        inventory = new Inventory();

        try {
            String filePath = "img/heart0.png";
            BufferedImage  myPicture= ImageIO.read(new File(filePath));
            ImageIcon imageIcon = new ImageIcon(myPicture);
            healthImage = imageIcon.getImage(); // transform it

            filePath = "img/mana0.png";
            myPicture = ImageIO.read(new File(filePath));
            imageIcon = new ImageIcon(myPicture);
            manaImage = imageIcon.getImage(); // transform it

        } catch (Exception ignored){}

        abilityBar.addAbility("img/fireballright.png","fireball","X");
    }

    public void addMana(){
        if (mana < maxMana){
            mana += 1;
        }
    }

    public void takeDamage(GameComponent game){
        health -= 1;
        try {
            SimpleAudioPlayer temp = new SimpleAudioPlayer();
            temp.playSound(temp.damageTaken);
        } catch (Exception ignored) {}
        if (health <= 0){
            game.isActive = false;
            game.gameOver = true;
        }
    }

    public void checkNear(List<Enemy> enemies, GameComponent game){
        for (Enemy i : enemies){
            if (i.graphicsX - 50 == this.x || i.graphicsX + 50 == this.x || i.graphicsX == this.x){
                if (i.graphicsY - 50 == this.y || i.graphicsY + 50 == this.y || i.graphicsY == this.y){
                    takeDamage(game);
                }
            }
        }
    }

    public void paintCharacter(Graphics g){
        try { // paint the character img
            String filePath = "img/knight" + this.direction + this.phase + ".png";
            BufferedImage myPicture = ImageIO.read(new File(filePath));
            ImageIcon imageIcon = new ImageIcon(myPicture);
            playerImage = imageIcon.getImage(); // transform it
            g.drawImage(playerImage,x,y,55,55,null );
        } catch (Exception ignored){}

        //paint the health bars
        int currentX = 15;
        int currentY = 435;
        for (int i = 0; i < health; i++){
            g.drawImage(healthImage, currentX, currentY, 35, 35, null);
            currentX += 35;
        }
        currentX = 30;
        currentY = 415;
        for (int i = 0; i < mana; i++){
            g.drawImage(manaImage, currentX, currentY, 25, 25, null);
            currentX += 25;
        }
        abilityBar.paintBar(g);
    }

}
