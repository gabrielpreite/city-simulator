package com.personal;

public class Road extends Lot{
    static final int HOR = 0;
    static final int VER = 1;
    static final int CRO = 2; //crossroads

    private int dir;

    public Road(Lot l){
        super(l.coor_x, l.coor_y);
    }

    public Road(int coor_x, int coor_y, int dir){
        super(coor_x, coor_y);
        this.dir = dir;
        this.type = Lot.ROAD;
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
                ", coor_x=" + coor_x +
                ", coor_y=" + coor_y +
                ", type=" + type +
                "} " + super.toString();
    }
}
