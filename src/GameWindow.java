import javax.swing.*;

public class GameWindow {
    private JFrame jFrame;
    public GameWindow(GamePanel panel){
        jFrame = new JFrame();
        jFrame.add(panel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

}
