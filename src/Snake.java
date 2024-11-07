import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final int segmentWidth = 20;
    private final int segmentHeight = 20;
    private int deltaX = 20;
    private int deltaY = 0;
    private List<Point> snakeSegments;

    public void init(){
        snakeSegments = new ArrayList<>();
        for(int i=0;i<10;i++){
            snakeSegments.add(new Point(i*deltaX+segmentWidth+100,100));
        }
    }

    public void render(Graphics g){
        move();
        g.setColor(Color.GREEN);
        for(Point p: snakeSegments)
            g.fillRect(
                    p.x,
                    p.y,
                    segmentWidth,
                    segmentHeight
            );
    }

    private void move(){
        addSegment();
        snakeSegments.removeFirst();
    }

    public void addSegment(){
        Point p = new Point(snakeSegments.getLast().x,snakeSegments.getLast().y);
        snakeSegments.add(new Point(p.x+deltaX, p.y+deltaY));
    }

    public void changeDirection(int x, int y) {
        // if it goes right it can't change direction to left, same with up and down
        if (x != 0 && deltaX == 0 || y != 0 && deltaY == 0) {
            deltaX = x;
            deltaY = y;
        }
    }

    public Point getHead(){
        return snakeSegments.getLast();
    }

    public List<Point> getBody(){
        List<Point> snakeBody = new ArrayList<>(snakeSegments);
        snakeBody.removeLast();
        snakeBody.removeLast();
        return snakeBody;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public List<Point> getSnakeSegments() {
        return snakeSegments;
    }
}
