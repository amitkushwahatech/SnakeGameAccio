# SnakeGameAccio

### Step 1 :  
              First we create a class SnakeGame 
              and also add resources ( where image of apple ,snake{body,head} )
              
####1 :)  inherit the property of JFrame for frame

 public class SnakeGame extends JFrame {
   
   public SnakeGame(){       
//       setBounds(100,100,800,500);
// false because if we change the size of frame then it also effect the gamplay pannel 
       setResizable(false);
       setVisible(true);
   }
   public static void main(String[]args){
       JFrame snakeGame = new SnakeGame();
   }
}


####2 :)    Create a one more class GameBuilder

import javax.swing.*;
// here we add a panel into a frame where all the game play happen
public class GameBoard extends JPanel{

// initialize a height and width  of the panel 
    int height = 400;
    int width = 400;
    
    // use for defined the snake 
    int x[] = new int[height*width];
    int y[] = new int[height*width];
    // size of the snake 
    int dots;
    
}


### Step 2 : 
          Add the GameBuilder class private into SnakeGame 
          
####           
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


### Step 3 :
          Now need to contruct the constructer of the  GameBuilder 
          when we start or call it need to load something intial or
          start to show the view of game

#### add the constructor part
    
    import javax.swing.*;
    public class GameBoard extends JPanel{

// initialize a height and width  of the panel 
    int height = 400;
    int width = 400;
    
    // use for defined the snake 
    int x[] = new int[height*width];
    int y[] = new int[height*width];
    // size of the snake 
    int dots;
    
    public GameBoard(){
        
        setFocusable(true);
        setPreferredSize(new Dimension(width, height));
        //set panel background color black
        setBackground(Color.BLACK);
    }
}



### Step 4: 
        #####  Now add a function to load a images of snake and food into GameBuilder
          
          
     private void loadImages(){
        ImageIcon image_apple = new ImageIcon("src/resources/apple.png");
        apple = image_apple.getImage();

        ImageIcon image_head = new ImageIcon("src/resources/head.png");
        head = image_head.getImage();

        ImageIcon image_body = new ImageIcon("src/resources/dot.png");
        body = image_body.getImage();
    }
    
    

### Step 5 : 
         now call the loadImage funtion into GameBuilder
        ###### Final code after add
         
  import javax.swing.*;
  public class GameBoard extends JPanel{

// initialize a height and width  of the panel 
    int height = 400;
    int width = 400;
    
    // use for defined the snake 
    int x[] = new int[height*width];
    int y[] = new int[height*width];
    // size of the snake 
    int dots;
    
    public GameBoard(){
        
        setFocusable(true);
        setPreferredSize(new Dimension(width, height));
        //set panel background color black
        setBackground(Color.BLACK);
        
        loadImages()
    }
    
    private void loadImages(){
        ImageIcon image_apple = new ImageIcon("src/resources/apple.png");
        apple = image_apple.getImage();

        ImageIcon image_head = new ImageIcon("src/resources/head.png");
        head = image_head.getImage();

        ImageIcon image_body = new ImageIcon("src/resources/dot.png");
        body = image_body.getImage();
    }
}


###Step 6 : 
          initalize the game here we place the snake
          
          
          
     public void initGame(){
        dots = 3;
        for(int i = 0;i<dots;i++){
            x[i] = 150+i*dot_size;
            y[i] = 150;
        }
        timer = new Timer(DELAY, this);
        timer.start();
    }


