package de.cg.te.mouse;

import java.awt.event.*;

import de.cg.te.ctrl.App;
import de.cg.te.ctrl.ChoosePanel;
import de.cg.te.ctrl.Var;

public class ChooseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int x = e.getX()-10;
            int y = e.getY()-10;

            int tx = x/32;
            int ty = y/32;

            Var.choosenTile = ChoosePanel.getNumber(tx, ty);

            App.win.updateWindow();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}