package de.cg.te.ctrl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class CreateWindow extends JFrame {

    private JButton btnCreate = new JButton("Create");
    private JTextField[] createFields;

    public CreateWindow() {
        super("New Tile Map");
        setBounds(150, 150, 400, 500);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        btnCreate.setBounds(125, 400, 150, 50);
        add(btnCreate);

        createFields = generate("Width: ", "Height: ", "Name: ", "TileSet: ", "Tile size: ");

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create(createFields);
            }
        });
    }

    public JTextField[] generate(String... varNames) {
        JTextField[] fields = new JTextField[varNames.length];

        for (int i = 0; i<varNames.length; i++) {
            String name = varNames[i]; 
            
            JLabel lbl = new JLabel(name);
            lbl.setBounds(50, 50+i*50, 100, 40);
            add(lbl);

            JTextField jtf = new JTextField();
            jtf.setBounds(160, 50+i*50,150,40);
            add(jtf);
            fields[i] = jtf; 
        }

        return fields; 
    }

    public void create(JTextField... fields) {

        int width = Integer.parseInt(fields[0].getText());
        int height = Integer.parseInt(fields[1].getText());
        String name = fields[2].getText();
        String imgPath = fields[3].getText();
        int tileSize = Integer.parseInt(fields[4].getText());

        Control.newFile(width, height, name, imgPath, tileSize);

        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        
        App.win.updateWindow();
    }

}