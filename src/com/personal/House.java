package com.personal;

public class House extends Building{
    private int rent;

    public House(Building b, int rent){
        super((Lot)b, b.getType_bu(), b.getHappiness(), b.getWorkers(), b.getCapacity(), b.getSalary());
        this.rent = rent;
    }

    public int getRent() {
        return rent;
    }
}
