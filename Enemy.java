import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.desktop.SystemEventListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Set;
import java.util.List;

public class Enemy {
    public boolean isDead = false;
    public int health = 50;
    public int positionX; // location to be spawned in on the map
    public int positionY;

    Image image; // the wall image

    Image image2; // the enemy image

    public int graphicsX; // location to be drawn on the screen
    public int graphicsY;

    public String name;

    public Enemy(int x, int y, String name){
        positionX = x;
        positionY = y;
        this.name = name;
        try {
            BufferedImage myPicture = ImageIO.read(new File("img/wall.jpeg"));
            ImageIcon imageIcon = new ImageIcon(myPicture);
            image = imageIcon.getImage(); // transform it}

            BufferedImage myPicture2 = ImageIO.read(new File("img/"+name+ ".png"));
            ImageIcon imageIcon2 = new ImageIcon(myPicture2);
            image2 = imageIcon2.getImage(); // transform it
        } catch (Exception ignored){}
    }

    public void draw(Graphics g, int currentX, int currentY, String fileName){
        try {
            graphicsX = currentX;
            graphicsY = currentY;

            g.drawImage(image,currentX,currentY,50,50,null );
            g.drawImage(image2,currentX,currentY,50,50,null );

        }catch (Exception ignored){}
    }

    public boolean checkHit(Projectile projectile){
        // might have to change the Position Y and Position X
        if (projectile.graphicsX == this.graphicsX && projectile.graphicsY == this.graphicsY){
            health -= projectile.damage;
            projectile.alive = false;
            if (health < 0){
                isDead = true;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o ){
        if (o == null){
            return false;
        }
        if (o instanceof Enemy){
            Enemy temp = (Enemy) o;
            if (temp.positionX == positionX && temp.positionY == positionY ){
                return true;
            }
        }
        return false;
    }
}
