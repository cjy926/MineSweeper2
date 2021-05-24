package minesweeper;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class MineLabel extends JLabel {
    Player p1;
    Player p2;
    int minecount;
    int leftmine;
    public MineLabel(Player p1,Player p2,int minecount){
        this.p1=p1;
        this.p2=p2;
        this.minecount=minecount;
        this.setText(String.format("剩  余  雷  数:  %d",minecount));
    }
    public void update() {
        leftmine=minecount- p1.getAddscore()-p1.getCostscore()-p2.getAddscore()-p2.getCostscore();
        this.setText(String.format("剩  余  雷  数:  %d",leftmine));
    }


    public int getLeftmine() {
        return leftmine;
    }
}
