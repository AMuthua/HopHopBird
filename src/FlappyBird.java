import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList; //This stores all the pipes in the game
import java.util.Random;
import javax.swing.*;



public class FlappyBird extends JPanel implements ActionListener, KeyListener{
    int boardWidth = 360;
    int boardHeight = 640;


    // Images
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    // Bird
    int birdX = boardWidth/8;
    int birdY = boardHeight/2;
    int birdWidth = 34;
    int birdHeight = 24;

    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img) {
            this.img = img;
        }

    }

    /*
    Pipes Through which the Bird Hops through
        Definition of the X and Y axis
    
    */
    int pipeX = boardWidth;
    int pipeY = 0; // this means the pipe is going to start at top of screen and right side as well
    int pipeWidth  = 64;
    int pipeHeight = 512;

    class Pipe {
        int x = pipeX;
        int y  = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img; // this is important as we have two pipes, the top and bottom.
        boolean passed = false; // used to check is the bird has b=hooped through the pipe

        Pipe(Image img) {
            this.img = img;
        }
    }

    // Game logic
    Bird bird;
    int velocityX = -4; // moves pipes to the left speed(and simulates the bird moving right by -4 px every frame)
    int velocityY = 0; // when the game starts the bird just falls down.
    int gravity = 1;

    Timer gameLoop;


    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        // setBackground(Color.blue);
        setFocusable(true);
        addKeyListener(this);


        // Load images
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        
        // bird image : Contstructor
        bird = new Bird(birdImg);

        // game timer loop
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        // System.out.println("test");
        // background
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);

        // bird
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
    }

    // This is the move function for the bird
    public void move(){
        /*bird mechanics 
            Gravity is a downward force. So the bird slows down
            change in velocity over time.
            first frame = -6 pixels.
            second frame = -2 pixels
            ...up to 0
            coming down = +2
        
        */ 

        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y,0);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
