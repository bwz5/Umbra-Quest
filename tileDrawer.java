import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Set;

public class tileDrawer {
    public static void drawTiles(Graphics g,int worldX, int worldY, List<List<String>> records, List<Entity> entities, Set<Interactable> interactables, List<Enemy> enemies, GameComponent game){
        int currentY = 0;
        int currentX = 0;
        Image wallImage = null;
        Image fenceImage = null;

        try {
            BufferedImage myPicture = ImageIO.read(new File("img/wall.jpeg"));
            ImageIcon imageIcon = new ImageIcon(myPicture);
            wallImage = imageIcon.getImage(); // transform it

            BufferedImage myPicture2 = ImageIO.read(new File("img/fence0.png"));
            ImageIcon imageIcon2 = new ImageIcon(myPicture2);
            fenceImage = imageIcon2.getImage(); // transform it

        } catch (Exception ignored){}

        for (int i = worldX; i < worldX+ 10; i++){
            for (int j = worldY ; j <  worldY +10; j++){
                String tempString = records.get(i).get(j);
                if (tempString.equals("1")){
                    try {
                        g.drawImage(wallImage,currentX,currentY,50,50,null );

                    } catch (Exception ignored){}
                } else if (tempString.equals("0")){
                    try {
                        g.drawImage(wallImage,currentX,currentY,50,50,null );

                        g.drawImage(fenceImage,currentX,currentY,50,50,null );

                    } catch (Exception ignored){}
                }
                else if (tempString.equals("X")){
                    Wizard temp = new Wizard(i,j,"wizard0");
                    int count = 0;
                    for (Entity k : entities){
                        if (k.equals(temp)){
                            k.drawnX = currentX;
                            k.drawnY = currentY;
                            count += 1;
                        }
                    }
                    if (count == 0) {
                        entities.add(temp);
                    }
                    temp.draw(g,currentX,currentY,"wizard0");

                }
                else if (tempString.equals("M")) {
                    Merchant temp = new Merchant(i, j, "merchant0");
                    int count = 0;
                    for (Entity k : entities) {
                        if (k.equals(temp)) {
                            k.drawnX = currentX;
                            k.drawnY = currentY;
                            count += 1;
                        }
                    }
                    if (count == 0) {
                        entities.add(temp);
                    }

                    temp.draw(g, currentX, currentY, "merchant0");

                }else if (tempString.equals("K")) {
                    Knight temp = new Knight(i, j, "knight0");
                    int count = 0;
                    for (Entity k : entities) {
                        if (k.equals(temp)) {
                            k.drawnX = currentX;
                            k.drawnY = currentY;
                            count += 1;
                        }
                    }
                    if (count == 0) {
                        entities.add(temp);
                    }
                    temp.draw(g, currentX, currentY, "knight0");

                }else if (tempString.equals("E")){
                    Enemy temp = new Enemy(i,j,"grendel0");
                    int count = 0;
                    for (Enemy k : enemies){
                        if (k.equals(temp)){
                            k.graphicsX = currentX;
                            k.graphicsY = currentY;
                            count += 1;
                        }
                    }

                    if (count == 0) {
                        enemies.add(temp);
                    }
                    if (!enemies.isEmpty()) {
                        for (Enemy k : enemies) {
                            if (k.isDead) {
                                enemies.remove(k);
                                records.get(i).set(j, "1");
                            }
                        }
                    }
                    for (Enemy k: enemies){
                        k.draw(g,currentX,currentY,"grendel0");
                    }}
                else if (tempString.equals("T")){
                    try {
                        BufferedImage myPicture2 = ImageIO.read(new File("img/wall.jpeg"));
                        ImageIcon imageIcon2 = new ImageIcon(myPicture2);
                        Image image2 = imageIcon2.getImage(); // transform it
                        g.drawImage(image2,currentX,currentY,50,50,null );


                        BufferedImage myPicture = ImageIO.read(new File("img/trap0.png"));
                        ImageIcon imageIcon = new ImageIcon(myPicture);
                        Image image = imageIcon.getImage(); // transform it
                        g.drawImage(image,currentX,currentY,50,50,null );

                        TrapDoor temp = new TrapDoor(i,j);
                        int count = 0;
                        for (Interactable k : interactables){
                            if (k.equals(temp)){
                                count += 1;
                            }
                        }
                        if (count == 0) {
                            interactables.add(temp);
                        }
                    } catch (Exception ignored){}
                    }
                else if (tempString.equals("?")) {
                    try {
                        BufferedImage myPicture2 = ImageIO.read(new File("img/wall.jpeg"));
                        ImageIcon imageIcon2 = new ImageIcon(myPicture2);
                        Image image2 = imageIcon2.getImage(); // transform it
                        g.drawImage(image2,currentX,currentY,50,50,null );

                        BufferedImage myPicture = ImageIO.read(new File("img/key0.png"));
                        ImageIcon imageIcon = new ImageIcon(myPicture);
                        Image image = imageIcon.getImage(); // transform it
                        g.drawImage(image,currentX,currentY,50,50,null );

                    }catch (Exception ignored){}

                }
                for (Projectile k : game.projectilesList){
                    if (!k.alive){
                        break;
                    }
                    k.update();
                    for (Enemy e : enemies){
                        if (!e.checkHit(k)){
                            k.drawProjectile(g,game);
                        }
                    }
                }

                currentX += 50;
            }
            currentX = 0;
            currentY += 50;
        }
    }
}
