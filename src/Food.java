import java.awt.*;

public class Food {

    private Point p = new Point();

    public Food(Point p){
        this.p.move(p.x, p.y);
    }

    public void render(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(p.x,p.y,20,20);
    }

   public void setPosition(Point p){
        this.p.x = p.x;
        this.p.y = p.y;
   }

    public Point getP() {
        return p;
    }

}
