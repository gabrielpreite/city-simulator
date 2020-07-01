package com.personal;

import java.util.ArrayList;
import java.util.Random;

public class Engine {
    private final int SIZE_X = 20;
    private final int SIZE_Y = 20;
    private final int BUILD_PERCENT = 15;
    private final int ROAD_PERCENT = 5;

    private Grid grid;
    //privte Gui gui;

    public Engine(){
        grid = new Grid(SIZE_X, SIZE_Y);
        Random rand = new Random();

        //gui = new Gui(grid);

        while(true){
            try{
                Thread.sleep(10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            ArrayList<Road> roads = grid.getEligibleRoads();
            /*for(Lot tmp : roads)
                System.out.println(tmp.toString());*/
            if(roads.size()>0){
                Road selR = roads.get(rand.nextInt(roads.size()));
                if(rand.nextInt(100) < ROAD_PERCENT){
                    int dir = selR.getDir() == Road.VER ? Road.HOR : Road.VER;
                    selR.setDir(Road.CRO);
                    grid.plopRoad(selR, dir);
                }
            }

            ArrayList<Lot> buildings = grid.getEligibleBuildings();
            /*for(Lot tmp : buildings)
                System.out.println(tmp.toString());*/
            if(buildings.size()>0){
                Lot selB = buildings.get(rand.nextInt(buildings.size()));
                int bonus = grid.getNeighbours(selB);
                if(rand.nextInt(100) < BUILD_PERCENT*(bonus+1)){
                    selB.setType(Lot.BUILDING);
                    grid.plopBuilding(selB);
                }
            }

            System.out.println(grid.toString());
        }
    }

    public static void main(String[] args) {
	    new Engine();
    }
}
