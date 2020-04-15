package de.cg.te.mouse;

import java.awt.event.*;

import de.cg.te.ctrl.ActionWindow;
import de.cg.te.ctrl.App;
import de.cg.te.ctrl.Var;

public class EditListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int x = e.getX()-10;
            int y = e.getY()-10;
            
            int tx = x/Var.gridSize;
            int ty = y/Var.gridSize;

            if (Var.currentLayer < 3) {
                if (!(tx > Var.gridSize || ty > Var.gridSize || tx < 0 || ty < 0)) {
                    Var.tiles[Var.currentLayer-1][ty][tx] = Var.choosenTile;
                }
            }

            if (Var.currentLayer == 3) {
                if (!(tx > Var.gridSize || ty > Var.gridSize || tx < 0 || ty < 0)) {
                    if (Var.tiles[3-1][ty][tx] != 1)
                        Var.tiles[3-1][ty][tx] = 1;
                    else 
                        Var.tiles[3-1][ty][tx] = 0;
                }
            }

            if (Var.currentLayer == 4) {
                if (!(tx > Var.gridSize || ty > Var.gridSize || tx < 0 || ty < 0)) {
                    String current = ""; 

                    if (Var.actionLayer[ty][tx] != null) {
                        current = Var.actionLayer[ty][tx];
                    }

                    new ActionWindow(tx,ty, current);
                }
            }

            App.win.updateWindow();

            System.out.println("Tile created on " + tx + " / " + ty + " with the id of " + Var.choosenTile);

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        

    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}



}