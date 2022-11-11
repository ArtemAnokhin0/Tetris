package tetris;

import java.awt.*;

public class GameField {
    private static final GameObj[][] gameField = new GameObj[Tetris.getRows()][Tetris.getCols()];

    static void createGameField(){
        for(int y=0; y<Tetris.getRows(); y++)
            for(int x=0; x<10; x++)
                gameField[y][x] = new GameObj(x * Tetris.getImageSize(), y * Tetris.getImageSize(), Images.N0.img);

        for(int y=0; y<Tetris.getRows(); y++)
            for(int x=10; x<Tetris.getCols(); x++)
                gameField[y][x] = new GameObj(x * Tetris.getImageSize(), y * Tetris.getImageSize(), Images.NONE.img);

        for(int y=4; y<6; y++)
            for(int x=11; x<15; x++) {
                gameField[y][x].setIsEmpty(true);
                gameField[y][x].setImg(Images.N0.img);
            }
    }

    static GameObj getObj(Coor coor){
        return gameField[coor.y()][coor.x()];
    }

    static void set(int x, int y, GameObj gameObj){
        gameField[y][x] = new GameObj(gameObj.getCoor().x(), gameObj.getCoor().y(), gameObj.getImg());
    }

    static class GameObj {
        private final Coor coor;
        private boolean isEmpty;
        private Image img;

        GameObj(int x, int y, Image img){
            this.coor = new Coor(x,y);
            this.img = img;
            this.isEmpty = (img == Images.N0.img);
        }

        Coor getCoor() {return coor;}

        void setIsEmpty(boolean isEmpty){this.isEmpty = isEmpty;}
        boolean getIsEmpty(){return isEmpty;}

        void setImg(Image img){this.img = img;}
        Image getImg(){return img;}

    }

}

record Coor(int x, int y) {}
