import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Entity {

    int entityX;
    int entityY;

    public int drawnX;  // pixel locations
    public int drawnY;

    String name;

    String dialogueFile;

    Image image; // the wall image

    Image image2; // the entity image

    String dialogueText;

    DialogueBox dialogueBox;

    public Entity(int x, int y, String name){
        entityX = x;
        entityY = y;
        this.name = name;
        try {
            BufferedImage myPicture = ImageIO.read(new File("img/wall.jpeg"));
            ImageIcon imageIcon = new ImageIcon(myPicture);
            image = imageIcon.getImage(); // transform it

            BufferedImage myPicture2 = ImageIO.read(new File("img/" + name + ".png"));
            ImageIcon imageIcon2 = new ImageIcon(myPicture2);
            image2 = imageIcon2.getImage(); // transform it
        } catch (Exception ignored){}
    }

    public void showDialogue(Graphics g, JComponent frame){
        dialogueBox = new DialogueBox();
        try {
            dialogueBox.setImage(dialogueFile);
        } catch (Exception ignored){}
        dialogueBox.drawImage(g, frame);
    }
    public void draw(Graphics g, int currentX, int currentY, String fileName){
            drawnX = currentX;
            drawnY = currentY;

            g.drawImage(image,currentX,currentY,50,50,null );
            g.drawImage(image2,currentX,currentY,50,50,null );
    }

    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (o instanceof Entity){
            Entity temp = (Entity) o;
            if (temp.entityX == entityX && temp.entityY == entityY ){
                return true;
            }
        }
        return false;
    }
}
