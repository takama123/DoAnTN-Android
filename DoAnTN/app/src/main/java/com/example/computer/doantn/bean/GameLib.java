package com.example.computer.doantn.bean;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Computer on 5/7/2017.
 */

public class GameLib {
    public int getNumberLevelOpen(String lop, Context context) {
        String path = lop.concat("-setting.txt");
        int numberLevelOpen = 0;
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                numberLevelOpen = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numberLevelOpen;
    }

    public ArrayList<GameLevelBean> getListGameOpenOfLevel(String lop, int numberLevelOpen, Context context) {
        String path = lop.concat(".txt");
        ArrayList<GameLevelBean> listLevel = new ArrayList<>();
        ArrayList<GameLevelBean> listLevelGet = new ArrayList<>();
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                GameLevelBean level = splipLine(line);
                listLevel.add(level);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (GameLevelBean level : listLevel) {
            if (level.getLevel() <= numberLevelOpen)
                listLevelGet.add(level);

        }
        return listLevelGet;
    }

    private GameLevelBean splipLine(String line) {
        String[] propertyLevel = line.split(";");
        GameLevelBean level = new GameLevelBean(Integer.parseInt(propertyLevel[0]),
                propertyLevel[4], propertyLevel[1], propertyLevel[3], propertyLevel[2]);
        return level;
    }

    public void wirteFile(String lop, int i) {

    }
}
