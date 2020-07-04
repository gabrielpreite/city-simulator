package com.personal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBInterface {
    private static DBInterface INSTANCE = null;

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private DBInterface(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/city?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            statement = connect.createStatement();
            statement.executeUpdate("use city;");
            statement.executeUpdate("delete from lot where id_lot>0;");
            statement.executeUpdate("alter table lot AUTO_INCREMENT = 0");
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

}
