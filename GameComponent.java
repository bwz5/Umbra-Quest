
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameComponent extends JPanel implements MouseListener, KeyListener {
    boolean isActive;

    int toggleMovementCount = 0;

    Character player = new Character();

    int worldX = 20 ; // top left starting location X
    int worldY = 22; // top left starting location Y

    Lighting lighting = new Lighting(this,500); // for the lighting effect of the game

    Set<Entity> entities = new HashSet<>(); // the npcs

    List<Enemy> enemies = new LinkedList<>(); // the enemies

    Set<Interactable> interactables = new HashSet<>(); // the interactables
    List<List<String>> records; // the map's loaded information

    public GameComponent() {

        this.setFocusable(true);
        this.requestFocus();

        this.setLayout(new BorderLayout());

        // This component reacts to mouse events.
        this.addMouseListener(this);
        this.addKeyListener(this);

        isActive = false;
        // Set a recommended size for the game board (prevents it from
        // disappearing when frame is packed).
        setPreferredSize(new Dimension(500, 500));

        readWorldMap();



    }

    public void startGame() {
        isActive = true;
        repaint();
    }

    public void stopGame() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isActive) {
            try { // makes the start screen background image
                BufferedImage myPicture = ImageIO.read(new File("img/EVER QUEST.png"));
                ImageIcon imageIcon = new ImageIcon(myPicture);
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                imageIcon = new ImageIcon(newimg);
                imageIcon.paintIcon(this,g,0,0);
            } catch (Exception ignored){}
             // paints the character
        } else {

            tileDrawer.drawTiles(g,worldX,worldY,records,entities, interactables, enemies);
            lighting.draw(g);
            player.paintCharacter(g);

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        for (Entity i : entities){
            if (x <= i.drawnX + 50 && x >= i.drawnX && y <= i.drawnY + 50 && y >= i.drawnY ){
                i.showDialogue(this.getGraphics(),this);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggleMovementCount += 1;

        int playerWorldX = worldX + 5;
        int playerWorldY = worldY + 5;

        if (player.phase == 1){
            player.phase =2;
        } else {
            player.phase = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            player.direction = "up";
            if ( !records.get(playerWorldX-1).get(playerWorldY).equals("0") ){
                worldX -= 1;
            }

        }else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            player.direction = "down";
            if ( !records.get(playerWorldX+1).get(playerWorldY).equals("0")){
                worldX += 1;
            }

        } else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            if ( !records.get(playerWorldX).get(playerWorldY-1).equals("0")){
                worldY -=1;
            }
            player.direction = "left";

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.direction = "right";
            if ( !records.get(playerWorldX).get(playerWorldY+1).equals("0")){
                worldY +=1;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_X){
            if (player.mana > 0) {
                Projectile fireball = new Projectile(player.direction);
                fireball.shootProjectile(getGraphics());
                player.mana -= 1;
                for(Enemy i : enemies){
                    if (i.checkHit(player)){
                        i.takeDamage(enemies);
                    }
                }
            }
        }

        for (Interactable i : interactables){
            i.collisionDetector(worldX+5,worldY +5, this);
        }
        if (toggleMovementCount % 4 == 0){
            player.addMana();
        }


        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void readWorldMap(){
        try {
            records = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("maps/level1.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (Exception e) {}
    }
}
