package com.example.computer.doantn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

/**
 * Created by Computer on 5/1/2017.
 */

public class SelectLevelGameActivity extends AppCompatActivity implements View.OnClickListener, GameLevelAdapter.CallBack{

    Button btnSound;
    ImageButton btnBack;
    RecyclerView recViewListLevel;
    ArrayList<GameLevelBean> listLevel;
    GameLevelAdapter gameLevelAdapter;

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
    }

    private void setWidgets(Context context) {
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnSound =(Button) findViewById(R.id.btnSound);
        recViewListLevel = (RecyclerView) findViewById(R.id.recViewListLevel);

        // get name of class
        String nameClass = getNameClass(getIntent());

        //get list level of class
        this.listLevel = getLevelClass(nameClass);

        //set listView
        setListLevelClass();
    }

    private void setListLevelClass() {
        Log.d("Lop:","" +this.listLevel.size());
        gameLevelAdapter = new GameLevelAdapter(this,this.listLevel);
        recViewListLevel.setAdapter(gameLevelAdapter);
        gameLevelAdapter.setCallBack(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recViewListLevel.setLayoutManager(linearLayoutManager);
    }

    private String getNameClass(Intent intent) {
        return intent.getStringExtra("class");

    }

    private ArrayList<GameLevelBean> getLevelClass(String nameClass){
        ArrayList<GameLevelBean> listLevelOfClass = new ArrayList<>();
        switch (nameClass){
            case "lop1":
                listLevelOfClass = readFile("lop1");
                break;
            case "lop2":
                listLevelOfClass = readFile("lop1");
                break;
            case "lop3":
                listLevelOfClass = readFile("lop1");
                break;
            default:
                listLevelOfClass = readFile("lop1");
                break;
        }

        return listLevelOfClass;
    }

    private ArrayList<GameLevelBean> readFile(String lop1) {
        ArrayList<GameLevelBean> listLevelOfClass = new ArrayList<>();
        listLevelOfClass.add(new GameLevelBean("lop1", 1, "phép cộng trong phạm vi lớp 10"));
        listLevelOfClass.add(new GameLevelBean("lop1", 1, "phép cộng trong phạm vi lớp 10"));
        listLevelOfClass.add(new GameLevelBean("lop1", 1, "phép cộng trong phạm vi lớp 10"));
        listLevelOfClass.add(new GameLevelBean("lop1", 1, "phép cộng trong phạm vi lớp 10"));
        listLevelOfClass.add(new GameLevelBean("lop1", 1, "phép cộng trong phạm vi lớp 10"));
        listLevelOfClass.add(new GameLevelBean("lop1", 1, "phép cộng trong phạm vi lớp 10"));
        return listLevelOfClass;
    }

    @Override
    public void onItemClick(int position) {
        GameLevelBean gameLevel = listLevel.get(position);
        Intent itent = new Intent(this, GameMainActivity.class);
        itent.putExtra("itemLevel", gameLevel);
        startActivity(itent);
    }

    @Override
    public void onClick(View v) {

    }
}
