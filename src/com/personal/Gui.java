package com.personal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Gui {
    private final Color COL_EMPTY = Color.GREEN;
    private final Color COL_ROAD = Color.GRAY;
    private final Color COL_BUILDING = Color.WHITE;
    private final int FRAME_X = 800;
    private final int FRAME_Y = 800;

    private int size_x;
    private int size_y;
    private JFrame f;
    private ArrayList< ArrayList<JButton> > buttons;

    public Gui(int size_x, int size_y){
        this.size_x = size_x;
        this.size_y = size_y;

        f = new JFrame();

        int lab_x = FRAME_X/size_x;
        int lab_y = FRAME_Y/size_y;

        buttons = new ArrayList< ArrayList<JButton> >();
        for(int i=0; i<size_x; i++){
            buttons.add(new ArrayList<JButton>());
            for(int j=0; j<size_y; j++){
                JButton but = new JButton();
                but.setBounds(i*lab_x, j*lab_y, lab_x, lab_y);
                but.setBackground(COL_EMPTY);
                buttons.get(i).add(but);
                f.add(but);
            }
        }

        f.setSize(FRAME_X, FRAME_Y);
        f.setLayout(null);
        f.setVisible(true);
    }

    public void refresh(Grid grid){
        for(int i=0; i<size_x; i++){
            for(int j=0; j<size_y; j++){
                switch(grid.getLot(i, j).getType()){
                    case Lot.EMPTY: buttons.get(i).get(j).setBackground(COL_EMPTY); break;
                    case Lot.BUILDING: buttons.get(i).get(j).setBackground(COL_BUILDING); break;
                    case Lot.ROAD: buttons.get(i).get(j).setBackground(COL_ROAD); break;

                }
            }
        }
    }
}
