package com.example.computer.doantn;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button sound;
    Button start;
    Button setting;
    Button exit;

    MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mappingWidget();

        playMusic(R.raw.music1);
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
                changeStatusVolume(MainActivity.this);
                break;
            case R.id.btnStart:
                moveItent("start");
                break;
            case R.id.btnSetting:
                moveItent("setting");
                break;
            case R.id.btnExit:
                exit();
                break;
        }

    }

    private void exit() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void moveItent(String itent) {
        Intent itent2 = new Intent(MainActivity.this, SelectClassActivity.class);
        startActivity(itent2);
        Log.d("fuck:::::", itent);
    }

    private void changeStatusVolume(Context context) {
        if (sound.getText().equals("On")) {
            // open volume
            mp.setVolume(1, 1);
            sound.setBackgroundResource(R.mipmap.sound_on_icon);
            sound.setText("Off");
        } else {
            if (sound.getText().equals("Off")) {
                //mute volume
                mp.setVolume(0, 0);
                sound.setBackgroundResource(R.mipmap.sound_off_icon);
                sound.setText("On");
            }
        }
    }
}