###### final code after add initGame into GameBuilder


  import javax.swing.*;
  public class GameBoard extends JPanel{

// initialize a height and width  of the panel 
    int height = 400;
    int width = 400;
    
    // use for defined the snake 
    int x[] = new int[height*width];
    int y[] = new int[height*width];
    // size of the snake 
    int dots;
    // position of apple x,y and also defined here size of snake 
    int apple_x =100;
    int apple_y = 100;
    int dot_size = 10;
    Image apple;
    Image body;
    Image head;

// use for check left , right, down , up feature
    boolean leftDirection = true;
    boolean rightDirection = false;
    boolean upDirection = false;
    boolean downDirection = false;

// timer and delay
    Timer timer;
    int DELAY = 300;

// random position for apple to display that position its defined 
//max one position if great don't display it go out from the panel
    int RAND_POS = 39;
    boolean inGame = true;
    
    public GameBoard(){
        
        setFocusable(true);
        setPreferredSize(new Dimension(width, height));
        //set panel background color black
        setBackground(Color.BLACK);
        
        
        loadImages()
        initGame()
    }
    
    
     public void initGame(){
        dots = 3;
        for(int i = 0;i<dots;i++){
            x[i] = 150+i*dot_size;
            y[i] = 150;
        }
        timer = new Timer(DELAY, this);
        timer.start();
    }
    private void loadImages(){
        ImageIcon image_apple = new ImageIcon("src/resources/apple.png");
        apple = image_apple.getImage();

        ImageIcon image_head = new ImageIcon("src/resources/head.png");
        head = image_head.getImage();

        ImageIcon image_body = new ImageIcon("src/resources/dot.png");
        body = image_body.getImage();
    }
}



