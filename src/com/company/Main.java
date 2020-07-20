package com.company;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Gameplay GP = new Gameplay();
        obj.setBounds(10,10,700,600);
        obj.setTitle("Block Breaker 2D -By Darshan");
        obj.setResizable(true);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(GP);

    }
}
