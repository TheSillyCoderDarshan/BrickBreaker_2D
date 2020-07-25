package com.company;


import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score=0;

    private int totalBricks = 21;
    private Timer timer;
    private int delay=5;

    private int playerx = 310;

    private int ballposX = 360;
    private int ballposY = 360;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private MapGenerator map;
    public  Gameplay() {
        map = new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g){
        //bg
        g.setColor(Color.cyan);
        g.fillRect(1,1, 692, 592);

        //border
        g.setColor(Color.yellow);
        g.fillRect(0,0, 3, 592);
        g.fillRect(0,0, 692, 3);
        g.fillRect(691,0, 3, 592);

        //map
        map.draw((Graphics2D)g);

        //paddle
        g.setColor(Color.black);
        g.fillRect(playerx, 550, 100, 8);


        //ball
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);

        if(totalBricks<=0){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD, 30));
            g.drawString("YOU WON! Score: "+score,240,300);
        }

        if(ballposY>570){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD, 30));
            g.drawString("GAME OVER!, Score: "+score,240,260);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press ENTER to restart ",230,300);

        }


        g.dispose();

    }

    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            ballposX+=ballXdir;
            ballposY+=ballYdir;
            if(ballposX < 0){
                ballXdir = -ballXdir;
            }
            if(ballposY<0){
                ballYdir = -ballYdir;
            }
            if(ballposX > 670){
                ballXdir =-ballXdir;
            }
            if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerx,550,100,8))) {
                ballYdir = -ballYdir;
            }
            for(int i=0; i<map.map.length; i++)
            {
                for(int j=0; j<map.map[0].length; j++){
                    if(map.map[i][j]>0){
                        int brickX = j* map.brickW + 80;
                        int brickY = i* map.brickH + 50;
                        int brickW = map.brickW;
                        int brickH = map.brickH;

                        Rectangle rect = new Rectangle(brickX, brickY, brickW, brickH);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score+=10;
                            if(ballposX + 19 <= brickRect.x || ballposX+1 >= brickRect.x + brickRect.width){
                                ballXdir=-ballXdir;
                            }
                            else{
                                ballYdir=-ballYdir;
                            }
                        }
                    }
                }
            }

            }

            repaint();
            }


    public void keyTyped(KeyEvent e) {}


    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerx >=600){
                playerx = 600;
            }else{
                moveright();
            }

        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerx <=10){
                playerx = 10;
            }else{
                moveleft();
            }

        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play= true;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                playerx = 310;
                totalBricks = 21;
                map = new MapGenerator(3,7);
                repaint();
            }
        }

    }
    public void moveright(){
        play = true;
        playerx+=25;
    }
    public void moveleft(){
        play = true;
        playerx-=25;
    }




    public void keyReleased(KeyEvent e) {}

}
