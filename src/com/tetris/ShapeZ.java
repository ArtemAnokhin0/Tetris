package com.tetris;

import java.util.ArrayList;
import java.util.Arrays;

public class ShapeZ extends Figure{

    ShapeZ(){
        this.current = createNewShape(new Coor(5,0));
    }

    public ArrayList<Coor> createNewShape(Coor coor){
        return new ArrayList<>(Arrays.asList(
                new Coor(coor.getX(),coor.getY()),
                new Coor(coor.getX()-1,coor.getY()),
                new Coor(coor.getX(),coor.getY()+1),
                new Coor(coor.getX()+1,coor.getY()+1)  ));
    }

}