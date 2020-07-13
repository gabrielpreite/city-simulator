package com.personal;

public class Building extends Lot{
    static final int HOUSE = 0;
    static final int SCHOOL = 1;
    static final int LEISURE = 2;

    private int type_bu;
    private int capacity;
    private int happiness;
    private int workers;
    private int salary;

    public Building(Lot l, int type_bu, int capacity, int happiness, int workers, int salary){
        super(l.id, l.getCoor_x(), l.getCoor_y(), Lot.BUILDING);
        this.type_bu = type_bu;
        this.capacity = capacity;
        this.happiness = happiness;
        this.workers = workers;
        this.salary = salary;
    }

    public int getType_bu() {
        return type_bu;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getWorkers() {
        return workers;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Building{" +
                "coor_x=" + coor_x +
                ", coor_y=" + coor_y +
                ", type=" + type_lo +
                "} " + super.toString();
    }
}
