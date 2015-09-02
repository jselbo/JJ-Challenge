package com.selbo.main;

import com.selbo.display.GamePanel;

import javax.swing.JFrame;

/**
 * Created by Josh on 9/1/2015.
 */
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Challenge");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel();
        frame.setContentPane(gamePanel);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gamePanel.startGame();
    }
}
