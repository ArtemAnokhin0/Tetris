package com.tetris;

import java.util.ArrayList;
import java.util.Arrays;

public class ShapeI extends Figure{

    ShapeI(){
        this.current = createNewShape(new Coor(4,0));
    }

    public ArrayList<Coor> createNewShape(Coor coor){
        return new ArrayList<>(Arrays.asList(
                new Coor(coor.getX(),coor.getY()),
                new Coor(coor.getX()-1,coor.getY()),
                new Coor(coor.getX()+1,coor.getY()),
                new Coor(coor.getX()+2,coor.getY())  ));
    }

}
