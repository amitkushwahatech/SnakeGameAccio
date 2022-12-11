import javax.swing.*;
//inherit all the property of JFrame (java swing class to make simple frame)
public class SnakeGame extends JFrame {
    private GameBoard board;
    //constructor of the class
   public SnakeGame(){

       //initialise
       board = new GameBoard();
       //add it
       add(board);
//       setBounds(100,100,800,500);
       setResizable(false);
       pack();
       setVisible(true);
   }
   public static void main(String[]args){
       JFrame snakeGame = new SnakeGame();
   }
}
