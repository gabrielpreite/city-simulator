package com.personal;

public class Leisure extends Building{
    private int type_le;
    private int cost;

    public Leisure(Building b, int type_le, int cost){
        super((Lot)b, b.getType_bu(), b.getHappiness(), b.getWorkers(), b.getCapacity(), b.getSalary());
        this.type_le = type_le;
        this.cost = cost;
    }

    public int getType_le() {
        return type_le;
    }

    public int getCost() {
        return cost;
    }
}
