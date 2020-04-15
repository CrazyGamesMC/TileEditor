package de.cg.te.ctrl;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage; 

public class EditPanel extends JPanel{
    
    @Override
    public void paintComponent(Graphics g) {

        final int size = Var.gridSize; 

        if (Var.initiated) {
            setSize(20+size*Var.width, 20+size*Var.height);

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            //Grid
            for (int y = 0; y<Var.height; y++) {
                for (int x = 0; x<Var.width; x++) {
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(10+x*size, 10+y*size, size, size);

                    int t1 = Var.tiles[0][y][x];
                    int t2 = -1;
                    boolean hasCollision = false; 
                    boolean hasAction = false;
                    if (Var.currentLayer > 1) {
                        t2 = Var.tiles[1][y][x];
                        if (Var.currentLayer == 3) {
                            hasCollision = (Var.tiles[2][y][x] == 1);
                        }

                        if (Var.currentLayer == 4) {
                            hasAction = (Var.actionLayer[y][x] != null && !Var.actionLayer[y][x].equals(""));
                        }
                    }

                    if (t1 != -1) {
                        g.drawImage(Var.images[t1].getScaledInstance(size, size, BufferedImage.SCALE_REPLICATE), 10+x*size, 10+y*size, null);
                    }

                    if (t2 != -1) {
                        g.drawImage(Var.images[t2].getScaledInstance(size, size, BufferedImage.SCALE_REPLICATE), 10+x*size, 10+y*size, null);
                    }

                    if (hasCollision) {
                        g.setColor(Color.RED);
                        g.fillRect(10+x*size+8, 10+y*size+8, 16, 16);
                    }

                    if (hasAction) {
                        g.setColor(Color.GREEN);
                        g.fillRect(10+x*size+8, 10+y*size+8, 16, 16);
                    }

                }
            }
        }


    }

}