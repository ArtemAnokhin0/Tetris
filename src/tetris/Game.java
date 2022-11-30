package tetris;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {
    private static volatile boolean isGameOver;
    private static volatile boolean isPaused = true;
    private static final AtomicInteger score = new AtomicInteger();
    private static final AtomicInteger speed = new AtomicInteger(300);
    private static int bestScore;

    private static Figure currentFigure;
    private static Figure futureFigure;

    static boolean isStopped() {
        return (isGameOver || isPaused);
    }

    static int getSpeed() {
        return speed.get();
    }

    private static void restart() {
        score.set(0);
        speed.set(300);
        GameField.createGameField();
        isGameOver = false;
        isPaused = true;
        start();
    }

    static String getMsg() {
        if (isGameOver)
            return "GAME OVER       Score: " + score + "   Best: " + bestScore + "        Right button => Restart";
        else if (isPaused)
            return "Left button => Continue                  Right button => Restart";
        else
            return "Left button => Pause                     Score: " + score;
    }

    static void upArrowPressed() {
        Thread thread = new Thread(() -> {
            if (Game.isStopped())
                return;
            Game.getCurrentFigure().rotate();
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ignored) {
        }
    }

    static void leftArrowPressed() {
        if (isStopped())
            return;
        
        currentFigure.moveLeft();
    }

    static void rightArrowPressed() {
        if (isStopped())
            return;
        
        currentFigure.moveRight();
    }

    static void downArrowPressed() {
        if (isStopped())
            return;
        
        currentFigure.moveDown();
    }

    static void leftButtonPressed() {
        if (isPaused) {
            isPaused = false;
            createLoop();
        } else
            isPaused = true;
    }

    static void rightButtonPressed() {
        if (isStopped())
            restart();
    }

    static void start() {
        isGameOver = false;
        currentFigure = createFutureFigure();
        currentFigure.drawCurrent(currentFigure.getCurrent());
        futureFigure = createFutureFigure();
        drawFuture();
        createLoop();
    }

    private static void GameOver() {
        bestScore = Math.max(score.get(), bestScore);
        isGameOver = true;
    }

    private static void createLoop() {
        new Thread(() -> {
            while (!Game.isStopped()) {
                Game.getCurrentFigure().moveDown();
                try {
                    Thread.sleep(Game.getSpeed());
                } catch (Exception ignored) {
                }
            }
        }).start();
    }

    static void createNewFigure() {
        if (!isGameOver) {
            setCurrentFigure();
            if (currentFigure.drawCurrent(currentFigure.getCurrent())) {
                futureFigure = createFutureFigure();
                clearFuture();
                drawFuture();
            } else
                GameOver();
        }
    }

    private static Figure createFutureFigure() {
        Random random = new Random();
        return switch (random.nextInt(7)) {
            case 0 -> new Figure.ShapeO();
            case 1 -> new Figure.ShapeI();
            case 2 -> new Figure.ShapeZ();
            case 3 -> new Figure.ShapeT();
            case 4 -> new Figure.ShapeL();
            case 5 -> new Figure.ShapeS();
            default -> new Figure.ShapeJ();
        };
    }

    private static void setCurrentFigure() {
        currentFigure = futureFigure;
    }

    static synchronized Figure getCurrentFigure() {
        return currentFigure;
    }

    private static void drawFuture() {
        for (Coor coor : futureFigure.getCurrent())
            GameField.getObj(new Coor(coor.x() + 8, coor.y() + 4)).setImg(Images.N1.img);

    }

    private static void clearFuture() {
        for (int y = 4; y < 6; y++)
            for (int x = 11; x < 15; x++)
                GameField.getObj(new Coor(x, y)).setImg(Images.N0.img);
    }

    static void figureStuck() {
        Thread thread = new Thread(() -> {
            for (int y = 0; y < Tetris.getRows(); y++) {
                boolean rowIsFull = true;
                for (int x = 0; x < 10; x++) {
                    if (GameField.getObj(new Coor(x, y)).isEmpty())
                        rowIsFull = false;
                }
                if (rowIsFull)
                    Game.removeRow(y);
            }
            Game.createNewFigure();
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ignored) {
        }
    }

    static void removeRow(int yRow) {
        for (int x = 0; x < 10; x++) {
            GameField.getObj(new Coor(x, yRow)).isEmpty(true);
            GameField.getObj(new Coor(x, yRow)).setImg(Images.N0.img);
        }

        score.addAndGet(10);
        
        if (speed.get() >= 10) 
            speed.addAndGet(-10);

        for (int y = yRow; y > 0; y--) {
            for (int x = 0; x < 10; x++) 
                GameField.set(x, y, GameField.getObj(new Coor(x, y - 1)));
        }
    }

}
