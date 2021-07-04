package com.tetris;

import java.util.ArrayList;
import java.util.Arrays;

public class ShapeJ extends Figure{

    ShapeJ(){
        this.current = createNewShape(new Coor(5,0));
    }

    public ArrayList<Coor> createNewShape(Coor coor){
        return new ArrayList<>(Arrays.asList(
                new Coor(coor.getX(),coor.getY()),
                new Coor(coor.getX(),coor.getY()+1),
                new Coor(coor.getX()-1,coor.getY()),
                new Coor(coor.getX()-2,coor.getY())  ));
    }

}