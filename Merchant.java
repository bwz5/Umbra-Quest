import javax.swing.*;
import java.awt.*;

public class Merchant extends Entity{
    Object[] inventory = {"Helmet", "Boots", "Armor", "Sword"};

    public Merchant(int x, int y, String name) {
        super(x, y, name);
        dialogueFile = "img/OsmoOpeningDialogue.png";
    }

    @Override
    public void showDialogue(Graphics g, JComponent frame){
        dialogueBox = new DialogueBox();
        try {
            dialogueBox.setImage(dialogueFile);
        } catch (Exception ignored){}
        dialogueBox.openTrade(frame,this);
    }
}
