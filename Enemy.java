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

    public int graphicsX; // location to be drawn on the screen
    public int graphicsY;

    public String name;

    public Enemy(int x, int y, String name){
        positionX = x;
        positionY = y;
        this.name = name;
    }

    public void draw(Graphics g, int currentX, int currentY, String fileName){
        try {
            graphicsX = currentX;
            graphicsY = currentY;

            BufferedImage myPicture = ImageIO.read(new File("img/wall.jpeg"));
            ImageIcon imageIcon = new ImageIcon(myPicture);
            Image image = imageIcon.getImage(); // transform it
            g.drawImage(image,currentX,currentY,50,50,null );
            if (isDead){
                return;
            }
            BufferedImage myPicture2 = ImageIO.read(new File("img/"+fileName+ ".png"));
            ImageIcon imageIcon2 = new ImageIcon(myPicture2);
            Image image2 = imageIcon2.getImage(); // transform it
            g.drawImage(image2,currentX,currentY,50,50,null );
        }catch (Exception ignored){}
    }

    public boolean checkHit(Character player){
        // might have to change the Position Y and Position X
        System.out.println(player.x);
        System.out.println(graphicsX);

        System.out.println(player.y);
        System.out.println(graphicsY);


        if (player.x == this.graphicsX){
            if (player.direction.equals("up") && this.graphicsY < player.y){
                // an up attack landed
                return true;
            } else if (player.direction.equals("down") && this.graphicsY > player.y){
                // a bottom attack landed
                System.out.println("down landed");

                return true;
            }
        } else if (player.y == this.graphicsY){
            if (player.direction.equals("right") && this.graphicsX > player.x){
                // a left attack landed
                System.out.println("right landed");

                return true;
            } else if (player.direction.equals("left") && this.graphicsX < player.x){
                System.out.println("left landed");
                // a right attack landed
                return true;
            }
        }
        return false;
    }

    public synchronized void  takeDamage(List<Enemy> enemies){
        if (this.health == 0){
            isDead = true;
            return;
        }
        this.health -= 10;
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
