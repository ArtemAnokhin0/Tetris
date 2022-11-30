package tetris;

import java.util.ArrayList;

interface Movable {
    boolean mayMove(ArrayList<Coor> newList);

    void rotate();

    void moveLeft();

    void moveRight();

    void moveDown();

    void move(ArrayList<Coor> newList, boolean mayStuck);

    void removeFigure(ArrayList<Coor> shape);

}
