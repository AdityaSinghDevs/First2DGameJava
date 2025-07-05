package src.entity;

import src.main.GamePanel;
import src.main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Player extends Entity{

    GamePanel gp ;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues(){
        x= 100;
        y= 100;
        speed = 4;
        direction = "up";
    }

    public void getPlayerImage(){
        try{
            up1 = read(getClass().getResourceAsStream("/player/back1.png"));
            up2 = read(getClass().getResourceAsStream("/player/back2.png"));
            down1 = read(getClass().getResourceAsStream("/player/front1.png"));
            down2 = read(getClass().getResourceAsStream("/player/front2.png"));
            right1 = read(getClass().getResourceAsStream("/player/right1.png"));
            right2 = read(getClass().getResourceAsStream("/player/right2.png"));
            left1 = read(getClass().getResourceAsStream("/player/left1.png"));
            left2 = read(getClass().getResourceAsStream("/player/left2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        //to update info

        if(keyH.upPressed == true){
            direction = "up";
            y = y - speed;
        }
        else if (keyH.downPressed == true){
            direction = "down";
            y += speed;
        }
        else if (keyH.leftPressed == true){
            direction = "left";
            x -= speed;
        }
        else if (keyH.rightPressed == true) {
            direction = "right";
            x +=speed;
        }

        //Player image changes every 10 frames, clock
        spriteCounter++;
        if(spriteCounter>10){
            if(spriteNum ==1){
                spriteNum = 2;
            }
            else if(spriteNum ==2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2 ){
//            g2.setColor(Color.white);
//            g2.fillRect(x, y, gp.tileSize, gp.tileSize );

        BufferedImage image = null ;

        switch (direction){
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;

                }
            }
            case  "down" -> {

                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {

                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {

                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }

         int playerHeight = 2* gp.tileSize; //adjusting player size
         int playerWidth =  gp.tileSize;
        g2.drawImage(image, x, y, playerWidth, playerHeight, null);


    }
}
