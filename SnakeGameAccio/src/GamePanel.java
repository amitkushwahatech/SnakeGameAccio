import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {


    //for food position
    private int[] xPos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,475,500,525,550,575,600,675,625,650,700,725,750};
    private int[] yPos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,475,500,525,550,575};
    int RAND_POSX = 33;
    int RAND_POSY = 22;


    //for score
    private int score =0;
    private boolean gameOver = false;
    private Random random = new Random();
    private int enemyX,enemyY;
    //for Snake length
    // x len
    private int[] snakexlength = new int[750];
    //y len
    private int[] snakeylength = new int[750];

    // starting length of snake
    private int lengthOfSnake =3;
    //for direction
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    //count for movements
    private int moves =0;

    //all the image
    // add a icon snake title
    private ImageIcon snaketitle = new ImageIcon( getClass().getResource("assets/snaketitle.jpg"));
    private ImageIcon leftmouth = new ImageIcon( getClass().getResource("assets/leftmouth.png"));
    private ImageIcon rightmouth = new ImageIcon( getClass().getResource("assets/rightmouth.png"));
    private ImageIcon upmouth = new ImageIcon( getClass().getResource("assets/upmouth.png"));
    private ImageIcon downmouth = new ImageIcon( getClass().getResource("assets/downmouth.png"));
    private ImageIcon snakeimage = new ImageIcon( getClass().getResource("assets/snakeimage.png"));
    private ImageIcon enemy = new ImageIcon( getClass().getResource("assets/enemy.png"));

    // for time delay
    private Timer timer;
    private int delay =100;
    GamePanel(){

        //add key listner
        addKeyListener(this);
        // add for key listner work on jpanel
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        //initialize timer
        timer = new Timer(delay,this);
        timer.start();

        // set enemy or food position
        newEnemy();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // draw border
        g.setColor(Color.white);
        // add 2 rect  (for score bord , play ground)
        g.drawRect(24,10,851,55);
        g.drawRect(24,74,851,576);

        //add snake title image as a 1 rect as a title icon
        snaketitle.paintIcon(this, g,25,11);
        // play ground set
        g.setColor(Color.BLACK);
        g.fillRect(25,75,850,575);

        //draw a snake
        //initial position of snake
        if(moves==0){
            snakexlength[0]=100;
            snakexlength[1]=75;
            snakexlength[2]=50;

            snakeylength[0]=100;
            snakeylength[1]=100;
            snakeylength[2]=100;
//            moves++;
        }

        //draw snake head part
        if(left){
            leftmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        }
        if(right){
            rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        }
        if(up){
            upmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        }
        if(down){
            downmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        }

        //draw snake body
        for(int i=1;i<lengthOfSnake;i++){
            snakeimage.paintIcon(this,g,snakexlength[i],snakeylength[i]);
        }

        //draw enemy or food image
        enemy.paintIcon(this,g,enemyX,enemyY);

        //if game over display game over
        if(gameOver){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial",Font.BOLD,50));
            g.drawString("Game Over",300,300);
            //for start
            g.setFont(new Font("Arial",Font.BOLD,20));
            g.drawString("Press SPACE to Restart",320,350);
        }

        //score bord
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.PLAIN,14));
        g.drawString("Score: "+ score,750,30);
        g.setFont(new Font("Arial",Font.PLAIN,14));
        g.drawString("Length: "+ lengthOfSnake,750,50);


        //for dispose draw image
//        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // move body
        for(int i=lengthOfSnake-1;i>0;i--){
            snakexlength[i]=snakexlength[i-1];
            snakeylength[i]=snakeylength[i-1];
        }

        // snake move left dir
        if(left){
            snakexlength[0]= snakexlength[0]-25;
        }
        // snake move right dir
        if(right){
            snakexlength[0]= snakexlength[0]+25;
        }
        // snake move up dir
        if(up){
            snakeylength[0]= snakeylength[0]-25;
        }
        // snake move down dir
        if(down){
            snakeylength[0]= snakeylength[0]+25;
        }

        // after border snake move again show other dir
        // left right
        if(snakexlength[0]>850)snakexlength[0]=25;
        if(snakexlength[0]<25)snakexlength[0]=850;
        //top botom
        if(snakeylength[0]>625)snakeylength[0]=75;
        if(snakeylength[0]<75)snakeylength[0]=625;

        //if snake eat enemy
        collideWithEnemy();
        ///collision check snake
        collideWithBody();

        // draw back if position of snake chane
        repaint();
    }

    // key press method

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            restart();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT && !right){
            left=true;
            right=false;
            up=false;
            down=false;
            moves++;
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT && !left){
            left=false;
            right=true;
            up=false;
            down=false;
            moves++;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP && !down){
            left=false;
            right=false;
            up=true;
            down=false;
            moves++;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN && !up){
            left=false;
            right=false;
            up=false;
            down=true;
            moves++;
        }

    }



    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // locate a new position of enemy or food
    private void newEnemy(){
//        enemyX=xPos[random.nextInt(28)];
//        enemyY=yPos[random.nextInt(19)];
        int r = (int)(Math.random()*(RAND_POSX));
        enemyX = r*25+25;


        r= (int)(Math.random()*(RAND_POSY));
        enemyY = r*25+75;

        for(int i=lengthOfSnake-1;i>0;i--){
            if(snakexlength[0]==enemyX && snakeylength[0]==enemyY){
                newEnemy();
            }
        }
    }

    // food eat if snake head pos == food or enemy
    private void collideWithEnemy(){
        if(snakexlength[0]==enemyX && snakeylength[0]==enemyY){
            newEnemy();
            lengthOfSnake++;
            score++;
        }
    }
    /// If snake head touch its body then game over
    private void collideWithBody(){
        for(int i=lengthOfSnake-1;i>0;i--){
            if(snakexlength[i]==snakexlength[0] && snakeylength[i]==snakeylength[0]){
                timer.stop();
                gameOver=true;
            }
        }
    }

    private void restart(){
        gameOver=false;
        moves=0;
        score=0;
        lengthOfSnake=3;
        left=false;
        right=true;
        up=false;
        down=false;
        timer.start();
        repaint();
    }
}
