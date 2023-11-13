import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Set;

public class tileDrawer {
    public static void drawTiles(Graphics g,int worldX, int worldY, List<List<String>> records, Set<Entity> entities, Set<Interactable> interactables, List<Enemy> enemies){
        int currentY = 0;
        int currentX = 0;

        for (int i = worldX; i < worldX+ 10; i++){
            for (int j = worldY ; j <  worldY +10; j++){
                if (records.get(i).get(j).equals("1")){
                    try {
                        BufferedImage myPicture = ImageIO.read(new File("img/wall.jpeg"));
                        ImageIcon imageIcon = new ImageIcon(myPicture);
                        Image image = imageIcon.getImage(); // transform it
                        g.drawImage(image,currentX,currentY,50,50,null );

                    } catch (Exception ignored){}
                } else if (records.get(i).get(j).equals("0")){
                    try {
                        BufferedImage myPicture = ImageIO.read(new File("img/wall.jpeg"));
                        ImageIcon imageIcon = new ImageIcon(myPicture);
                        Image image = imageIcon.getImage(); // transform it
                        g.drawImage(image,currentX,currentY,50,50,null );

                        BufferedImage myPicture2 = ImageIO.read(new File("img/fence0.png"));
                        ImageIcon imageIcon2 = new ImageIcon(myPicture2);
                        Image image2 = imageIcon2.getImage(); // transform it
                        g.drawImage(image2,currentX,currentY,50,50,null );

                    } catch (Exception ignored){}
                }
                else if (records.get(i).get(j).equals("2")){
                    try {
                        BufferedImage myPicture = ImageIO.read(new File("img/dirt.png"));
                        ImageIcon imageIcon = new ImageIcon(myPicture);
                        Image image = imageIcon.getImage(); // transform it
                        g.drawImage(image,currentX,currentY,50,50,null );

                    } catch (Exception ignored){}
                }
                else if (records.get(i).get(j).equals("5")){
                    try {
                        BufferedImage myPicture = ImageIO.read(new File("img/border0.png"));
                        ImageIcon imageIcon = new ImageIcon(myPicture);
                        Image image = imageIcon.getImage(); // transform it
                        g.drawImage(image,currentX,currentY,50,50,null );

                    } catch (Exception ignored){}
                }
                else if (records.get(i).get(j).equals("X")){
                    Wizard temp = new Wizard(i,j,"Wizard");
                    int count = 0;
                    for (Entity k : entities){
                        if (k.equals(temp)){
                            count += 1;
                        }
                    }
                    if (count == 0) {
                        entities.add(temp);
                    }

                    for (Entity k: entities){
                        k.draw(g,currentX,currentY,"wizard0");
                    }}
                else if (records.get(i).get(j).equals("M")){
                    Merchant temp = new Merchant(i,j,"Merchant");
                    int count = 0;
                    for (Entity k : entities){
                        if (k.equals(temp)){
                            count += 1;
                        }
                    }
                    if (count == 0) {
                        entities.add(temp);
                    }
                    for (Entity k: entities){
                        k.draw(g,currentX,currentY,"merchant0");
                    }}
                else if (records.get(i).get(j).equals("K")){
                    Knight temp = new Knight(i,j,"Knight");
                    int count = 0;
                    for (Entity k : entities){
                        if (k.equals(temp)){
                            count += 1;
                        }
                    }
                    if (count == 0) {
                        entities.add(temp);
                    }
                    for (Entity k: entities){
                        k.draw(g,currentX,currentY,"knight0");
                    }}
                else if (records.get(i).get(j).equals("E")){
                    Enemy temp = new Enemy(i,j,"Grendel");
                    int count = 0;
                    for (Enemy k : enemies){
                        if (k.equals(temp)){
                            count += 1;
                        }
                    }
                    if (count == 0) {
                        enemies.add(temp);
                    }
                    for (Enemy k: enemies){
                        k.draw(g,currentX,currentY,"grendel0");
                    }}
                else if (records.get(i).get(j).equals("T")){
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

                currentX += 50;
            }
            currentX = 0;
            currentY += 50;
        }
    }
}
