import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {

    private Game game;
    public static final Dimension DIMENSION = new Dimension(600,600);
    public static final List<Point> GAME_GRID = new ArrayList<>();
    private Food food;
    private Point snakeHead;
    private int score = 0;

    private Font gameOverFont=new Font("TimesRoman",Font.BOLD,50);
    private Font scoreFont = new Font("TimesRoman",Font.ITALIC, 20);


    static {
        for(int row=0; row<30; row++){
            for(int col=0; col<30; col++){
                GAME_GRID.add(new Point(col*20,row*20));
            }
        }
    }

    private Snake snake = new Snake();

    public GamePanel(Game game){
        this.game = game;
        setPreferredSize(DIMENSION);
        setBackground(Color.BLACK);
        addKeyListener(new UserInput(this));
        snake.init();
        food = new Food(GAME_GRID.get((int)(Math.random()*900)));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if(game.isGameOver()) {
            g.setColor(Color.red);
            g.setFont(gameOverFont);
            g.drawString("Game Over", 150, 150);
        }

        g.setColor(Color.RED);
        g.setFont(scoreFont);
        g.drawString("Score: " + score, 20,60);

        revalidate();
        snake.render(g);
        food.render(g);
        detectCollision();
    }

    public Snake getSnake(){
        return snake;
    }
    public Food getFood(){
        return food;
    }

    public Game getGame() {
        return game;
    }

    public void detectCollision(){
        snakeHead = snake.getHead();

        if(ateFood()){
            score++;
            food.setPosition(generateFoodPosition());
            snake.addSegment();
        }
        if (hitWall() || hitSelf()) {
            game.setGameOver(true);
            repaint();
            game.stopGame();
        }


    }

    private boolean hitSelf() {
        for(Point p:snake.getBody()){
            if(getCollisionBetweenTwoRectangles(snakeHead,p,20))
                return true;
        }
        return false;
    }

    public boolean hitWall(){
        return snakeHead.x + 20 > DIMENSION.getWidth() ||
                snakeHead.x < 0 ||
                snakeHead.y + 20 > DIMENSION.getHeight() ||
                snakeHead.y < 0;
    }

    public boolean ateFood(){
        return getCollisionBetweenTwoRectangles(snakeHead,food.getP(),20);
    }

    public boolean getCollisionBetweenTwoRectangles(Point p1, Point p2, int offset){
        return p1.x+offset > p2.x &&
                p1.x < p2.x + offset &&
                p1.y + offset > p2.y &&
                p1.y < p2.y + offset;
    }

    public void drawGrid(Graphics g){
        g.setColor(Color.GRAY);
        for(int i=1; i<30;i++){
            g.drawLine(0,i*20,DIMENSION.width,i*20);
            g.drawLine(i*20,0,i*20,DIMENSION.height);
        }
    }

    public void resetGame(){
        snake.setDeltaX(20);
        snake.setDeltaY(0);
        snake.init();
        game.setGameOver(false);
        score = 0;
        food.setPosition(generateFoodPosition());
        game.resumeGame();
    }


    public Point generateFoodPosition(){
        List<Point> emptySpace = GAME_GRID;
        emptySpace.removeAll(snake.getSnakeSegments());
        return emptySpace.get((int)(Math.random()*emptySpace.size()-1));
    }

}
