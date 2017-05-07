package com.example.computer.doantn;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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

import com.example.computer.doantn.bean.GameLevelBean;
import com.example.computer.doantn.bean.GameLib;

import java.util.ArrayList;

/**
 * Created by Computer on 5/1/2017.
 */

public class SelectLevelGameActivity extends AppCompatActivity implements View.OnClickListener, GameLevelAdapter.CallBack {

    Button btnSound;
    ImageButton btnBack;
    RecyclerView recViewListLevel;
    ArrayList<GameLevelBean> listLevel;
    GameLevelAdapter gameLevelAdapter;
    MediaPlayer mp = null;
    String nameClass;
    GameLib gameLib;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // end remove title
        setContentView(R.layout.list_level_lop);
        gameLib = new GameLib();
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
        btnSound = (Button) findViewById(R.id.btnSound);
        btnBack.setOnClickListener(this);
        btnSound.setOnClickListener(this);
        recViewListLevel = (RecyclerView) findViewById(R.id.recViewListLevel);

        // get name of class
        nameClass = getIntent().getStringExtra("class");

        //get list level of class
        listLevel = getLevelClass(nameClass);

        //set listView
        setListLevelClass();
    }

    private void setListLevelClass() {
        gameLevelAdapter = new GameLevelAdapter(this, this.listLevel);
        recViewListLevel.setAdapter(gameLevelAdapter);
        gameLevelAdapter.setCallBack(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recViewListLevel.setLayoutManager(linearLayoutManager);
    }

    private ArrayList<GameLevelBean> getLevelClass(String nameClass) {
        ArrayList<GameLevelBean> listLevelOfClass = null;
        int numberLevelOpen = gameLib.getNumberLevelOpen(nameClass, this);
        listLevelOfClass = gameLib.getListGameOpenOfLevel(nameClass, numberLevelOpen, this);
        return listLevelOfClass;
    }

    @Override
    public void onItemClick(int position) { // click level
        GameLevelBean gameLevel = listLevel.get(position);
        Intent itent = new Intent(this, GameMainActivity.class);
        Log.d("aaaa",gameLevel.getLevel()+"");
        itent.putExtra("lop", nameClass);
        itent.putExtra("idLevel", gameLevel.getLevel()+"");
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
}
