package com.personal;

public class Lot {
    static final int EMPTY = 0;
    static final int BUILDING = 1;
    static final int ROAD = 2;

    protected int coor_x, coor_y;
    protected int type;

    public Lot(int coor_x, int coor_y) {
        this.coor_x = coor_x;
        this.coor_y = coor_y;
        this.type = EMPTY;
    }

    public int getCoor_x() {
        return coor_x;
    }

    public int getCoor_y() {
        return coor_y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "coor_x=" + coor_x +
                ", coor_y=" + coor_y +
                ", type=" + type +
                '}';
    }
}
