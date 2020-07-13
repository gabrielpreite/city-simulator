package com.personal;

import java.util.ArrayList;
import java.util.Random;

public class Grid {
    private final int ROAD_EXT_PERCENT = 90;

    private int size_x, size_y;
    private DBInterface db = DBInterface.getInstance();
    public ArrayList< ArrayList<Lot> > map;

    public Grid(int size_x, int size_y){
        this.size_x = size_x;
        this.size_y = size_y;


    }

    public ArrayList<Lot> getEligibleBuildings(){
        ArrayList<Lot> eli = new ArrayList<Lot>();
        for(int i=0; i<map.size(); i++){
            for(int j=0; j<map.get(0).size(); j++){
                Lot sel = map.get(i).get(j);
                if(sel.getType_lo() == Lot.EMPTY){
                    Boolean roadside = false;

                    try{if(map.get(i+1).get(j).getType_lo() == Lot.ROAD){roadside = true;}}
                    catch(Exception e){}

                    try{if(map.get(i-1).get(j).getType_lo() == Lot.ROAD){roadside = true;}}
                    catch(Exception e){}

                    try{if(map.get(i).get(j+1).getType_lo() == Lot.ROAD){roadside = true;}}
                    catch(Exception e){}

                    try{if(map.get(i).get(j-1).getType_lo() == Lot.ROAD){roadside = true;}}
                    catch(Exception e){}

                    if(roadside)
                        eli.add(sel);
                }
            }
        }
        return eli;
    }

    public ArrayList<Road> getEligibleRoads(){
        ArrayList<Road> eli = new ArrayList<Road>();
        for(int i=0; i<map.size(); i++) {
            for (int j = 0; j < map.get(0).size(); j++) {
                Lot l = map.get(i).get(j);
                if(l.getType_lo() == Lot.ROAD){
                    Road r = (Road)l;
                    //System.out.println(r.toString());
                    if(r.getDir() != Road.CRO)
                        eli.add(r);
                }
            }
        }
        return eli;
    }

    /*public void plopRoad(Road road, int dir){
        Random r = new Random();
        int x = road.getCoor_x();
        int y = road.getCoor_y();

        if(dir == Road.VER){
            //System.out.println(map.get(x).get(y+1).getType());
            //System.out.println(x+","+y);
            try{
                for(int i=1;;i++){
                    int div = 1;
                    if(map.get(x+i).get(y).getType_lo() == Lot.BUILDING)
                            div = 2;
                    if(r.nextInt(100) < ROAD_EXT_PERCENT/div){
                        Road ro = new Road(map.get(x+i).get(y));
                        if(map.get(x+i).get(y) instanceof Road)
                            ro = (Road)map.get(x+i).get(y);
                        ro.setType_lo(Lot.ROAD);
                        ro.setDir(Road.VER);
                        if(!adjacentRoads(ro))
                            break;
                        map.get(x+i).set(y, ro);
                    }else{break;}
                }
            }catch(Exception e){}
            try{
                for(int i=-1; map.get(x+i).get(y).getType_lo() == Lot.EMPTY;i--){
                    int div = 1;
                    if(map.get(x+i).get(y).getType_lo() == Lot.BUILDING)
                        div = 2;
                    if(r.nextInt(100) < ROAD_EXT_PERCENT/div){
                        Road ro = new Road(map.get(x+i).get(y));
                        if(map.get(x+i).get(y) instanceof Road)
                            ro = (Road)map.get(x+i).get(y);
                        ro.setType_lo(Lot.ROAD);
                        ro.setDir(Road.VER);
                        if(!adjacentRoads(ro))
                            break;
                        map.get(x+i).set(y, ro);
                    }else{break;}
                }
            }catch(Exception e){}
        }else{
            try{
                for(int i=1; map.get(x).get(y+i).getType_lo() == Lot.EMPTY;i++){
                    int div = 1;
                    if(map.get(x+i).get(y).getType_lo() == Lot.BUILDING)
                        div = 2;
                    if(r.nextInt(100) < ROAD_EXT_PERCENT/div){
                        Road ro = new Road(map.get(x).get(y+i));
                        if(map.get(x).get(y+i) instanceof Road)
                            ro = (Road)map.get(x).get(y+i);
                        ro.setType_lo(Lot.ROAD);
                        ro.setDir(Road.HOR);
                        if(!adjacentRoads(ro))
                            break;
                        map.get(x).set(y+i, ro);
                    }else{break;}
                }
            }catch(Exception e){}
            try{
                for(int i=-1; map.get(x).get(y+i).getType_lo() == Lot.EMPTY;i--){
                    int div = 1;
                    if(map.get(x+i).get(y).getType_lo() == Lot.BUILDING)
                        div = 2;
                    if(r.nextInt(100) < ROAD_EXT_PERCENT/div){
                        Road ro = new Road(map.get(x).get(y+i));
                        if(map.get(x).get(y+i) instanceof Road)
                            ro = (Road)map.get(x).get(y+i);
                        ro.setType_lo(Lot.ROAD);
                        ro.setDir(Road.HOR);
                        if(!adjacentRoads(ro))
                            break;
                        map.get(x).set(y+i, ro);
                    }else{break;}
                }
            }catch(Exception e){}
        }
    }*/

    public void plopBuilding(Lot l){
        map.get(l.getCoor_x()).set(l.getCoor_y(), l);
    }

    private boolean adjacentRoads(Road r){
        if(r.getDir() == Road.VER){
            try {
                if (map.get(r.getCoor_x()).get(r.coor_y+1).getType_lo() == Lot.ROAD ||
                        map.get(r.getCoor_x()).get(r.coor_y-1).getType_lo() == Lot.ROAD)
                    return false;
            }catch(Exception e){}
        }else{
            try {
                if (map.get(r.getCoor_x()+1).get(r.coor_y).getType_lo() == Lot.ROAD ||
                        map.get(r.getCoor_x()-1).get(r.coor_y).getType_lo() == Lot.ROAD)
                    return false;
            }catch(Exception e){}
        }
        return true;
    }

    public int getNeighbours(Lot l){
        int count = 0;

        try{if(map.get(l.getCoor_x()+1).get(l.coor_y).getType_lo() == Lot.BUILDING)
            count++;}catch(Exception e){}

        try{if(map.get(l.getCoor_x()-1).get(l.coor_y).getType_lo() == Lot.BUILDING)
            count++;}catch(Exception e){}

        try{if(map.get(l.getCoor_x()).get(l.coor_y+1).getType_lo() == Lot.BUILDING)
            count++;}catch(Exception e){}

        try{if(map.get(l.getCoor_x()).get(l.coor_y-1).getType_lo() == Lot.BUILDING)
            count++;}catch(Exception e){}

        return count;
    }

    public Lot getLot(int i, int j){
        return map.get(i).get(j);
    }

    @Override
    public String toString(){
        String s = "";

        for(ArrayList<Lot> a : map){
            for(Lot l : a){
                int val = l.getType_lo();
                switch(val){
                    case Lot.EMPTY: s+="E"; break;
                    case Lot.BUILDING: s+="B"; break;
                    case Lot.ROAD: s+="R"; break;
                }
            }
            s+="\n";
        }
        return s;
    }
}
