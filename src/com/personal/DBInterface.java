package com.personal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBInterface {
    private static DBInterface INSTANCE = null;

    public static int size_x, size_y;

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private DBInterface(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/city?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
            statement = connect.createStatement();
            statement.executeUpdate("use city;");
            statement.executeUpdate("delete from road where id_roa>0;");
            statement.executeUpdate("alter table road AUTO_INCREMENT = 0");
            statement.executeUpdate("delete from open where id_wor>0;");
            statement.executeUpdate("alter table open AUTO_INCREMENT = 0");
            statement.executeUpdate("delete from work where id_cit>0;");
            statement.executeUpdate("alter table work AUTO_INCREMENT = 0");
            statement.executeUpdate("delete from study where id_cit>0;");
            statement.executeUpdate("alter table study AUTO_INCREMENT = 0");
            statement.executeUpdate("delete from visit where id_cit>0;");
            statement.executeUpdate("alter table visit AUTO_INCREMENT = 0");
            statement.executeUpdate("delete from building where id_bui>0;");
            statement.executeUpdate("alter table building AUTO_INCREMENT = 0");
            statement.executeUpdate("delete from family where id_fam>0;");
            statement.executeUpdate("alter table family AUTO_INCREMENT = 0");
            statement.executeUpdate("delete from citizen where id_cit>0;");
            statement.executeUpdate("alter table citizen AUTO_INCREMENT = 0");
            statement.executeUpdate("delete from house where id_hou>0;");
            statement.executeUpdate("alter table house AUTO_INCREMENT = 0");
            statement.executeUpdate("delete from school where id_sch>0;");
            statement.executeUpdate("alter table school AUTO_INCREMENT = 0");
            statement.executeUpdate("delete from leisure where id_lei>0;");
            statement.executeUpdate("alter table leisure AUTO_INCREMENT = 0");
            statement.executeUpdate("delete from shop where id_sho>0;");
            statement.executeUpdate("alter table shop AUTO_INCREMENT = 0");
            statement.executeUpdate("delete from park where id_par>0;");
            statement.executeUpdate("alter table park AUTO_INCREMENT = 0");
        }catch(Exception e){e.printStackTrace();}
    }

    public static DBInterface getInstance(){
        if(INSTANCE == null)
            INSTANCE = new DBInterface();
        return INSTANCE;
    }

    public void setLot(Lot l){
        try{
            preparedStatement = connect.prepareStatement("update lot set type_lo = ? where id_lot = ?");
            preparedStatement.setInt(1, l.getType_lo());
            preparedStatement.setInt(2, l.getCoor_x()*size_x+l.getCoor_y()+1);
            preparedStatement.executeUpdate();
        }catch(Exception e){e.printStackTrace();}
    }

    public void setBuilding(Building b){
        try{
            preparedStatement = connect.prepareStatement("delete from building where id = ?");
            preparedStatement.setInt(1, b.getId());
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("insert into building values(?, ?, ?, ? ,?, ?)");
            preparedStatement.setInt(1, b.getId());
            preparedStatement.setInt(2, b.getType_bu());
            preparedStatement.setInt(3, b.getHappiness());
            preparedStatement.setInt(4, b.getWorkers());
            preparedStatement.setInt(5, b.getCapacity());
            preparedStatement.setInt(6, b.getSalary());
            preparedStatement.executeUpdate();

            if(b instanceof House){
                House h = (House)b;
                preparedStatement = connect.prepareStatement("insert into house values(?, ?)");
                preparedStatement.setInt(1, h.id);
                preparedStatement.setInt(2, h.getRent());
            }else if(b instanceof School){
                School s = (School)b;
                preparedStatement = connect.prepareStatement("insert into school values(?)");
                preparedStatement.setInt(1, s.id);
            }else if(b instanceof Leisure){
                Leisure l = (Leisure)b;
                preparedStatement = connect.prepareStatement("insert into leisure values(?, ?, ?)");
                preparedStatement.setInt(1, l.id);
                preparedStatement.setInt(2, l.getType_le());
                preparedStatement.setInt(3, l.getCost());
            }
            preparedStatement.executeUpdate();
        }catch(Exception e){e.printStackTrace();}
    }

    public void resetLots(){
        try{
            preparedStatement = connect.prepareStatement("update lot set type_lo = ?");
            preparedStatement.setInt(1, Lot.EMPTY);
            preparedStatement.executeUpdate();
        }catch(Exception e){e.printStackTrace();}
    }

    public void addRoad(int start_x, int end_x, int start_y, int end_y, int dir, int center_x, int center_y){
        try{
            //sets as roadside type all the adjacent lots, including the corners
            preparedStatement = connect.prepareStatement("update lot set type_lo = ? where (coor_x between ? and ?) and (coor_y between ? and ?) and type_lo = ?");
            preparedStatement.setInt(1, Lot.ROADSIDE);
            preparedStatement.setInt(2, Math.min(start_x, end_x)-1);
            preparedStatement.setInt(3, Math.max(start_x, end_x)+1);
            preparedStatement.setInt(4, Math.min(start_y, end_y)-1);
            preparedStatement.setInt(5, Math.max(start_y, end_y)+1);
            preparedStatement.setInt(6, Lot.EMPTY);
            preparedStatement.executeUpdate();

            //sets as empty the corners
            //TEMPFIXtodo bug: setting roadside corners as empty could erase roadside lots from other roads
            /*statement.executeUpdate("update lot set type_lo = " + Lot.EMPTY + " where coor_x = " + (Math.min(start_x, end_x)-1) + " and coor_y = " + (Math.min(start_y, end_y)-1) + " and type_lo = " + Lot.ROADSIDE);
            statement.executeUpdate("update lot set type_lo = " + Lot.EMPTY + " where coor_x = " + (Math.max(start_x, end_x)+1) + " and coor_y = " + (Math.min(start_y, end_y)-1) + " and type_lo = " + Lot.ROADSIDE);
            statement.executeUpdate("update lot set type_lo = " + Lot.EMPTY + " where coor_x = " + (Math.min(start_x, end_x)-1) + " and coor_y = " + (Math.max(start_y, end_y)+1) + " and type_lo = " + Lot.ROADSIDE);
            statement.executeUpdate("update lot set type_lo = " + Lot.EMPTY + " where coor_x = " + (Math.max(start_x, end_x)+1) + " and coor_y = " + (Math.max(start_y, end_y)+1) + " and type_lo = " + Lot.ROADSIDE);
            */

            //sets as road the lots between the requested coordinates
            preparedStatement = connect.prepareStatement("update lot set type_lo = ? where (coor_x between ? and ?) and (coor_y between ? and ?)");
            preparedStatement.setInt(1, Lot.ROAD);
            preparedStatement.setInt(2, Math.min(start_x, end_x));
            preparedStatement.setInt(3, Math.max(start_x, end_x));
            preparedStatement.setInt(4, Math.min(start_y, end_y));
            preparedStatement.setInt(5, Math.max(start_y, end_y));
            preparedStatement.executeUpdate();

            //deletes the road entry from the center of the new road, removing the duplicate
            statement.executeUpdate("delete from road where id_roa = " + center_x*size_x+size_y+1);

            //adds the new road to the road table
            if(dir == Road.HOR){
                for(int i=Math.min(start_x, end_x); i<=Math.max(start_x, end_x); i++){
                    for(int j=Math.min(start_y, end_y); j<=Math.max(start_y, end_y); j++){
                        resultSet = statement.executeQuery("select * from road where id_roa = " + (i*size_x+j+1));
                        if(resultSet.next()){
                            preparedStatement = connect.prepareStatement("update road set dir = ? where id_roa = ?");
                            preparedStatement.setInt(1, Road.CRO);
                            preparedStatement.setInt(2, resultSet.getInt("id_roa"));
                            preparedStatement.executeUpdate();
                        }else{
                            preparedStatement = connect.prepareStatement("insert into road values(?, ?)");
                            preparedStatement.setInt(1, i*size_x+j+1);
                            preparedStatement.setInt(2, Road.HOR);
                            preparedStatement.executeUpdate();
                        }
                    }
                }
            }
            else{
                for(int i=Math.min(start_y, end_y); i<=Math.max(start_y, end_y); i++){
                    for(int j=Math.min(start_x, end_x); j<=Math.max(start_x, end_x); j++){
                        resultSet = statement.executeQuery("select * from road where id_roa = " + (j*size_x+i+1));
                        if(resultSet.next()) {
                            preparedStatement = connect.prepareStatement("update road set dir = ? where id_roa = ?");
                            preparedStatement.setInt(1, Road.CRO);
                            preparedStatement.setInt(2, resultSet.getInt("id_roa"));
                            preparedStatement.executeUpdate();
                        }else{
                            preparedStatement = connect.prepareStatement("insert into road values(?, ?)");
                            preparedStatement.setInt(1, j*size_x+i+1);
                            preparedStatement.setInt(2, Road.VER);
                            preparedStatement.executeUpdate();
                        }
                    }
                }
            }

            //sets as crossroad the center of the new road
            preparedStatement = connect.prepareStatement("update road set dir = ? where id_roa = ?");
            preparedStatement.setInt(1, Road.CRO);
            preparedStatement.setInt(2, center_x*size_x+center_y+1);
            preparedStatement.executeUpdate();
        }catch(Exception e){e.printStackTrace();}
    }

    public Lot getLot(int x, int y){
        //todo bug: add out of bounds case
        Lot l = null;
        try{
            preparedStatement = connect.prepareStatement("select * from lot " +
                    "left join building on lot.id_lot = building.id_bui " +
                    "left join road on lot.id_lot = road.id_roa " +
                    "left join house on building.id_bui = house.id_hou " +
                    "left join school on building.id_bui = school.id_sch " +
                    "left join leisure on building.id_bui = leisure.id_lei " +
                    "left join shop on leisure.id_lei = shop.id_sho " +
                    "left join park on leisure.id_lei = park.id_par " +
                    "where id_lot = ?");
            preparedStatement.setInt(1, x*size_x+y+1);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            l = new Lot(resultSet.getInt("id_lot"),
                    resultSet.getInt("coor_x"),
                    resultSet.getInt("coor_y"),
                    resultSet.getInt("type_lo"));
            switch(l.getType_lo()){
                case Lot.ROAD:
                    Road r = new Road(l, resultSet.getInt("dir"));
                    return r;
                case Lot.BUILDING:
                    Building b = new Building(l,
                            resultSet.getInt("type_bu"),
                            resultSet.getInt("capacity"),
                            resultSet.getInt("happiness"),
                            resultSet.getInt("workers"),
                            resultSet.getInt("salary"));
                    return b;
            }//todo add other building types to switch
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            return l;
        }
    }

    public ArrayList<Road> getEligibleRoads(){
        ArrayList<Road> roads = new ArrayList<Road>();
        try{
            resultSet = statement.executeQuery("select * from lot left join road on lot.id_lot = road.id_roa where dir != " + Road.CRO);
            while(resultSet.next()){
                Lot l = new Lot(resultSet.getInt("id_lot"), resultSet.getInt("coor_x"), resultSet.getInt("coor_y"), resultSet.getInt("type_lo"));
                Road r = new Road(l, resultSet.getInt("dir"));
                roads.add(r);
            }
        }catch(Exception e){e.printStackTrace();}
        return roads;
    }

    public ArrayList<Lot> getEligibleLots(){
        ArrayList<Lot> lots = new ArrayList<Lot>();
        try{
            resultSet = statement.executeQuery("select * from lot left join road on lot.id_lot = road.id_roa where type_lo = " + Lot.ROADSIDE);
            while(resultSet.next()){
                Lot l = new Lot(resultSet.getInt("id_lot"), resultSet.getInt("coor_x"), resultSet.getInt("coor_y"), resultSet.getInt("type_lo"));
                lots.add(l);
            }
        }catch(Exception e){e.printStackTrace();}
        return lots;
    }

    /*public ArrayList< ArrayList<Lot> > getGrid(){
        ArrayList< ArrayList<Lot> > grid = new ArrayList< ArrayList<Lot> >();
        for(int i=0; i<size_x; i++){
            grid.add(new ArrayList<Lot>());
            for(int j=0; i<size_y; j++){
                grid.get(i).add(null);
            }
        }

        try{
            resultSet = statement.executeQuery("select * from lot " +
                    "left join building on lot.id_lot = building.id_bui " +
                    "left join road on lot.id_lot = road.id_roa " +
                    "left join house on building.id_bui = house.id_hou " +
                    "left join school on building.id_bui = school.id_sch " +
                    "left join leisure on building.id_bui = leisure.id_lei " +
                    "left join shop on leisure.id_lei = shop.id_sho " +
                    "left join park on leisure.id_lei = park.id_par");
            while(resultSet.next()){
                Lot l = new Lot(resultSet.getInt("id_lot"),
                        resultSet.getInt("coor_x"),
                        resultSet.getInt("coor_y"),
                        resultSet.getInt("type_lo"));
                switch(l.getType_lo()){
                    case Lot.ROAD:
                        Road r = new Road(l, resultSet.getInt("dir"));
                        grid.get(l.getCoor_x()).set(l.getCoor_y(), r);
                        break;
                    case Lot.BUILDING:
                        Building b = new Building(l,
                                resultSet.getInt("type_bu"),
                                resultSet.getInt("capacity"),
                                resultSet.getInt("happiness"),
                                resultSet.getInt("workers"),
                                resultSet.getInt("salary"));
                        grid.get(resultSet.getInt("coor_x")).set(resultSet.getInt("coor_y"), b);
                        break;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
            return grid;
        }
    }*/

}
