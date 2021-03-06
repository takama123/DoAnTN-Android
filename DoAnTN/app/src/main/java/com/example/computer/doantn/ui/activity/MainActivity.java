package com.example.computer.doantn.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.computer.doantn.R;
import com.example.computer.doantn.bean.Constant;
import com.example.computer.doantn.bean.GameLib;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button sound;
    Button start;
    Button setting;
    Button exit;

    MediaPlayer mp = null;
    GameLib gl = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //end remove title
        setContentView(R.layout.activity_main);
        mappingWidget();
        gl = new GameLib();
        checkTheFistTime();
        playMusic(R.raw.music1);
    }

    private void checkTheFistTime() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
            edit.commit();
            if(Constant.FIST_TIME){
                try {
                    gl.creatSettingFile();
                    Constant.FIST_TIME = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    private void playMusic(int idMusic) {
        mp = MediaPlayer.create(this, idMusic);
        mp.start();
        mp.setVolume(1, 1);
    }

    private void mappingWidget() {
        sound = (Button) findViewById(R.id.btnSound);
        start = (Button) findViewById(R.id.btnStart);
        setting = (Button) findViewById(R.id.btnSetting);
        exit = (Button) findViewById(R.id.btnExit);

        sound.setOnClickListener(this);
        start.setOnClickListener(this);
        setting.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int idView = v.getId();
        switch (idView) {
            case R.id.btnSound:
                changeStatusVolume(sound);
                break;
            case R.id.btnStart:
                moveItent("start");
                break;
            case R.id.btnSetting:
                moveItent("setting");
                break;
            case R.id.btnExit:
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alert_label_editor, null);
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Thoát khỏi trò chơi !")
                        .setMessage("Bạn có chắc chắn muốn thoát khỏi trò chơi không ?")
                        .setPositiveButton("Vâng", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("Không", null)
                        .show();
                break;
        }
    }

    private void resetLevel() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Reset game !")
                .setMessage("Bạn có chắc chắn muốn reset trò chơi không ?")
                .setPositiveButton("Vâng", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("Không", null)
                .show();
    }

    private void moveItent(String itent) {
        Intent itent2 = null;
        if("start".equals(itent)){
            itent2 = new Intent(MainActivity.this, SelectClassActivity.class);
        }else if("setting".equals(itent)){
            itent2 = new Intent(MainActivity.this, SettingActivity.class);
        }
        mp.stop();
        startActivity(itent2);
    }

    private void changeStatusVolume(Button view) {
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
