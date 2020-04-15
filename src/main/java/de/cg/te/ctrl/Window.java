package de.cg.te.ctrl;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import de.cg.te.mouse.ChooseListener;
import de.cg.te.mouse.EditListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {

    private JPanel editPanel = new EditPanel();
    private JPanel choosePanel = new ChoosePanel();

    private JMenuBar menuBar = new JMenuBar();
    private JMenu mFile = new JMenu("File");
    private JMenuItem iFileOpen = new JMenuItem("Open");
    private JMenuItem iFileSave = new JMenuItem("Save");
    private JMenuItem iFileNew = new JMenuItem("New");
    private JMenu mLayer = new JMenu("Layer");
    private JMenuItem iLayerOne = new JMenuItem("Layer 1");
    private JMenuItem iLayerTwo = new JMenuItem("Layer 2");
    private JMenuItem iLayerCollisions = new JMenuItem("Collisions");
    private JMenuItem iLayerActions = new JMenuItem("Actions");

    public Window() {
        super("Tile Editor");

        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setBounds(50, 50, 1280, 720);

        setLayout(null);

        


        //Menu Bar
        iFileNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateWindow cw = new CreateWindow(); 
            }

        });
        iFileSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Control.saveFile();
            }

        });
        iFileOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoadWindow();
            }

        });

        initLayerMenu();

        mFile.add(iFileNew);
        mFile.add(iFileOpen);
        mFile.add(iFileSave);

        mLayer.add(iLayerOne);
        mLayer.add(iLayerTwo);
        mLayer.add(iLayerCollisions);
        mLayer.add(iLayerActions);
        
        menuBar.add(mFile);
        menuBar.add(mLayer);
        menuBar.setBounds(0,0,1280,20);

        add(menuBar);

        //Panels
        editPanel.addMouseListener(new EditListener());
        editPanel.setBounds(300, 50, 960, 650);
        add(editPanel);

        choosePanel.addMouseListener(new ChooseListener());
        choosePanel.setBounds(20, 50, 250, 650);
        add(choosePanel);

        updateWindow();
    }

    public void updateWindow() {
        repaint();
        validate();
        
    }

    public void initLayerMenu() {
        iLayerOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Var.currentLayer = 1;
                Var.onCollisionLayer = false; 
                Var.onActionLayer = false;
                updateWindow();
            }
        });

        iLayerTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Var.currentLayer = 2;
                Var.onCollisionLayer = false; 
                Var.onActionLayer = false;
                updateWindow();
            }
        });

        iLayerCollisions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Var.currentLayer = 3;
                Var.onCollisionLayer = true; 
                Var.onActionLayer = false;
                updateWindow();
            }
        });

        iLayerActions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Var.currentLayer = 4;
                Var.onCollisionLayer = false; 
                Var.onActionLayer = true;
                updateWindow();
            }
        });
    }



}