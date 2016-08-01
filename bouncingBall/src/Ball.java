
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Melancholia
 * Date: 05.04.14
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 */
public class Ball {

    private int x;
    private int y;
    private int dx = 0;
    private int dy = 25;
    private int diameter = 50;
    private Color color;

    public  Ball(int x, int y){
        this.x = x;
        this.y = y;
        color = Color.blue;
    }

    public void setPos(int x, int y){
//        System.out.println("Position is seted! ");
        this.x = x;
        this.y = y;
    }

    public void update(){

        move(dx, dy);
        if(BouncingBall.top > BouncingBall.pos + 10){
            BouncingBall.paused = true;
        }

        if(y < BouncingBall.top){
            dy = 25;
        }

        if(y + diameter>= BouncingBall.pos+11){
            dy = -25;
            BouncingBall.top += (BouncingBall.top - BouncingBall.top * 0.98);
        }

    }

    public void draw(Graphics g){
            g.setColor(color);
            if(y + diameter > BouncingBall.pos-10){
                g.fillOval(x, y, diameter+10, diameter);
            }else{
                g.fillOval(x, y, diameter, diameter);
            }
    }

    public void move(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }



}
