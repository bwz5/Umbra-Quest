import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory extends JLabel {

    class Item {
        String name;
        String description;

        public Item(String name, String description){
            this.name = name;
            this.description = description;
        }
    }
    List<Item> inventory = new ArrayList<>();

    int maxCapacity = 20;

    public Inventory(){};

    public void addItem(String name, String description){
        Item temp = new Item(name, description);
        inventory.add(temp);
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(new Color(0.81f, 0.63f, 0.48f, 0.9f));
        g.fillRect(100,100,300,300);
        g.setColor(Color.black);
        g.drawString(this.getText(),130,130);
    }

    public void printText(){
        String temp = "";
        if (inventory.isEmpty()){
            temp = "Nothing is in your inventory";
        }
        for (Item i : inventory){
            temp += i.name + ": " + i.description + "\n";

        }
        this.setText(temp);
    }
}
