package com.tetris;

import java.util.ArrayList;
import java.util.Arrays;

public class ShapeO extends Figure{

    ShapeO(){
        this.current = createNewShape(new Coor(4,0));
    }

    public ArrayList<Coor> createNewShape(Coor coor){
        return new ArrayList<>(Arrays.asList(
                new Coor(coor.getX(),coor.getY()),
                new Coor(coor.getX(),coor.getY()+1),
                new Coor(coor.getX()+1,coor.getY()),
                new Coor(coor.getX()+1,coor.getY()+1)  ));
    }

    @Override
    public void rotate(){ }

}