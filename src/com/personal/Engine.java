package com.personal;

import java.util.ArrayList;
import java.util.Random;

public class Engine {
    private final int SIZE_X = 20;
    private final int SIZE_Y = 20;
    private final int BUILD_PERCENT = 2;
    private final int ROAD_PERCENT = 5;

    private Grid grid;

    public Engine(){
        grid = new Grid(SIZE_X, SIZE_Y);
        Random r = new Random();

        while(true){
            try{
                Thread.sleep(10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            ArrayList<Lot> roads = grid.getEligibleRoads();
            /*for(Lot tmp : roads)
                System.out.println(tmp.toString());*/
            for(Lot l : roads){
                if(r.nextInt(100) < ROAD_PERCENT){
                    Road road = (Road)l;
                    int dir = road.getDir() == Road.VER ? Road.HOR : Road.VER;
                    road.setDir(Road.CRO);
                    grid.plopRoad(road, dir);
                }
            }

            ArrayList<Lot> buildings = grid.getEligibleBuildings();
            /*for(Lot tmp : buildings)
                System.out.println(tmp.toString());*/
            for(Lot l : buildings){
                if(r.nextInt(100/BUILD_PERCENT) == 0){
                    l = new Building(l.getCoor_x(), l.getCoor_y());
                    l.setType(Lot.BUILDING);
                    grid.plopBuilding(l);
                }
            }

            System.out.println(grid.toString());
        }
    }

    public static void main(String[] args) {
	    new Engine();
    }
}
