package server.ServerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import server.ServerController.ServerController;

/**
 * Created by ivan on 08.04.2017.
 */
public class ServerView {
    private JPanel panel1;
    private JButton startButton;
    private JTextArea logArea;
    private JPanel mainPanel;
    private ServerController serverController = null;
    private boolean status = false;
    public ServerView() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!status) {
                    //serverController.start();
                    startButton.setText("Stop");
                    status = true;
                }
                else {
                    //serverController.stop();
                    startButton.setText("Start");
                    status = false;
                }

            }
        });
    }
    public void start(ServerController controller) {
        serverController = controller;
        JFrame frame = new JFrame("Server");
        frame.setContentPane(new ServerView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
