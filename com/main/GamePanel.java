package com.main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen settings
    final int originalTileSize = 16; // 16x16
    final int scale = 3; //scale for computer screen size

    final int tileSize = originalTileSize * scale; // 48x48

    //decide how many tiles vertically x horizontally will be displayed on screen
    //this decides screen size

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768px
    final int screenHeight = tileSize * maxScreenRow; //576px

    //Need a gameClock
    Thread gameThread;


    //constructor of gamepanel
    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //improves rendering performance by loading stuff offscreen
    }


    @Override
    public void run() {
        gameThread = new Thread(this);  //instantiating thread
        gameThread.start();

    }
}
