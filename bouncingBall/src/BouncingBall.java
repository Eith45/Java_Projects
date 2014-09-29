

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Melancholia
 * Date: 05.04.14
 * Time: 21:25
 * To change this template use File | Settings | File Templates.
 */

public class BouncingBall extends JPanel implements Runnable{
    public static final int xSize = 700;
    public static final int ySize = 700;
    public static final int FPS = 1000/36;
    public static final int pos = ySize-25;
    public static int top;
    public static int height;
    public static Thread thread;
    private static Object lock = new Object();
    public static volatile boolean paused = true;
    public static boolean drawBall =false;


    static Ball ball;

    public BouncingBall(){
        thread = new Thread(this);
        ball = new Ball(-100, -100);
        paused = true;
        thread.start();
    }

    public static void main(String[] args){

        JFrame mainFrame =  new JFrame("Bouncing ball");
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        final JTextField field = new JTextField();
        field.setBounds(0, 550, 100, 40);
        start.setBounds(0, 500, 100, 40);
        stop.setBounds(0, 600, 100, 40);
        mainFrame.add(start);
        mainFrame.add(stop);
        mainFrame.add(field);
        mainFrame.getContentPane().add(new BouncingBall());
        mainFrame.setSize(xSize, ySize+12);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                field.setText("");
                drawBall = false;
                paused = true;
            }
        });

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                double input = Double.parseDouble(field.getText());
                height = (int) (input * 100);
                drawBall = true;
                top = ySize - height;
                ball.setPos(450, top);
                paused = false;
                synchronized (lock){
                    lock.notifyAll();
                }
            }
        });
        mainFrame.setVisible(true);
    }

    public static void allowPause() {
        synchronized(lock) {
            while(paused) {
                try {
                    lock.wait();
                } catch(InterruptedException e) {
                    // nothing
                }
            }
        }
    }

    public void run() {
        while (true){
            update();
            repaint();
            allowPause();
            try{
                Thread.sleep(FPS);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }
    public void update(){
        ball.update();
    }
    public void paint(Graphics g){
        Color brown = new Color(139,69,19);
        g.setColor(Color.white);
        g.fillRect(100, 0, xSize, pos);
        g.fillRect(0,0, 100, 500);
        g.fillRect(100, pos+8, xSize, ySize);
        if(drawBall){
            ball.draw(g);
        }
        g.setColor(brown);
        for(int i = 0; i < 8; i++){
            g.drawLine(100, pos+i, xSize, pos+i);
        }
        g.drawLine(100, 0, 100, pos);
        for(int i = 1; i <= 6; i++){
            g.drawLine(97, pos - 100*i, 106, pos-100*i  );
            g.drawString(i + "m", 100, pos - 100*i);
        }
    }



}

