package com.personal;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Config {
    String path = System.getProperty("user.dir");//gets current working path
    File file = new File(path+"\\src\\com\\config.txt");
    BufferedReader br = new BufferedReader(new FileReader(file));
    String s;
    StringTokenizer st;
    ArrayList <Integer> params = new ArrayList<Integer>();
    ArrayList <String> colors = new ArrayList<String>();

    public Config() throws IOException {
        while ((s = br.readLine())!=null){
            if(!(s.contains("//"))) { //checks for comment line, skips it if present
                st = new StringTokenizer(s, "= ");
                st.nextToken();//discards token preceding delim
                if (params.size() < 7)
                    params.add(Integer.parseInt(st.nextToken()));
                else
                    colors.add(st.nextToken());
            }
        }
    }

    public int getsizex() { return params.get(0); }
    public int getsizey() { return params.get(1); }
    public int getbuildp() { return params.get(2); }
    public int getroadp() { return params.get(3); }
    public int getroadextp() { return params.get(4); }
    public int getframex() { return params.get(5); }
    public int getframey() { return params.get(6); }

    public Color getcol_empty() { return getcolor(colors.get(0)); }
    public Color getcol_road() { return getcolor(colors.get(1)); }
    public Color getcol_house() { return getcolor(colors.get(2)); }
    public Color getcol_school() { return getcolor(colors.get(3)); }
    public Color getcol_leisure() { return getcolor(colors.get(4)); }


    public Color getcolor (String col) {
        switch (col){
            case "RED" : return Color.RED;
            case "GREEN" : return  Color.GREEN;
            case "BLUE" : return Color.BLUE;
            case "CYAN" : return Color.CYAN;
            case "GRAY" : return Color.GRAY;
            case "DARKGRAY" : return Color.DARK_GRAY;
            case "DARK_GRAY" : return Color.DARK_GRAY;
            case "PINK" : return Color.PINK;
            case "MAGENTA" : return Color.MAGENTA;
            case "YELLOW" : return Color.YELLOW;
            case "ORANGE" : return Color.ORANGE;
            case "BLACK" : return Color.BLACK;
            case "WHITE" : return Color.WHITE;
            default: System.out.println("Color in config file not recognized"); System.exit(1); break;
        }
        return null;
    }

}