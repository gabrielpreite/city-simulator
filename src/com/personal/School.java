package com.personal;

public class School extends Building{

    public School(Building b){
        super((Lot)b, b.getType_bu(), b.getHappiness(), b.getWorkers(), b.getCapacity(), b.getSalary());
    }
}
