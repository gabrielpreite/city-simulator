package com.personal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Engine {
    private final Config conf = new Config();
    private final int SIZE_X = conf.getsizex();
    private final int SIZE_Y = conf.getsizey();
    private final int BUILD_PERCENT = conf.getbuildp();//todo restore build percent
    private final int ROAD_PERCENT = conf.getroadp();
    private final int ROAD_EXT_PERCENT = conf.getroadextp();

    private Grid grid;
    private Gui gui;
    private DBInterface db = DBInterface.getInstance();

    public Engine() {
        DBInterface.size_x = SIZE_X;
        DBInterface.size_y = SIZE_Y;
        grid = new Grid(SIZE_X, SIZE_Y);
        Random rand = new Random();
        gui = new Gui(SIZE_X, SIZE_Y);

        //init
        db.resetLots();
        db.addRoad(SIZE_X/4, SIZE_X/4*3,
                   SIZE_Y/2, SIZE_Y/2, Road.HOR,
                   SIZE_X/2, SIZE_Y/2);

        while(true){
            gui.refresh();

            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            ArrayList<Road> roads = db.getEligibleRoads();
            /*for(Lot tmp : roads)
                System.out.println(tmp.toString());*/
            if(roads.size()>0){
                Road selR = roads.get(rand.nextInt(roads.size()));
                System.out.println(selR.toString());
                if(rand.nextInt(100) < ROAD_PERCENT){
                    int dir = selR.getDir() == Road.VER ? Road.HOR : Road.VER;
                    int center_x = selR.getCoor_x();
                    int center_y = selR.getCoor_y();
                    int start_x, end_x, start_y, end_y;

                    int mod_x = dir == Road.HOR ? 1 : 0;
                    int mod_y = dir == Road.VER ? 1 : 0;
                    //todo bug: fix adjacent roads and collisions
                    for(int i=1; ; i++){
                        end_x = selR.getCoor_x() + i*mod_x;
                        end_y = selR.getCoor_y() + i*mod_y;
                        Lot l1 = db.getLot(end_x+mod_y, end_y+mod_x);
                        Lot l2 = db.getLot(end_x-mod_y, end_y-mod_x);
                        Lot l3 = db.getLot(end_x+mod_x, end_y+mod_y);
                        if(rand.nextInt(100) > ROAD_EXT_PERCENT || l1.getType_lo() == Lot.ROAD || l2.getType_lo() == Lot.ROAD || l3.getType_lo() > 1){
                            System.out.println("found match at:"+l1.getCoor_x()+", "+l1.getCoor_y()+" or:"+l2.getCoor_x()+", "+l2.getCoor_y());
                            System.out.println(l1.toString());
                            System.out.println(l2.toString());
                            end_x -= mod_x;
                            end_y -= mod_y;
                            System.out.println("reverted to:"+end_x+", "+end_y);
                            System.out.println("break");
                            break;
                        }
                    }
                    for(int i=-1; ; i--) {
                        start_x = selR.getCoor_x() + i * mod_x;
                        start_y = selR.getCoor_y() + i * mod_y;

                        Lot l1 = db.getLot(start_x + mod_y, start_y + mod_x);
                        Lot l2 = db.getLot(start_x - mod_y, start_y - mod_x);
                        Lot l3 = db.getLot(end_x+mod_x, end_y+mod_y);
                        if (rand.nextInt(100) > ROAD_EXT_PERCENT || l1.getType_lo() == Lot.ROAD || l2.getType_lo() == Lot.ROAD || l3.getType_lo() > 1){
                            System.out.println("found match at:"+l1.getCoor_x()+", "+l1.getCoor_y()+" or:"+l2.getCoor_x()+", "+l2.getCoor_y());
                            System.out.println(l1.toString());
                            System.out.println(l2.toString());
                            end_x += mod_x;
                            end_y += mod_y;
                            System.out.println("reverted to:"+end_x+", "+end_y);
                            System.out.println("break");
                            break;
                        }
                    }
                    System.out.println("startx:"+start_x+",endx:"+end_x+",starty:"+start_y+",endy:"+end_y+",dir:"+dir+",centerx:"+center_x+",centery:"+center_y);
                    db.addRoad(start_x, end_x, start_y, end_y, dir, center_x, center_y);
                }
            }

            ArrayList<Lot> buildings = db.getEligibleLots();
            /*for(Lot tmp : buildings)
                System.out.println(tmp.toString());*/
            if(buildings.size()>0){
                Lot selB = buildings.get(rand.nextInt(buildings.size()));
                int bonus = grid.getNeighbours(selB);
                if(rand.nextInt(100) < BUILD_PERCENT*(bonus+1)){
                    selB.setType_lo(Lot.BUILDING);
                    grid.plopBuilding(selB);
                }
            }

            //System.out.println(grid.toString());
        }
    }

    public static void main(String[] args) throws IOException {
	    new Engine();
    }
}
