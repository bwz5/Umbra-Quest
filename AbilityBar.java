import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AbilityBar {

    List<Ability> abilityList = new ArrayList<>();

    public class Ability {
        Image image;

        String name;

        String buttonToPress;

        public Ability(String fileName, String _name, String _buttonToPress){
            try {
                BufferedImage myPicture = ImageIO.read(new File(fileName));
                ImageIcon imageIcon = new ImageIcon(myPicture);
                image = imageIcon.getImage();
            } catch (Exception ignored){};
            name = _name;
            buttonToPress = _buttonToPress;
        }
    }

    public AbilityBar(){

    }

    public void addAbility(String fileName, String name, String buttonPressed){
        abilityList.add(new Ability(fileName,name,buttonPressed));
    }

    public void paintBar(Graphics g){
        int currentX = 435;
        int currentY = 435;

        for (Ability a : abilityList){
            g.drawImage(a.image,currentX,currentY,35,35,null);
            g.setColor(Color.white);
            g.drawString(a.buttonToPress,currentX + 5,currentY + 39);
            currentX -= 35;
        }
    }
}
