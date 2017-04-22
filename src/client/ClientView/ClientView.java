package client.ClientView;

/**
 * Created by ivan on 09.04.2017.
 */
import client.ClientController.ClientController;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClientView extends JPanel  implements MouseMotionListener, Runnable {
    public Point mousePoint;
    public Point playerLocation;
    public JFrame winMain;
    private Object player;
//    private Vector<Object> Objects = new Vector<>();
    private Objects mObjects;
    private ClientController controller;
    public ClientView(ClientController controller_) {
        controller = controller_;
        player = new Object(new Point(100, 100), 40);
        mousePoint = new Point(100, 100);
        winMain = new JFrame();
        winMain.setSize(1024, 768);
        addMouseMotionListener(this);
        winMain.add(this);
        winMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winMain.setVisible(true);
        Thread thread = new Thread(this);

        while (!controller.checkGameStatusUpdate()) {}
        mObjects = new Objects(controller.getGameStatus());

        thread.start();
    }
//    public static void main(String args[])
//    {
//        new ClientView();
//
//    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
//        for(Object o: Objects) {
//
//            g.setColor(o.getColor());
//            Point loc = o.getPosition();
//            g.fillOval(loc.x-o.getRadius()/2, loc.y-o.getRadius()/2, o.getRadius(), o.getRadius());
//        }
        g.setColor(Color.red);
        playerLocation = player.getPosition();
        g.fillOval(playerLocation.x-player.getRadius()/2, playerLocation.y-player.getRadius()/2, player.getRadius(), player.getRadius());
    }


    @Override
    public void mouseDragged(MouseEvent e) {
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        mousePoint = e.getPoint();
    }
    @Override
    public void run() {
        while(winMain.isVisible()) {
            if(mousePoint != null) {
                playerLocation = player.getPosition();
                double Xdif = playerLocation.getX() - mousePoint.getX();
                double Ydif = playerLocation.getY() - mousePoint.getY();
                double angle = Math.atan2(Ydif, Xdif) * 180 / Math.PI;

                double dX = Math.cos(angle * Math.PI/180) * 2;
                double dY = Math.sin(angle * Math.PI/180) * 2;
                // the if test looks confusing but basically it is checking if the sign changes
                /// when adding in dX or dY. if so then set it to the mouseX or mouseY
                double newX = ((playerLocation.getX()-mousePoint.getX())*(playerLocation.getX()-dX-mousePoint.getX()) > 0)
                        ? playerLocation.getX()-dX : mousePoint.getX();
                double newY = ((playerLocation.getY()-mousePoint.getY())*(playerLocation.getY()-dY-mousePoint.getY()) > 0)
                        ? playerLocation.getY()-dY : mousePoint.getY();
				/*double newX = playerLocation.getX()-dX;
				double newY = playerLocation.getY()-dY;*/
                playerLocation.setLocation(newX, newY);
                player.setPosition(playerLocation);
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}