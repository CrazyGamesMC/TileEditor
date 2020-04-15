package de.cg.te.ctrl; 

import java.awt.image.BufferedImage;

public class Var {

    public static boolean initiated = false; 
    public static boolean onActionLayer = false; 
    public static boolean onCollisionLayer = false; 

    public static int[][][] tiles;

    public static String[][] actionLayer; 

    public static int choosenTile = 0; 
    public static int currentLayer = 2; 
    public static int width = 16; 
    public static int height = 16; 
    public static int tileSize = 0; 
    public static final int gridSize = 32; 

    public static String name = ""; 
    public static String imgPath = ""; 

    public static BufferedImage[] images;



}