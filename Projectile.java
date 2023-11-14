import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.TimerTask;

public class Projectile {
    int spawnX; 
    int spawnY;

    int damage = 10;

    Image newimg;
    
    String direction;

    double speed = 1;

    int graphicsX;
    int graphicsY;


    boolean alive;

    public Projectile( String d, int x , int y){
        this.direction = d;
        spawnX = x;
        spawnY = y;
        alive = true;

        graphicsX = 250;
        graphicsY = 250;

        try {
            String filePath = "img/fireball" + direction + ".png";
            BufferedImage myPicture = ImageIO.read(new File(filePath));
            ImageIcon imageIcon = new ImageIcon(myPicture);
            Image image = imageIcon.getImage(); // transform it
            newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        } catch (Exception ignored){}
    }

    public void update(){
        if (!alive){
            return;
        }
        if (graphicsX < 0 || graphicsX > 500 || graphicsY < 0 || graphicsY > 500){
            alive = false;
        }

        if (direction.equals("up")){
            graphicsY -= speed;
        } else if (direction.equals("down")){
            graphicsY += speed;
        } else if (direction.equals("left")){
            graphicsX -= speed;
        } else {
            graphicsX += speed;
        }
    }

    public void drawProjectile(Graphics g, GameComponent game){
        if (!alive){
            return;
        }
        try {
            g.drawImage(newimg, graphicsX, graphicsY, 50,50,null);
        } catch (Exception ignored){}
    }
}
