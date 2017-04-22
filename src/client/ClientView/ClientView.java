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
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;

public class ClientView extends JPanel  implements MouseMotionListener, Runnable {
    public Point mousePoint;
    public Point playerLocation;
    public Point oldPlayerLocation = new Point(0,0);
    public JFrame winMain;
    private Object player;
//    private Vector<Object> Objects = new Vector<>();
    private Map<UUID, Object> mObjects;
    private UUID mId;
    private ClientController controller;
    public ClientView(ClientController controller_) {
        controller = controller_;
//        player = new Object(new Point(100, 100), 40);
        mousePoint = new Point(100, 100);
        winMain = new JFrame();
        winMain.setSize(1024, 768);
        addMouseMotionListener(this);
        winMain.add(this);
        winMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winMain.setVisible(true);
        Thread thread = new Thread(this);

        while (!controller.checkGameStatusUpdate()) {}
        mObjects = new Objects(controller.getGameStatus()).getObjects();
        mId = controller.getId();
        player = mObjects.get(mId);

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
        for (Map.Entry<UUID, Object> pair : mObjects.entrySet()){
            if (pair.getKey() == mId)
                continue;
            Object o = pair.getValue();
            g.setColor(o.getColor());
            Point loc = o.getPosition();
            g.fillOval(loc.x-o.getRadius()/2, loc.y-o.getRadius()/2, o.getRadius(), o.getRadius());
        }

        g.setColor(player.getColor());
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

                double dX = Math.cos(angle * Math.PI/180) * 4;
                double dY = Math.sin(angle * Math.PI/180) * 4;
                // the if test looks confusing but basically it is checking if the sign changes
                /// when adding in dX or dY. if so then set it to the mouseX or mouseY
                double newX = ((playerLocation.getX()-mousePoint.getX())*(playerLocation.getX()-dX-mousePoint.getX()) > 0)
                        ? playerLocation.getX()-dX : mousePoint.getX();
                double newY = ((playerLocation.getY()-mousePoint.getY())*(playerLocation.getY()-dY-mousePoint.getY()) > 0)
                        ? playerLocation.getY()-dY : mousePoint.getY();
				/*double newX = playerLocation.getX()-dX;
				double newY = playerLocation.getY()-dY;*/
                playerLocation.setLocation(newX, newY);
                oldPlayerLocation = playerLocation;
                String loc = "" + playerLocation.x + ";" + playerLocation.y + ";";
                controller.updateClientStatus(loc);
                player.setPosition(playerLocation);
                repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (controller.checkGameStatusUpdate()) {
                    String stat = controller.getGameStatus();
                    if (stat.equals("Stop")) {
                        JOptionPane.showMessageDialog(null, "Server has stopped!", "That`s all", JOptionPane.INFORMATION_MESSAGE);
                        winMain.setVisible(false);
                        System.exit(0);
                        break;
                    }
                    mObjects = new Objects(stat).getObjects();
                    player = mObjects.get(mId);
                    if (player == null)
                    {
                        JOptionPane.showMessageDialog(null, "You Lose!", "That`s all", JOptionPane.INFORMATION_MESSAGE);
                        winMain.setVisible(false);
                    }
                }
            }
        }
    }
}