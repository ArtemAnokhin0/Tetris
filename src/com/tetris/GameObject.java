package com.tetris;

import java.awt.*;

class GameObject {
    public Coor coor;
    public boolean isEmpty;
    public Image img;

    GameObject(int x, int y, Image img){
        this.coor = new Coor(x,y);
        this.img = img;
        this.isEmpty = (img == Images.N0.img);
    }


}
