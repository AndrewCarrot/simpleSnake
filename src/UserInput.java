import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class UserInput implements KeyListener {
    private GamePanel gamePanel;
    public UserInput(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == VK_SPACE){
            if(!gamePanel.getGame().getThread().isInterrupted())
                gamePanel.getGame().stopGame();
            else if(gamePanel.getGame().isGameOver())
                gamePanel.resetGame();
            else
                gamePanel.getGame().resumeGame();

        }




        switch(e.getKeyCode()){
            case VK_LEFT:
                gamePanel.getSnake().changeDirection(-20,0);
                break;
            case VK_UP:
                gamePanel.getSnake().changeDirection(0,-20);
                break;
            case VK_RIGHT:
                gamePanel.getSnake().changeDirection(20,0);
                break;
            case VK_DOWN:
                gamePanel.getSnake().changeDirection(0,20);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
