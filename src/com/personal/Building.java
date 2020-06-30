package com.personal;

public class Building extends Lot{

    public Building(int coor_x, int coor_y){
        super(coor_x, coor_y);
    }

    @Override
    public String toString() {
        return "Building{" +
                "coor_x=" + coor_x +
                ", coor_y=" + coor_y +
                ", type=" + type +
                "} " + super.toString();
    }
}
