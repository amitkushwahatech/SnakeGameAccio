import javax.swing.*;
import java.awt.*;

public class snakeGame2  {

    public static  void main(String[]args){

        //set the JFrame title
        JFrame frame  = new JFrame("Snake Game 2.o");
        //set width and height of frame
        frame.setBounds(10,10,905,700);
        //resiz is false
        frame.setResizable(false);
        //If cut or exit program close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // make a panel where game play
        GamePanel panel = new GamePanel();
        // set a background colr
        panel.setBackground(Color.DARK_GRAY);
        // add panel into frame
        frame.add(panel);
        //set visibiltity true display all things
        frame.setVisible(true);
    }
}
