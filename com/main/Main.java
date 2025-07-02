package com.main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); //adding the panel inside the window
        window.pack(); //to see it


        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}
