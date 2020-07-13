package com.personal;

public class Lot {
    static final int EMPTY = 0;
    static final int BUILDING = 1;
    static final int ROAD = 2;
    static final int ROADSIDE = 3;

    protected int id;
    protected int coor_x, coor_y;
    protected int type_lo;

    public Lot(int id, int coor_x, int coor_y, int type_lo) {
        this.id = id;
        this.coor_x = coor_x;
        this.coor_y = coor_y;
        this.type_lo = type_lo;
    }

    public int getId() { return id; }

    public int getCoor_x() {
        return coor_x;
    }

    public int getCoor_y() {
        return coor_y;
    }

    public int getType_lo() {
        return type_lo;
    }

    public void setType_lo(int type_lo) {
        this.type_lo = type_lo;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "coor_x=" + coor_x +
                ", coor_y=" + coor_y +
                ", type=" + type_lo +
                '}';
    }
}
