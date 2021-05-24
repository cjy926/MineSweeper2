package components;

import Music.Music;
import entity.GridStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public abstract class BasicComponent extends JComponent  {
    Music music;
    boolean touch=false;
    GridStatus status = GridStatus.Covered;
    public BasicComponent() {
        initial();
    }

    private void initial(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton()==1){
                    onMouseLeftClicked();
                }
                if(e.getButton()==3){
                    onMouseRightClicked();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if (status==GridStatus.Covered){
                    touch=true;
                    touch();
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {

                if (touch==true){
                    touch=false;
                    touch();
                }

            }

        });

    }

    /**
     * invoke this method when mouse left clicked
     */
    public abstract void onMouseLeftClicked();

    /**
     * invoke this method when mouse right clicked
     */
    public abstract void onMouseRightClicked();
    public abstract void touch();
}
