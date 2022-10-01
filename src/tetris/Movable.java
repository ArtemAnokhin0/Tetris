package tetris;

import java.util.ArrayList;

interface Movable {
    boolean canMove(ArrayList<Coor> newList);
    void rotate();

    void moveLeft() ;
    void moveRight() ;
    void moveDown();
    void move(ArrayList<Coor> newList, boolean canStuck);

    void removeFigure(ArrayList<Coor> shape);

}
