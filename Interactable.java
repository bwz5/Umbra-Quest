import javax.swing.*;

public class Interactable {
    int x;
    int y;

    public Interactable(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (o instanceof Interactable){
            Interactable temp = (Interactable) o;
            if (temp.x == x && temp.y == y ){
                return true;
            }
        }
        return false;
    }

    public boolean collided(int playerx, int playery){
        return (playerx == x && playery == y);
    }
    public void collisionDetector(int playerx, int playery, GameComponent frame, Character player){
        if (collided(playerx,playery) && !player.collectedKey){
            JOptionPane.showMessageDialog(frame,"You need the key to enter.");
        } else if (collided(playerx,playery) && player.collectedKey){
            onceCollided(frame);
        }
    }
    public void onceCollided(GameComponent game){
        game.isActive = false;
        game.gameOver = true;
    }
}
