package com.example.computer.doantn;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.computer.doantn.bean.GameLevelBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Computer on 5/1/2017.
 */

public class SelectLevelGameActivity extends AppCompatActivity implements View.OnClickListener, GameLevelAdapter.CallBack{

    Button btnSound;
    ImageButton btnBack;
    RecyclerView recViewListLevel;
    ArrayList<GameLevelBean> listLevel;
    GameLevelAdapter gameLevelAdapter;
    MediaPlayer mp = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // end remove title
        setContentView(R.layout.list_level_lop);

        setWidgets(this);
        playMusic(R.raw.music1);
    }

    private void playMusic(int idMusic) {
        mp = MediaPlayer.create(this, idMusic);
        mp.start();
        mp.setVolume(1, 1);
    }

    private void setWidgets(Context context) {
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnSound =(Button) findViewById(R.id.btnSound);
        btnBack.setOnClickListener(this);
        btnSound.setOnClickListener(this);
        recViewListLevel = (RecyclerView) findViewById(R.id.recViewListLevel);

        // get name of class
        String nameClass = getIntent().getStringExtra("class");

        //get list level of class
        listLevel = getLevelClass(nameClass);

        //set listView
        setListLevelClass();
    }

    private void setListLevelClass() {
        gameLevelAdapter = new GameLevelAdapter(this,this.listLevel);
        recViewListLevel.setAdapter(gameLevelAdapter);
        gameLevelAdapter.setCallBack(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recViewListLevel.setLayoutManager(linearLayoutManager);
    }

    private ArrayList<GameLevelBean> getLevelClass(String nameClass){
        ArrayList<GameLevelBean> listLevelOfClass = null;
        int numberLevelOpen = getNumberLevelOpen(nameClass);
        Log.d("TAG:",numberLevelOpen+"");
        listLevelOfClass = getListGameLevel(nameClass,numberLevelOpen);
        return listLevelOfClass;
    }

    private int getNumberLevelOpen(String lop) {
        String path = lop.concat("-setting.txt");
        int numberLevelOpen = 0;
        AssetManager am = this.getAssets();
        try {
            InputStream is = am.open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null){
                numberLevelOpen = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numberLevelOpen;
    }

    private ArrayList<GameLevelBean> getListGameLevel(String lop, int numberLevelOpen) {
        String path = lop.concat(".txt");
        ArrayList<GameLevelBean> listLevel = new ArrayList<>();
        ArrayList<GameLevelBean> listLevelGet = new ArrayList<>();
        AssetManager am = this.getAssets();
        try {
            InputStream is = am.open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null){
                GameLevelBean level = splipLine(line);
                listLevel.add(level);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (GameLevelBean level: listLevel) {
            if(level.getLevel() <= numberLevelOpen)
                listLevelGet.add(level);

        }
        return listLevelGet;
    }

    @Override
    public void onItemClick(int position) { // click level
        GameLevelBean gameLevel = listLevel.get(position);
        Intent itent = new Intent(this, GameMainActivity.class);
        itent.putExtra("lop", "1");
        itent.putExtra("maxNumber", gameLevel.getMaxlength());
        itent.putExtra("typeMath", gameLevel.getTypeMath());
        itent.putExtra("isRemember", gameLevel.getIsRemember());
        startActivity(itent);
    }

    @Override
    public void onClick(View v) { // click button back or sound
        int idView = v.getId();
        switch (idView) {
            case R.id.btnSound:
                changeStatusVolume(btnSound);
                break;
            case R.id.btnBack:
                finish();
                break;
            default:
                break;
        }
    }

    public void changeStatusVolume(Button view) {
        if (view.getText().equals("On")) {
            // open volume
            mp.setVolume(1, 1);
            view.setBackgroundResource(R.mipmap.sound_on_icon);
            view.setText("Off");
        } else {
            if (view.getText().equals("Off")) {
                //mute volume
                mp.setVolume(0, 0);
                view.setBackgroundResource(R.mipmap.sound_off_icon);
                view.setText("On");
            }
        }
    }

    private GameLevelBean splipLine(String line) {
        String[] propertyLevel = line.split(";");
        GameLevelBean level = new GameLevelBean(Integer.parseInt(propertyLevel[0]),
                propertyLevel[4],propertyLevel[1],propertyLevel[3],propertyLevel[2]);
        return level;
    }
}
