package de.cg.te.ctrl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.image.*;

public class Control {

    public static void initImages(String imgPath, int tileSize) {
        try {
            BufferedImage img = ImageIO.read(new File(imgPath));

            int imgColumns = img.getWidth() / tileSize;
            int imgRows = img.getHeight() / tileSize;

            int totalEntries = imgColumns * imgRows;

            Var.images = new BufferedImage[totalEntries];

            for (int section = 0; section < totalEntries; section++) {
                int counter = 0;
                int imgX = 0;
                int imgY = 0;
                boolean tf = false;

                for (int i = 0; i < imgRows && !tf; i++) {
                    for (int j = 0; j < imgColumns && !tf; j++) {
                        if (counter == section) {
                            imgX = j * tileSize;
                            imgY = i * tileSize;
                            tf = true;
                        }
                        counter++;
                    }
                }

                BufferedImage subImage = img.getSubimage(imgX, imgY, tileSize, tileSize);

                Var.images[section] = subImage;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void newFile(int w, int h, String name, String imgPath, int tileSize) {
        Var.name = name;
        Var.width = w;
        Var.height = h;
        Var.tileSize = tileSize;
        Var.imgPath = imgPath; 

        initImages(imgPath, tileSize);

        Var.tiles = new int[4][h][w];

        for (int l = 0; l < 4; l++) {
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    Var.tiles[l][y][x] = -1;
                }
            }
        }

        Var.actionLayer = new String[h][w];

        Var.initiated = true;

    }

    public static void saveFile() {
        ArrayList<String> contents = new ArrayList<>();

        contents.add(Var.imgPath + "," + Var.tileSize + "," + Var.width + "," + Var.height);

        for (int l = 0; l < 2; l++) {
            String toAdd = "";
            for (int y = 0; y < Var.height; y++) {
                for (int x = 0; x < Var.width; x++) {
                    toAdd = toAdd + Var.tiles[l][y][x] + ",";
                }
            }
            toAdd = toAdd + "0";
            contents.add(toAdd);
        }

        String toAdd = "";
        for (int y = 0; y < Var.height; y++) {
            for (int x = 0; x < Var.width; x++) {
                char adder = 'o';
                int tile = Var.tiles[2][y][x];

                if (tile == 1)
                    adder = 'x';

                toAdd = toAdd + adder + ",";
            }
        }
        toAdd = toAdd + "o";
        contents.add(toAdd);

        toAdd = "";
        for (int y = 0; y < Var.height; y++) {
            for (int x = 0; x < Var.width; x++) {
                String adder = "o";
                if (Var.actionLayer[y][x] != null && Var.actionLayer[y][x] != "") {
                    adder = Var.actionLayer[y][x];
                }
                toAdd = toAdd + adder + ",";
            }
        }

        toAdd = toAdd + "o";
        contents.add(toAdd);

        File file = new File(Var.name);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            PrintWriter writer = new PrintWriter(file);
            for (String line : contents) {
                writer.println(line);
            }

            writer.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void loadFile(String path) {
        File file = new File(path);

        if (!file.exists()) {
            return; 
        }

        ArrayList<String> contents = new ArrayList<>(); 

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));  

            String line = "";
            while ((line = reader.readLine()) != null) {
                contents.add(line);
            }

            reader.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Load options
        String lineOne = contents.get(0);
        String[] options = lineOne.split(",");

        Var.name = path;
        Var.imgPath = options[0];
        Var.tileSize = Integer.parseInt(options[1]);
        Var.width = Integer.parseInt(options[2]);
        Var.height = Integer.parseInt(options[3]);

        //Load layers 1 & 2
        Var.tiles = new int[4][Var.width][Var.height];

        String[][] loadedTiles = new String[2][Var.width*Var.height+1];

        loadedTiles[0] = contents.get(1).split(",");
        loadedTiles[1] = contents.get(2).split(",");

        for (int y = 0; y<Var.height; y++) {
            for (int x = 0; x<Var.width; x++) {
                int number = y*Var.width+x;
                Var.tiles[0][y][x] = Integer.parseInt(loadedTiles[0][number]);
                Var.tiles[1][y][x] = Integer.parseInt(loadedTiles[1][number]);
            }
        }

        //Load collision layer
        String[] loadedCollisions = contents.get(3).split(",");
        for (int y = 0; y<Var.height; y++) {
            for (int x = 0; x<Var.width; x++) {
                int number = y*Var.width+x;
                int toSet = 0; 
                if (loadedCollisions[number].equals("x")) {
                    toSet = 1; 
                }
                Var.tiles[2][y][x] = toSet;
            }
        }

        //Load actions layer
        Var.actionLayer = new String[Var.height][Var.width];

        String[] loadedActions = contents.get(4).split(",");
        for (int y = 0; y<Var.height; y++) {
            for (int x = 0; x<Var.width; x++) {
                int number = y*Var.width+x;
                String toSet = loadedActions[number];
                if (toSet.equals("o")) {
                    toSet = ""; 
                }
                Var.actionLayer[y][x] = toSet; 
            }
        }

        initImages(Var.imgPath, Var.tileSize);

        Var.initiated = true; 

    }
}