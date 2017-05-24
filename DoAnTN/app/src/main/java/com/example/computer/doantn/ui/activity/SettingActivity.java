package com.example.computer.doantn.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.computer.doantn.R;
import com.example.computer.doantn.bean.GameLib;

import java.io.IOException;

/**
 * Created by Computer on 5/22/2017.
 */

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnReset, btnGuiGopY, btnAbout;
    ImageButton btnBack;
    GameLib gl = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //end remove title
        setContentView(R.layout.setting_layout);
        mappingWidget();

    }

    private void mappingWidget() {
        gl = new GameLib();
        btnReset = (Button) findViewById(R.id.btnReset);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnGuiGopY = (Button) findViewById(R.id.btnSendMail);
        btnAbout = (Button) findViewById(R.id.btnAbout);

        btnReset.setOnClickListener(this);
        btnGuiGopY.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int idView = v.getId();
        switch (idView) {
            case R.id.btnReset:
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("Bạn có chắc chắn muốn cài đặt lại trò chơi không ?")
                        .setPositiveButton("Vâng", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    gl.creatSettingFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("Không", null)
                        .show();
                break;
            case R.id.btnSendMail:
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT);
                break;
            case R.id.btnAbout:
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT);
                break;
            case R.id.btnBack:
                finish();
                break;
        }
    }
}
