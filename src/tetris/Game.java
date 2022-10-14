package tetris;

import java.util.Random;

class Loop implements Runnable{
    @Override
    public void run() {
        while (!Game.isStopped()) {
            Game.getCurrentFigure().moveDown();
            try{Thread.sleep(300);}catch(Exception ignored){}
        }
    }
}

class FigureStuck implements Runnable{
    @Override
    public void run() {
        for(int y=0; y<Tetris.getRows(); y++){
            boolean rowIsFull = true;
            for (int x=0; x<10; x++){
                if(GameField.getObj(new Coor(x,y)).getIsEmpty())
                    rowIsFull = false;
            }
            if(rowIsFull)
                Game.removeRow(y);
        }
        Game.createNewFigure();
    }
}

class Rotate implements Runnable{
    @Override
    public void run() {
        if(Game.isStopped())
            return;
        Game.getCurrentFigure().rotate();
    }
}



public class Game {
    private static volatile boolean isGameOver;
    private static volatile boolean isPaused = true;
    private static int score = 0;
    private static int bestScore;

    private static Figure currentFigure;
    private static Figure futureFigure;

    static boolean isStopped(){
        return (isGameOver || isPaused);
    }

    private static void restart(){
        score=0;
        GameField.createGameField();
        isGameOver = false;
        isPaused = true;
        start();
    }

    static String getMsg(){
        if(isGameOver)
            return "GAME OVER       Score: "+score+"   Best: "+bestScore+"             Right button => Restart";
        else if(isPaused)
            return "Left button => Continue                  Right button => Restart";
        else
            return "Left button => Pause                     Score: "+score;
    }

    static void upArrowPressed(){
        Thread thread = new Thread(new Rotate());
        thread.start();
        try {thread.join();} catch (InterruptedException ignored) {}
    }

    static void leftArrowPressed(){
        if(isStopped())
            return;
        currentFigure.moveLeft();
    }

    static void rightArrowPressed(){
        if(isStopped())
            return;
        currentFigure.moveRight();
    }

    static void downArrowPressed(){
        if(isStopped())
            return;
        currentFigure.moveDown();
    }

    static void leftButtonPressed(){
        if(isPaused) {
            isPaused = false;
            createLoop();
        }else
            isPaused = true;
    }

    static void rightButtonPressed(){
        if(isStopped())
            restart();
    }

    static void start(){
        isGameOver = false;
        currentFigure = createFutureFigure();
        currentFigure.drawCurrent(currentFigure.getCurrent());
        futureFigure = createFutureFigure();
        drawFuture();
        createLoop();
    }

    private static void GameOver() {
        bestScore= Math.max(score, bestScore);
        isGameOver = true;
    }

    private static void createLoop(){
        Thread thread = new Thread(new Loop());
        thread.start();
    }

    static void createNewFigure(){
        if(!isGameOver) {
            setCurrentFigure();
            if (currentFigure.drawCurrent(currentFigure.getCurrent())){
                futureFigure = createFutureFigure();
                clearFuture();
                drawFuture();
                score+=4;
            }else
                GameOver();
        }
    }

    private static Figure createFutureFigure(){
        Random random = new Random();
        return switch (random.nextInt(7)) {
            case 0 -> new ShapeO();
            case 1 -> new ShapeI();
            case 2 -> new ShapeZ();
            case 3 -> new ShapeT();
            case 4 -> new ShapeL();
            case 5 -> new ShapeS();
            default -> new ShapeJ();
        };
    }

    private static void setCurrentFigure(){
        currentFigure = futureFigure;
    }

    static Figure getCurrentFigure(){
        return currentFigure;
    }

    private static void drawFuture() {
        for(Coor coor: futureFigure.getCurrent())
            GameField.getObj(new Coor(coor.x()+8,coor.y()+4)).setImg(Images.N1.img);

    }

    private static void clearFuture() {
        for(int y=4; y<6; y++)
            for(int x=11; x<15; x++)
                GameField.getObj(new Coor(x,y)).setImg(Images.N0.img);
    }

    static void figureStuck(){
        Thread thread = new Thread(new FigureStuck());
        thread.start();
        try { thread.join(); } catch (InterruptedException ignored) { }
    }

    static void removeRow(int yRow){
        for(int x=0; x<10;x++){
            GameField.getObj(new Coor(x,yRow)).setIsEmpty(true);
            GameField.getObj(new Coor(x,yRow)).setImg(Images.N0.img);
        }

        for(int y=yRow; y>0; y--)
            for(int x=0; x<10; x++) {
                GameField.set(x,y,GameField.getObj(new Coor(x,y-1)));
            }
    }

}