###Step 7: 
        Now we need to draw the image into the pannel 
        we overide the paintComponent and draw the image on pannel
 
 
         @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        if(inGame){
            graphics.drawImage(apple, apple_x, apple_y, this);
            for(int i = 0;i<dots;i++){
                if(i==0){
                    graphics.drawImage(head, x[0], y[0], this);
                }
                else{
                    graphics.drawImage(body, x[i], y[i], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }
        else{
            gameOver(graphics);
        }
    }
    
    
    
### Step 8: 
          Locate the apple randomly into a game pannel in GameBuilder
          
        
        
        
     private void locateApple(){
        int r = (int)(Math.random()*(RAND_POS));
        apple_x = r*dot_size;

        r = (int)(Math.random()*(RAND_POS));
        apple_y = r*dot_size;
    }
    
    
    
    
###Step 9: 
        inherit the ActionListener to perform a task like like move 
        colision tpanel work
        
        public class GameBoard extends JPanel implements ActionListener {
        
        ...
        .....
        .......
        
        }
        
        
 ###Step 10 :
         Add move function to move a snake up and down , left and right
         
  private void move(){
        for(int i = dots-1;i>0;i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(leftDirection){
            x[0] -= dot_size;
        }
        if(rightDirection){
            x[0] += dot_size;
        }
        if(upDirection){
            y[0] -= dot_size;
        }
        if(downDirection){
            y[0] += dot_size;
        }

    }
    
    
###Step 11 :  
          Add a funtion into GameBuilder  to check a position of apple 
          if condition for if head of snake touch apple then size of
          snake increase 

   private void checkApple(){
        if(x[0]==apple_x&&y[0]==apple_y){
            dots++;
            locateApple();
        }
    }
        
        
 ###Step 12 :
         Add function for check the collision fo snake 
         // if x[0]<0 or x[0]>=width or y[0] or y[0]>=height
         its mean strike to wall (collision ho gya baba! *)
         
         
     private void checkCollision(){
        if(x[0]<0){
            inGame = false;
        }
        if(x[0]>=width){
            inGame = false;
        }
        if(y[0]<0){
            inGame = false;
        }
        if(y[0]>=height){
            inGame = false;
        }
        for(int i = dots-1;i>=3;i--){
            if(x[0]==x[i]&&y[0]==y[i]){
                inGame = false;
                break;
            }
        }
    }
    
    
 ###Step 13 :
          Now action performed if collison happend or move the snake 
          or check position of apple  
     
     
     @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(inGame){
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }
    
    
 ###Step 14 :
         TAdapter class use for perform a key press task 
         using inherit the property of keyAdapter
         
         If we press the key then move the snake
         up, down, left, right
         provide the direction to the snake
         
           
         
     public class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent keyEvent){
            int key = keyEvent.getKeyCode();
            if((key == KeyEvent.VK_LEFT)&&(!rightDirection)){
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if((key == KeyEvent.VK_RIGHT)&&(!leftDirection)){
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if((key == KeyEvent.VK_UP)&&(!downDirection)){
                leftDirection = false;
                upDirection = true;
                rightDirection = false;
            }
            if((key == KeyEvent.VK_DOWN)&&(!upDirection)){
                leftDirection = false;
                rightDirection = false;
                downDirection = true;
            }


        }
    }
    
    
  ###Step 14 :
            When collision happened then it means game over
            we need to display the " Game Over "
            
            Add some font size , color 



      private void gameOver(Graphics graphics){
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metrics = getFontMetrics(small);
        graphics.setColor(Color.WHITE);
        graphics.setFont(small);
        graphics.drawString(msg, (width-metrics.stringWidth(msg))/2, height/2);

    }
    
    
    
###Step 15 : 
    #### Final view of gameBuilder class
    
    
    import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JPanel implements ActionListener {
    int height = 400;
    int width = 400;
    int x[] = new int[height*width];
    int y[] = new int[height*width];
    int dots;
    int apple_x =100;
    int apple_y = 100;
    int dot_size = 10;
    Image apple;
    Image body;
    Image head;

    boolean leftDirection = true;
    boolean rightDirection = false;
    boolean upDirection = false;
    boolean downDirection = false;

    Timer timer;
    int DELAY = 300;

    int RAND_POS = 39;
    boolean inGame = true;
    public GameBoard(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);

        loadImages();
        initGame();
    }
    public void initGame(){
        dots = 3;
        for(int i = 0;i<dots;i++){
            x[i] = 150+i*dot_size;
            y[i] = 150;
        }
        timer = new Timer(DELAY, this);
        timer.start();
    }
    private void loadImages(){
        ImageIcon image_apple = new ImageIcon("src/resources/apple.png");
        apple = image_apple.getImage();

        ImageIcon image_head = new ImageIcon("src/resources/head.png");
        head = image_head.getImage();

        ImageIcon image_body = new ImageIcon("src/resources/dot.png");
        body = image_body.getImage();
    }
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        if(inGame){
            graphics.drawImage(apple, apple_x, apple_y, this);
            for(int i = 0;i<dots;i++){
                if(i==0){
                    graphics.drawImage(head, x[0], y[0], this);
                }
                else{
                    graphics.drawImage(body, x[i], y[i], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }
        else{
            gameOver(graphics);
        }
    }
    private void move(){
        for(int i = dots-1;i>0;i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(leftDirection){
            x[0] -= dot_size;
        }
        if(rightDirection){
            x[0] += dot_size;
        }
        if(upDirection){
            y[0] -= dot_size;
        }
        if(downDirection){
            y[0] += dot_size;
        }

    }
    private void locateApple(){
        int r = (int)(Math.random()*(RAND_POS));
        apple_x = r*dot_size;

        r = (int)(Math.random()*(RAND_POS));
        apple_y = r*dot_size;
    }
    private void checkApple(){
        if(x[0]==apple_x&&y[0]==apple_y){
            dots++;
            locateApple();
        }
    }
    private void checkCollision(){
        if(x[0]<0){
            inGame = false;
        }
        if(x[0]>=width){
            inGame = false;
        }
        if(y[0]<0){
            inGame = false;
        }
        if(y[0]>=height){
            inGame = false;
        }
        for(int i = dots-1;i>=3;i--){
            if(x[0]==x[i]&&y[0]==y[i]){
                inGame = false;
                break;
            }
        }
    }
    private void gameOver(Graphics graphics){
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metrics = getFontMetrics(small);
        graphics.setColor(Color.WHITE);
        graphics.setFont(small);
        graphics.drawString(msg, (width-metrics.stringWidth(msg))/2, height/2);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(inGame){
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }

    public class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent keyEvent){
            int key = keyEvent.getKeyCode();
            if((key == KeyEvent.VK_LEFT)&&(!rightDirection)){
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if((key == KeyEvent.VK_RIGHT)&&(!leftDirection)){
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }
            if((key == KeyEvent.VK_UP)&&(!downDirection)){
                leftDirection = false;
                upDirection = true;
                rightDirection = false;
            }
            if((key == KeyEvent.VK_DOWN)&&(!upDirection)){
                leftDirection = false;
                rightDirection = false;
                downDirection = true;
            }


        }
    }

}




#Congratulation !  ##Snake Game Complete
