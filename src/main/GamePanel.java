package src.main;

import src.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen settings
    final int originalTileSize = 16; // 16x16
    final int scale = 3; //scale for computer screen size

    public final int tileSize = originalTileSize * scale; // 48x48

    //decide how many tiles vertically x horizontally will be displayed on screen

    //this decides screen size
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768px
    final int screenHeight = tileSize * maxScreenRow; //576px

    //FPS
    int FPS = 60 ;

    //Need a gameClock
    Thread gameThread;

    //Need the keyhandler
    KeyHandler keyH  = new KeyHandler();
    Player player = new Player(this, keyH);

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

    /* USING UPDATE AND PAINTCOMPONENT WE STARTED A GAME LOOP, THAT IS LIKE
    UPDATE -> PAINT -> UPDATE -> PAINT
    BUT WE NEED TO PUT CAP TO THIS PROCESS, BECAUSE FOR A MODERN CPU,
    ITS JUST A SIMPLE WHITE RECTANGLE RENDER, HENCE IT CAN DO IT MILLION TIMES PER SECOND, ONCE INITIATED
    THEREFORE, MAKING THE RECTANGLE DISSAPEAR, EVEN THOUGH ITS THERE !
    NEED TO SET THE FPS LIMIT

    and for that we need to figure out the current time and time taken between update and repaint
     */

    @Override
//    public void run() {
//        //SLEEP GAME LOOP METHOD

//        double drawInterval = 1000000000/FPS ; //basically 1second/FPS (9 zeroes)
//        //draw screen this many times //after every 0.01666 seconds
//
//        double nextDrawTime = System.nanoTime() + drawInterval ; //draw screen again after current time + interval
//        //therefore cap applied
//
//        //Creating game loop
//        while(gameThread != null){
//
//            long currentTime = System.nanoTime(); //very small unit , can use milli too
//            System.out.println(currentTime);
//
//
//
////            System.out.println("The game loop is running");
//
//            //1. UPDATE : update info such as character positions
//            //2. DRAW : draw the screen with the updated info
//
//            update();
//            repaint(); //calls paintComponent method //yes confusing but yes
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000; //1 second = 1000 ms and 1,000,000,000 ns
//                //time remaining until next draw time, and we make the thread sleep until remaining time
//
//                if(remainingTime < 0){
//                    remainingTime = 0 ;  //safety that if incase our update and repaint took more than remaining time, thread womnt sleep
//                }
//                Thread.sleep((long)remainingTime); //accepts number in millisecond only
//
//                nextDrawTime +=  drawInterval ;
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//
//
//    }

    public void run(){

        //DELTA GAME LOOP METHOD

        double drawInterval = 1000000000/FPS;  //ns per frame
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;


        while (gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime -lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000){
                System.out.println("FPS : " + drawCount);
                drawCount = 0 ;
                timer = 0;
            }
        }

    }



    public  void update(){
    //to update info

     player.update();
        }


    public void paintComponent(Graphics g){
        //graphics and paintcomponent pre built class and method in java
        //graphics class has many functions to draw obj on screen, graphics is like paintbrush
        //to paint update environment

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; //change the graphics g -> graphics2d g2
        //extends graphics to provide more control over geometry, coordinate and transformation
        //color management, and text layout

        player.draw(g2);
        g2.dispose(); //saves memory
    }
}
