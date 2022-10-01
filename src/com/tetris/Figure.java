package com.tetris;

import java.util.ArrayList;

abstract class Figure implements Movable{
    ArrayList<Coor> current;

    public boolean canMove(ArrayList<Coor> newList){
        for(Coor coor: newList){
            if(coor.getX()<0 || coor.getX()>=10 || coor.getY()<0 || coor.getY()>=Tetris.getRows())
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

        int centerX = getCurrent().get(0).getX();
        int centerY = getCurrent().get(0).getY();

        for(Coor coor: getCurrent())
            newList.add(new Coor(centerY-coor.getY() +centerX,coor.getX()-centerX +centerY));

        move(newList, false);
    }

    public void moveLeft() {
        ArrayList<Coor> newList = new ArrayList<>();
        for(Coor coor: getCurrent())
            newList.add(new Coor(coor.getX()-1, coor.getY()));

        move(newList, false);
    }

    public void moveRight() {
        ArrayList<Coor> newList = new ArrayList<>();
        for(Coor coor: getCurrent())
            newList.add(new Coor(coor.getX()+1, coor.getY()));

        move(newList, false);
    }

    public void moveDown(){
        ArrayList<Coor> newList = new ArrayList<>();
        for(Coor coor: getCurrent())
            newList.add(new Coor(coor.getX(), coor.getY()+1));

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
