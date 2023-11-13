import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Entity {

    int entityX;
    int entityY;

    int drawnX;  // pixel locations
    int drawnY;

    String name;

    String dialogueFile;

    String dialogueText;

    DialogueBox dialogueBox;

    public Entity(int x, int y, String name){
        entityX = x;
        entityY = y;
        this.name = name;
    }

    public void showDialogue(Graphics g, JComponent frame){
        dialogueBox = new DialogueBox();
        try {
            dialogueBox.setImage(dialogueFile);
        } catch (Exception ignored){}
        dialogueBox.drawImage(g, frame);
    }
    public void draw(Graphics g, int currentX, int currentY, String fileName){
        try {
            drawnX = currentX;
            drawnY = currentY;

            BufferedImage myPicture = ImageIO.read(new File("img/wall.jpeg"));
            ImageIcon imageIcon = new ImageIcon(myPicture);
            Image image = imageIcon.getImage(); // transform it
            g.drawImage(image,currentX,currentY,50,50,null );

            BufferedImage myPicture2 = ImageIO.read(new File("img/"+fileName+ ".png"));
            ImageIcon imageIcon2 = new ImageIcon(myPicture2);
            Image image2 = imageIcon2.getImage(); // transform it
            g.drawImage(image2,currentX,currentY,50,50,null );
        }catch (Exception ignored){}
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
