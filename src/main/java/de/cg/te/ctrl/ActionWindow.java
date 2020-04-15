package de.cg.te.ctrl;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ActionWindow extends JFrame {

    private int xpos, ypos;

    private JButton btnOk = new JButton("Set Action");
    private JTextField tfAction = new JTextField();

    public ActionWindow(int x, int y, String currentString) {
        super("New Action");

        setLayout(null);

        this.xpos = x; 
        this.ypos = y; 

        setBounds(150, 150, 300, 200);
        setVisible(true);

        tfAction.setBounds(10, 30, 250, 50);
        tfAction.setText(currentString);
        add(tfAction);

        btnOk.setBounds(100, 100, 100, 50);
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClick();
            }
        });
        add(btnOk);
    }

    private void buttonClick() {
        Var.actionLayer[ypos][xpos] = tfAction.getText();
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        App.win.updateWindow();
    }
}