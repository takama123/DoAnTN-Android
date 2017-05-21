package com.example.computer.doantn.ui.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.computer.doantn.R;

/**
 * Created by Computer on 4/9/2017.
 */
public class SelectClassActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLop1;
    private Button btnLop2;
    private Button btnLop3;
    private ImageView btnBack;
    private Button btnSound;

    MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //end remove title
        setContentView(R.layout.chon_lop);

        setWidgets();
        playMusic(R.raw.hai_con_than_lan_con);
    }

    private void setWidgets() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnSound =(Button) findViewById(R.id.btnSound);
        btnLop1 = (Button) findViewById(R.id.btnLop1);
        btnLop2 = (Button) findViewById(R.id.btnLop2);
        btnLop3 = (Button) findViewById(R.id.btnLop3);

        btnBack.setOnClickListener(this);
        btnSound.setOnClickListener(this);
        btnLop1.setOnClickListener(this);
        btnLop2.setOnClickListener(this);
        btnLop3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int idView = v.getId();
        Intent itent = new Intent(SelectClassActivity.this, SelectLevelGameActivity.class);
        mp.stop();
        switch (idView){
            case R.id.btnBack:
                finish();
                mp.start();
                break;
            case R.id.btnSound:
                changeStatusVolume(btnSound);
                Log.d("click:","btnSound");
                break;
            case R.id.btnLop1:
                itent.putExtra("class","lop1");
                startActivity(itent);
                break;
            case R.id.btnLop2:
                itent.putExtra("class","lop2");
                startActivity(itent);
                break;
            case R.id.btnLop3:
                itent.putExtra("class","lop3");
                startActivity(itent);
                break;

        }
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
    private void playMusic(int idMusic) {
        mp = MediaPlayer.create(this, idMusic);
        mp.start();
        mp.setVolume(1, 1);
    }
}