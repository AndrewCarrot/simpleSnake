public class Game implements Runnable{
    private GamePanel gamePanel;
    private Thread thread;
    private final int FPS = 15;
    private final double frameRate = 1000000000d / FPS;
    private GameWindow gameWindow;
    private boolean gameOver = false;

    public Game(){
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocusInWindow();
        startGameLoop();
    }

    public void startGameLoop(){
        thread = new Thread(this);
        thread.start();
    }

    public void stopGame(){
        thread.interrupt();
    }

    public void resumeGame(){
        startGameLoop();
    }

    public Thread getThread(){
        return thread;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    @Override
    public void run() {
        int frames = 0;
        long lastCheckMillis = System.currentTimeMillis();
        long nowMillis;
        long lastFrameNano = System.nanoTime();
        long nowNano;


        while(!thread.isInterrupted()){

            nowMillis = System.currentTimeMillis();
            if(nowMillis - lastCheckMillis >= 1000){
                System.out.println("FPS: " + frames);
                frames = 0;
                lastCheckMillis = nowMillis;
            }

            nowNano = System.nanoTime();
            if(nowNano - lastFrameNano >= frameRate){
                frames++;
                lastFrameNano = nowNano;
                gamePanel.repaint();
            }


        }
    }
}
