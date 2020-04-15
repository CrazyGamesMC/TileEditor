package de.cg.te.ctrl;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class LoadWindow extends JFrame {

    private JButton btnLoad = new JButton("Load");
    private JTextField tfPath = new JTextField();

    public LoadWindow() {
        super("Load File");

        setLayout(null);

        setBounds(150, 150, 300, 200);
        setVisible(true);

        tfPath.setBounds(10, 30, 250, 50);
        add(tfPath);

        btnLoad.setBounds(100, 100, 100, 50);
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClick();
            }
        });
        add(btnLoad);
    }

    private void buttonClick() {
        Control.loadFile(tfPath.getText());
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        App.win.updateWindow();
    }
}