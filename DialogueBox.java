
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
public class DialogueBox extends JOptionPane{
    Image newimg;

    String text;
    public DialogueBox(){
        this.setSize(new Dimension(250,250));
    }

    public void setImage(String fileName) throws IOException {
        BufferedImage myPicture = ImageIO.read(new File(fileName));
        ImageIcon imageIcon = new ImageIcon(myPicture);
        Image image = imageIcon.getImage(); // transform it
        newimg = image.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
    }

    public void drawImage(Graphics g, JComponent frame){
        ImageIcon icon = new ImageIcon(newimg);
        JOptionPane.showMessageDialog(frame,
                "",
                "",
                JOptionPane.INFORMATION_MESSAGE,
                icon);
    }

    public void openTrade(JComponent game, Merchant m){
        JOptionPane.showOptionDialog(game,"Buy an item from my shop!","Trade",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,m.inventory,1);
    }
    public void setText(String text){
        this.text = text;
    }
}
