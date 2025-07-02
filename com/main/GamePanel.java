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

    //Need the keyhandler
    KeyHandler keyH  = new KeyHandler();

    //Set player's default position
    //IN JAVA UPPER LEFT CORNER IS X:0 Y:0
    //X INCREASES TO RIGHT, Y INCREASES TO DOWN
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; //4px



    //constructor of gamepanel
    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //improves rendering performance by loading stuff offscreen
        this.addKeyListener(keyH);
        this.setFocusable(true); //with this gamepanel can be focused
    }

    public void startGameThread(){
        gameThread = new Thread(this);  //instantiating thread
        gameThread.start();
    }

    @Override
    public void run() {
        //Creating game loop

        while(gameThread != null){

//            System.out.println("The game loop is running");

            //1. UPDATE : update info such as character positions
            //2. DRAW : draw the screen with the updated info

            update();
            repaint(); //calls paintComponent method //yes confusing but yes

        }


    }

    public  void update(){
    //to update info
        if(keyH.upPressed == true){
            playerY = playerY - playerSpeed;
        }
        else if (keyH.downPressed == true){
            playerY += playerSpeed;
        }
        else if (keyH.leftPressed == true){
            playerX -= playerSpeed;
        }
        else if (keyH.rightPressed == true) {
            playerX +=playerSpeed;

        }
    }

    public void paintComponent(Graphics g){
        //graphics and paintcomponent pre built class and method in java
        //graphics class has many functions to draw obj on screen, graphics is like paintbrush
        //to paint update environment

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; //change the graphics g -> graphics2d g2
        //extends graphics to provide more control over geometry, coordinate and transformation
        //color management, and text layout

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize );
        g2.dispose(); //saves memory
    }
}
