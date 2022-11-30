package tetris;

import java.util.ArrayList;
import java.util.Arrays;

abstract class Figure implements Movable {
    protected ArrayList<Coor> current;

    Figure(int x, int y) {
        this.current = createNewShape(new Coor(x, y));
    }

    ArrayList<Coor> getCurrent() {
        return current;
    }

    abstract ArrayList<Coor> createNewShape(Coor coor);

    public boolean mayMove(ArrayList<Coor> newList) {
        for (Coor coor : newList) {
            if (coor.x() < 0 || coor.x() >= 10 || coor.y() < 0 || coor.y() >= Tetris.getRows())
                return false;
            if (!GameField.getObj(coor).isEmpty())
                return false;
        }
        return true;
    }

    boolean drawCurrent(ArrayList<Coor> newList) {
        if (mayMove(newList)) {
            for (Coor coor : newList) {
                GameField.getObj(coor).isEmpty(false);
                GameField.getObj(coor).setImg(Images.N1.img);
            }

            return true;
        }

        return false;
    }

    public void rotate() {
        ArrayList<Coor> newList = new ArrayList<>();

        int centerX = current.get(0).x();
        int centerY = current.get(0).y();

        for (Coor coor : current)
            newList.add(new Coor(centerY - coor.y() + centerX, coor.x() - centerX + centerY));

        move(newList, false);
    }

    public void moveLeft() {
        ArrayList<Coor> newList = new ArrayList<>();
        for (Coor coor : current)
            newList.add(new Coor(coor.x() - 1, coor.y()));

        move(newList, false);
    }

    public void moveRight() {
        ArrayList<Coor> newList = new ArrayList<>();
        for (Coor coor : current)
            newList.add(new Coor(coor.x() + 1, coor.y()));

        move(newList, false);
    }

    public void moveDown() {
        ArrayList<Coor> newList = new ArrayList<>();
        for (Coor coor : current)
            newList.add(new Coor(coor.x(), coor.y() + 1));

        move(newList, true);
    }

    public void move(ArrayList<Coor> newList, boolean mayStuck) {
        removeFigure(current);

        if (drawCurrent(newList)) {
            current = newList;
        } else {
            drawCurrent(current);
            if (mayStuck)
                Game.figureStuck();
        }
    }

    public void removeFigure(ArrayList<Coor> shape) {
        for (Coor coor : shape) {
            GameField.getObj(coor).isEmpty(true);
            GameField.getObj(coor).setImg(Images.N0.img);
        }
    }


    static class ShapeI extends Figure {

        ShapeI() {
            super(4, 0);
        }

        public ArrayList<Coor> createNewShape(Coor coor) {
            return new ArrayList<>(Arrays.asList(
                    new Coor(coor.x(), coor.y()),
                    new Coor(coor.x() - 1, coor.y()),
                    new Coor(coor.x() + 1, coor.y()),
                    new Coor(coor.x() + 2, coor.y())));
        }

    }

    static class ShapeJ extends Figure {

        ShapeJ() {
            super(5, 0);
        }

        public ArrayList<Coor> createNewShape(Coor coor) {
            return new ArrayList<>(Arrays.asList(
                    new Coor(coor.x(), coor.y()),
                    new Coor(coor.x(), coor.y() + 1),
                    new Coor(coor.x() - 1, coor.y()),
                    new Coor(coor.x() - 2, coor.y())));
        }

    }

    static class ShapeL extends Figure {

        ShapeL() {
            super(4, 0);
        }

        public ArrayList<Coor> createNewShape(Coor coor) {
            return new ArrayList<>(Arrays.asList(
                    new Coor(coor.x(), coor.y()),
                    new Coor(coor.x(), coor.y() + 1),
                    new Coor(coor.x() + 1, coor.y()),
                    new Coor(coor.x() + 2, coor.y())));
        }

    }

    static class ShapeO extends Figure {

        ShapeO() {
            super(4, 0);
        }

        public ArrayList<Coor> createNewShape(Coor coor) {
            return new ArrayList<>(Arrays.asList(
                    new Coor(coor.x(), coor.y()),
                    new Coor(coor.x(), coor.y() + 1),
                    new Coor(coor.x() + 1, coor.y()),
                    new Coor(coor.x() + 1, coor.y() + 1)));
        }

        @Override
        public void rotate() {
        }

    }

    static class ShapeS extends Figure {

        ShapeS() {
            super(5, 0);
        }

        public ArrayList<Coor> createNewShape(Coor coor) {
            return new ArrayList<>(Arrays.asList(
                    new Coor(coor.x(), coor.y()),
                    new Coor(coor.x() + 1, coor.y()),
                    new Coor(coor.x(), coor.y() + 1),
                    new Coor(coor.x() - 1, coor.y() + 1)));
        }

    }

    static class ShapeT extends Figure {

        ShapeT() {
            super(4, 0);
        }

        public ArrayList<Coor> createNewShape(Coor coor) {
            return new ArrayList<>(Arrays.asList(
                    new Coor(coor.x(), coor.y()),
                    new Coor(coor.x() - 1, coor.y()),
                    new Coor(coor.x(), coor.y() + 1),
                    new Coor(coor.x() + 1, coor.y())
            ));
        }

    }

    static class ShapeZ extends Figure {

        ShapeZ() {
            super(5, 0);
        }

        public ArrayList<Coor> createNewShape(Coor coor) {
            return new ArrayList<>(Arrays.asList(
                    new Coor(coor.x(), coor.y()),
                    new Coor(coor.x() - 1, coor.y()),
                    new Coor(coor.x(), coor.y() + 1),
                    new Coor(coor.x() + 1, coor.y() + 1)));
        }

    }

}
