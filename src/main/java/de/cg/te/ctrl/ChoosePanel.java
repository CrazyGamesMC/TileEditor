package de.cg.te.ctrl;

import javax.swing.JPanel;

import java.awt.*;

public class ChoosePanel extends JPanel{

    @Override
    public void paintComponent(Graphics g) {

        if (!Var.initiated)
            return; 

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (int i = 0; i<Var.images.length; i++) {
            int[] cords = getCoordinates(i); 
            int x = cords[0];
            int y = cords[1];

            g.drawImage((Image) Var.images[i].getScaledInstance(32, 32, 8), 10+x*32, 10+y*32, null);

            if (i == Var.choosenTile) {
                g.setColor(Color.RED);
                g.drawRect(10+x*32, 10+y*32, 32, 32);
            }
        }

        setSize(getWidth(), 20+(Var.images.length/7)*32);
    }

    public static int[] getCoordinates(int number) {
        int[] cords = {0, 0}; 

        cords[1] = number/7;
        cords[0] = number-cords[1]*7 ;
    
        return cords; 
    }

    public static int getNumber(int x, int y) {
        return 7*y+x;
    }

}