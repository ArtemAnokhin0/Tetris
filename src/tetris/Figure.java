package tetris;

import java.util.ArrayList;
import java.util.Arrays;

abstract class Figure implements Movable{
    ArrayList<Coor> current;

    public boolean canMove(ArrayList<Coor> newList){
        for(Coor coor: newList){
            if(coor.x()<0 || coor.x()>=10 || coor.y()<0 || coor.y()>=Tetris.getRows())
                return false;
            if (!GameField.get(coor).isEmpty)
                return false;
        }
        return true;
    }

    boolean drawCurrent(ArrayList<Coor> newList){
        if (canMove(newList)){
            for(Coor coor: newList) {
                GameField.get(coor).isEmpty = false;
                GameField.get(coor).img = Images.N1.img;
            }
            return true;
        }
        else
            return false;
    }

    public void rotate() {
        ArrayList<Coor> newList = new ArrayList<>();

        int centerX = getCurrent().get(0).x();
        int centerY = getCurrent().get(0).y();

        for(Coor coor: getCurrent())
            newList.add(new Coor(centerY-coor.y() +centerX,coor.x()-centerX +centerY));

        move(newList, false);
    }

    public void moveLeft() {
        ArrayList<Coor> newList = new ArrayList<>();
        for(Coor coor: getCurrent())
            newList.add(new Coor(coor.x()-1, coor.y()));

        move(newList, false);
    }

    public void moveRight() {
        ArrayList<Coor> newList = new ArrayList<>();
        for(Coor coor: getCurrent())
            newList.add(new Coor(coor.x()+1, coor.y()));

        move(newList, false);
    }

    public void moveDown(){
        ArrayList<Coor> newList = new ArrayList<>();
        for(Coor coor: getCurrent())
            newList.add(new Coor(coor.x(), coor.y()+1));

        move(newList, true);
    }

    public void move(ArrayList<Coor> newList, boolean canStuck){
        removeFigure(getCurrent());

        if(drawCurrent(newList)){
            setCurrent(newList);
        }else{
            drawCurrent(getCurrent());
            if(canStuck)
                Game.figureStuck();
        }
    }

    public void removeFigure(ArrayList<Coor> shape){
        for(Coor coor: shape) {
            GameField.get(coor).isEmpty = true;
            GameField.get(coor).img = Images.N0.img;
        }
    }

    ArrayList<Coor> getCurrent(){
        return current;
    }

    void setCurrent(ArrayList<Coor> newList){
        current = newList;
    }

}

class ShapeI extends Figure{

    ShapeI(){
        this.current = createNewShape(new Coor(4,0));
    }

    public ArrayList<Coor> createNewShape(Coor coor){
        return new ArrayList<>(Arrays.asList(
                new Coor(coor.x(),coor.y()),
                new Coor(coor.x()-1,coor.y()),
                new Coor(coor.x()+1,coor.y()),
                new Coor(coor.x()+2,coor.y())  ));
    }

}

class ShapeJ extends Figure{

    ShapeJ(){
        this.current = createNewShape(new Coor(5,0));
    }

    public ArrayList<Coor> createNewShape(Coor coor){
        return new ArrayList<>(Arrays.asList(
                new Coor(coor.x(),coor.y()),
                new Coor(coor.x(),coor.y()+1),
                new Coor(coor.x()-1,coor.y()),
                new Coor(coor.x()-2,coor.y())  ));
    }

}

class ShapeL extends Figure{

    ShapeL(){
        this.current = createNewShape(new Coor(4,0));
    }

    public ArrayList<Coor> createNewShape(Coor coor){
        return new ArrayList<>(Arrays.asList(
                new Coor(coor.x(),coor.y()),
                new Coor(coor.x(),coor.y()+1),
                new Coor(coor.x()+1,coor.y()),
                new Coor(coor.x()+2,coor.y())  ));
    }

}

class ShapeO extends Figure{

    ShapeO(){
        this.current = createNewShape(new Coor(4,0));
    }

    public ArrayList<Coor> createNewShape(Coor coor){
        return new ArrayList<>(Arrays.asList(
                new Coor(coor.x(),coor.y()),
                new Coor(coor.x(),coor.y()+1),
                new Coor(coor.x()+1,coor.y()),
                new Coor(coor.x()+1,coor.y()+1)  ));
    }

    @Override
    public void rotate(){ }

}

class ShapeS extends Figure{

    ShapeS(){
        this.current = createNewShape(new Coor(5,0));
    }

    public ArrayList<Coor> createNewShape(Coor coor){
        return new ArrayList<>(Arrays.asList(
                new Coor(coor.x(),coor.y()),
                new Coor(coor.x()+1,coor.y()),
                new Coor(coor.x(),coor.y()+1),
                new Coor(coor.x()-1,coor.y()+1)  ));
    }

}

class ShapeT extends Figure{

    ShapeT(){
        this.current = createNewShape(new Coor(4,0));
    }

    public ArrayList<Coor> createNewShape(Coor coor){
        return new ArrayList<>(Arrays.asList(
                new Coor(coor.x(),coor.y()),
                new Coor(coor.x()-1,coor.y()),
                new Coor(coor.x() ,coor.y()+1),
                new Coor(coor.x()+1,coor.y())  ));
    }

}

class ShapeZ extends Figure{

    ShapeZ(){
        this.current = createNewShape(new Coor(5,0));
    }

    public ArrayList<Coor> createNewShape(Coor coor){
        return new ArrayList<>(Arrays.asList(
                new Coor(coor.x(),coor.y()),
                new Coor(coor.x()-1,coor.y()),
                new Coor(coor.x(),coor.y()+1),
                new Coor(coor.x()+1,coor.y()+1)  ));
    }

}