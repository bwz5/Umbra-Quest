
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
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameComponent extends JPanel implements MouseListener, KeyListener, Runnable {
    boolean isActive;

    int levelCount = 1;

    boolean showingInventory = false;

    boolean gameOver = false;

    TimerTask timerTask;

    int toggleMovementCount = 0;

    Character player = new Character();

    int worldX = 20 ; // top left starting location X  25
    int worldY = 22; // top left starting location Y  27

    Lighting lighting = new Lighting(this,500); // for the lighting effect of the game

    List<Entity> entities = new ArrayList<>();  // the npcs

    List<Enemy> enemies = new LinkedList<>(); // the enemies

    Set<Interactable> interactables = new HashSet<>(); // the interactables
    public List<List<String>> records; // the map's loaded information

    List<Projectile> projectilesList = new LinkedList<>();

    Thread gameThread;

    String gameMap = "maps/level1.csv";

    public GameComponent() {

        this.setFocusable(true);
        this.requestFocus();

        this.add(player.inventory);
        gameThread = new Thread(this);
        this.setLayout(new BorderLayout());

        // This component reacts to mouse events.
        this.addMouseListener(this);
        this.addKeyListener(this);

        isActive = false;
        // Set a recommended size for the game board (prevents it from
        // disappearing when frame is packed).
        setPreferredSize(new Dimension(500, 500));

        readWorldMap();
        gameThread.start();
    }

    public void startGame() {
        isActive = true;
        repaint();
    }
    public void stopGame() {
        System.out.println("thank you for playing my game");
    }

    @Override
    public void run(){
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        try {
            gameThread.sleep(50);
        } catch (Exception ignored) {}
        super.paintComponent(g);
        paintFunctions(g);
        if (showingInventory){
            player.inventory.paintComponent(g);
        }
        repaint();
    }

    public void paintFunctions(Graphics g){
        if (!isActive) {
            try { // makes the start screen background image
                worldX = 20;
                worldY = 22;
                BufferedImage myPicture;
                if (gameOver){
                    myPicture = ImageIO.read(new File("img/gameover.png"));

                } else {
                    myPicture = ImageIO.read(new File("img/UMBRAQuest.png"));
                }
                ImageIcon imageIcon = new ImageIcon(myPicture);
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                imageIcon = new ImageIcon(newimg);
                imageIcon.paintIcon(this,g,0,0);
            } catch (Exception ignored){}
            // paints the character
        } else {
            tileDrawer.drawTiles(g,worldX,worldY,records,entities, interactables, enemies, this);
            lighting.draw(g);
            player.paintCharacter(g);

        };
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        for (Entity i : entities){
            if (x <= i.drawnX + 50 && x >= i.drawnX && y <= i.drawnY + 50 && y >= i.drawnY ){
                try {
                    SimpleAudioPlayer temp = new SimpleAudioPlayer();
                    temp.playSound(temp.clickSound);
                } catch (Exception ignored){}
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
        player.checkNear(enemies,this);

        int tempCode = e.getKeyCode();

        toggleMovementCount += 1;

        int playerWorldX = worldX + 5;
        int playerWorldY = worldY + 5;

        if (player.phase == 1){
            player.phase =2;
        } else {
            player.phase = 1;
        }
        if (tempCode == KeyEvent.VK_UP){
            player.direction = "up";
            if ( !records.get(playerWorldX-1).get(playerWorldY).equals("0") && !records.get(playerWorldX-1).get(playerWorldY).equals("M") && !records.get(playerWorldX-1).get(playerWorldY).equals("X") && !records.get(playerWorldX-1).get(playerWorldY).equals("E")){
                worldX -= 1;
            }

        }else if (tempCode == KeyEvent.VK_DOWN){
            player.direction = "down";
            if ( !records.get(playerWorldX+1).get(playerWorldY).equals("0") && !records.get(playerWorldX+1).get(playerWorldY).equals("M") && !records.get(playerWorldX+1).get(playerWorldY).equals("X") && !records.get(playerWorldX+1).get(playerWorldY).equals("E")){
                worldX += 1;
            }

        } else if (tempCode == KeyEvent.VK_LEFT){
            if ( !records.get(playerWorldX).get(playerWorldY-1).equals("0") && !records.get(playerWorldX).get(playerWorldY-1).equals("M") && !records.get(playerWorldX).get(playerWorldY-1).equals("X") && !records.get(playerWorldX).get(playerWorldY-1).equals("E")){
                worldY -=1;
            }
            player.direction = "left";

        } else if (tempCode == KeyEvent.VK_RIGHT){
            player.direction = "right";
            if ( !records.get(playerWorldX).get(playerWorldY+1).equals("0") && !records.get(playerWorldX).get(playerWorldY+1).equals("M") && !records.get(playerWorldX).get(playerWorldY+1).equals("X") && !records.get(playerWorldX).get(playerWorldY+1).equals("E")){
                worldY +=1;
            }
        } else if (tempCode == KeyEvent.VK_X){
            if (player.mana > 0) {
                try {
                    SimpleAudioPlayer temp = new SimpleAudioPlayer();
                    temp.playFireBallSound(temp.fireballFilePath);
                } catch (Exception ignored){}
                for (Projectile k : projectilesList){
                    if (!k.alive){
                        projectilesList.clear();
                    }
                }
                if (projectilesList.isEmpty()){
                    Projectile temp = new Projectile(player.direction,worldX, worldY);
                    projectilesList.add(temp);
                    player.mana -= 1;
                }
            }
        } else if (tempCode == KeyEvent.VK_I){
            if (!showingInventory){
            player.inventory.printText();
            showingInventory = true;

            } else {
                showingInventory = false;
            }
        }

        if (records.get(worldX+5).get(worldY+5).equals("?")){
            records.get(worldX+5).set(worldY+5, "1");
            try {
                SimpleAudioPlayer temp = new SimpleAudioPlayer();
                temp.playSound(temp.keyCollected);
            } catch (Exception ignored){}
            player.inventory.addItem("Key", "A magical key that opens a door.");
            player.collectedKey = true;
        }
        if (!this.interactables.isEmpty()){
            try {
            for (Interactable i : interactables){
                if (i.collided(worldX + 5,worldY + 5) && player.collectedKey){
                    worldX = 20;
                    worldY = 22;
                    levelCount += 1;
                    gameMap = "maps/level" + this.levelCount + ".csv";
                    readWorldMap();

                        interactables.remove(i);
                    //player.collectedKey = false;
                    player.collectedKey = false;
                } else {
                    if (i.collided(worldX + 5, worldY + 5) && !player.collectedKey){
                        JOptionPane.showMessageDialog(this,"You need the key to enter.");
                    }
                }
            }
            } catch (Exception ignored){}

        }

        if (toggleMovementCount % 4 == 0){
            player.addMana();
        }

        //repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void readWorldMap(){
        entities.clear();
        try {
            records = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(this.gameMap));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (Exception e) {}
    }
}
