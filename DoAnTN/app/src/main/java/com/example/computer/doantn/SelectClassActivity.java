package com.example.computer.doantn;

import android.content.ClipData;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Computer on 4/9/2017.
 */
public class SelectClassActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLop1;
    private Button btnLop2;
    private Button btnLop3;
    private ImageView btnBack;
    private ImageView btnSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chon_lop);

        setWidgets();
    }

    private void setWidgets() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnSound = (ImageView) findViewById(R.id.btnBack);
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
        switch (idView){
            case R.id.btnBack:

                Log.d("click:","btnBack");
                break;
            case R.id.btnSound:
                Log.d("click:","btnSound");
                break;
            case R.id.btnLop1:
                Log.d("click:","btnLop1");
                break;
            case R.id.btnLop2:
                Log.d("click:","btnLop2");
                break;
            case R.id.btnLop3:
                Log.d("click:","btnLop3");
                break;

        }
    }
}