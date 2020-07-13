package com.personal;

public class Road extends Lot{
    static final int HOR = 0;
    static final int VER = 1;
    static final int CRO = 2; //crossroads

    private int dir;

    public Road(Lot l, int dir){
        super(l.id, l.coor_x, l.coor_y, Lot.ROAD);
        this.dir = dir;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "Road{" +
                "dir=" + dir +
                ", id=" + id +
                ", coor_x=" + coor_x +
                ", coor_y=" + coor_y +
                ", type_lo=" + type_lo +
                "} " + super.toString();
    }
}
