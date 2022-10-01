package tetris;

import java.awt.*;

public class GameField {
    private static GameObject[][] gameField = new GameObject[Tetris.getRows()][Tetris.getCols()];

    static void createGameField(){
        for(int y=0; y<Tetris.getRows(); y++)
            for(int x=0; x<10; x++)
                gameField[y][x] = new GameObject(x * Tetris.getImageSize(), y * Tetris.getImageSize(), Images.N0.img);

        for(int y=0; y<Tetris.getRows(); y++)
            for(int x=10; x<Tetris.getCols(); x++)
                gameField[y][x] = new GameObject(x * Tetris.getImageSize(), y * Tetris.getImageSize(), Images.NONE.img);

        for(int y=4; y<6; y++)
            for(int x=11; x<15; x++) {
                gameField[y][x].isEmpty = true;
                gameField[y][x].img = Images.N0.img;
            }
    }

    static GameObject get(Coor coor){
        return gameField[coor.y()][coor.x()];
    }

    static void set(int x, int y, GameObject gameObject){
        gameField[y][x] = new GameObject(gameObject.coor.x(), gameObject.coor.y(), gameObject.img);
    }

}

record Coor(int x, int y) {

}

class GameObject {
    public Coor coor;
    public boolean isEmpty;
    public Image img;

    GameObject(int x, int y, Image img){
        this.coor = new Coor(x,y);
        this.img = img;
        this.isEmpty = (img == Images.N0.img);
    }

}