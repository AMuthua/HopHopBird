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
        boolean passed = false; // used to check is the bird has hooped through the pipe

        Pipe(Image img) {
            this.img = img;
        }
    }

    // Game logic
    Bird bird;
    int velocityX = -4; // moves pipes to the left speed(and simulates the bird moving right by -4 px every frame)
    int velocityY = 0; // when the game starts the bird just falls down.
    int gravity = 1;

    ArrayList<Pipe> pipes;
    Random random = new Random(); // Adding the pipe logic to be random heights

    Timer gameLoop;
    Timer placePipesTimer;


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
        pipes = new ArrayList<Pipe>();

        /*place pipes timer
            TIMER LOGIC: 1000 = 1 second
        
        */ 
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes(); // a timer to call place pipes every 1500ms: 1.5 seconds
            }
        });
        placePipesTimer.start();

        // game timer loop
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    /*
        Because we have many pipes [Line:68]
        We have to add them into an array list by creating a Function.
    */ 
    public void placePipes(){
        /*
            Math.random() gives us a height between 0 - 1
            We multiply it by pipeHeight/2 -> which gives 256
            so between 0 - 256

            SO,
            0 - 128 - (0-256) which means if we get 0 we have - pipeHeight/4 and pipeHeight is 3/4
        */ 
        int randomPipeY = (int) (pipeY - pipeHeight/4 -Math.random()*(pipeHeight/2));

        /*Working on the bottom pipe*/
        int openingSpace = boardHeight/4;

        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        // this is the top of the pipe plus pipe height and add the opening space and add the pipe to the array list
        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y  + pipeHeight + openingSpace;
        pipes.add(bottomPipe);
    }
    

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    // This function is for drawing all the components in the game

    public void draw(Graphics g){
        // System.out.println("test");
        // background
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);

        // bird
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        // pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
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

        // Pipe Mechanics
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX; // each frame we move out pipe -4 pixels to the left
        }

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
