import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Projectile {
    int spawnX; 
    int spawnY;

    int damage = 10;

    Image newimg;
    
    String direction;

    public Projectile( String d){
        this.spawnX = 250;
        this.spawnY = 250;
        this.direction = d;
    }

    public void shootProjectile(Graphics g){
        try {
            String filePath = "img/fireball" + direction + ".png";
            BufferedImage myPicture = ImageIO.read(new File(filePath));
            ImageIcon imageIcon = new ImageIcon(myPicture);
            Image image = imageIcon.getImage(); // transform it
            newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        } catch (Exception ignored){}

        if (direction.equals("up")){
            while (spawnY >= 0){
                    g.drawImage(newimg,spawnX,spawnY,null);
                    spawnY -= 50;
                }
            }
        else if (direction.equals("down")){
            while (spawnY <= 500){
                g.drawImage(newimg,spawnX,spawnY,null);
                spawnY += 50;
            }

        } else if (direction.equals("left")){
            while (spawnX >= 0){
                g.drawImage(newimg,spawnX,spawnY,null);
                spawnX -= 50;
            }

        } else if (direction.equals("right")){
            while (spawnX <= 500){
                g.drawImage(newimg,spawnX,spawnY,null);
                spawnX += 50;
            }

        }
    }
}
