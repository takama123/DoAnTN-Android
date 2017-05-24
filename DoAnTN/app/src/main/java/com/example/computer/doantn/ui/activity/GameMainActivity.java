package com.example.computer.doantn.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.computer.doantn.R;
import com.example.computer.doantn.bean.GameLib;
import com.example.computer.doantn.ui.fragment.GameLop1Fragment;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public class GameMainActivity extends AppCompatActivity
    implements GameLop1Fragment.OnFragmentInteractionListener, View.OnClickListener {

    private ImageButton mBtnBack;
    private TextView soCauHoi;
    private String lop;
    private String maxNumber;
    private String typeMath;
    private String isRemember;
    private String idLevel;
    private int idCauHoi = 0;
    private GameLop1Fragment mGameLop1Fragment;
    private GameLib gameLib;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //end remove title
        setContentView(R.layout.activity_game_main);
        gameLib = new GameLib();
        soCauHoi = (TextView) findViewById(R.id.idcauhoi);
        mBtnBack = (ImageButton) findViewById(R.id.btnBack);

        mBtnBack.setOnClickListener(this);
        //get info level
        getInfoLevel();
        initViews();
        convertScene(mGameLop1Fragment);
    }

    private void getInfoLevel() {
        idLevel = getIntent().getStringExtra("idLevel");
        lop = getIntent().getStringExtra("lop");
        maxNumber = getIntent().getStringExtra("maxNumber");
        typeMath = getIntent().getStringExtra("typeMath");
        isRemember = getIntent().getStringExtra("isRemember");

    }

    private void initViews() {
        Log.d("aaaa",idLevel +":" + lop +":" + idCauHoi +":" + maxNumber +":");
       mGameLop1Fragment = new GameLop1Fragment();
        idCauHoi ++;
        Bundle bundle = new Bundle();
        bundle.putString("idCauHoi",idCauHoi+"");
        bundle.putString("idLevel",idLevel+"");
        bundle.putString("lop",lop);
        bundle.putString("maxNumber",maxNumber);
        bundle.putString("typeMath",typeMath);
        bundle.putString("isRemember",isRemember);
        mGameLop1Fragment.setArguments(bundle);
    }

    private void convertScene(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.main_content,fragment,fragment.getTag())
            .commit();
    }

    @Override
    public void onFragmentInteraction(int idCauHoi) throws ParserConfigurationException, SAXException, IOException {
        if (idCauHoi == 10){//check cau hoi cuoi cung
            int levelOpen = gameLib.getMaxLevel(this, lop);
            if(Integer.parseInt(idLevel) == levelOpen){
                gameLib.wirteFile(lop, levelOpen+1);
            }
            gameLib.nextLevel(lop, this);
            finish();
        }else {
            soCauHoi.setText(idCauHoi + " / 10");
            doiManChoi();
        }
    }

    public void clickTest(View view) {
        idCauHoi++ ;
        GameLop1Fragment fragment = new GameLop1Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("idCauHoi",idCauHoi+"");
        bundle.putString("lop",lop);
        bundle.putString("maxNumber",maxNumber);
        bundle.putString("typeMath",typeMath);
        bundle.putString("isRemember",isRemember);
        fragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.main_content,fragment,fragment.getTag())
//            .commit();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        transaction.replace(R.id.main_content, fragment );
        transaction.addToBackStack(null);
        transaction.commit();
    }

    void doiManChoi(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            idCauHoi++ ;
            GameLop1Fragment fragment = new GameLop1Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("idCauHoi",idCauHoi+"");
            bundle.putString("lop",lop);
            bundle.putString("maxNumber",maxNumber);
            bundle.putString("typeMath",typeMath);
            bundle.putString("isRemember",isRemember);
            fragment.setArguments(bundle);
            FragmentManager manager = getSupportFragmentManager();
    //        manager.beginTransaction().replace(R.id.main_content,fragment,fragment.getTag())
    //            .commit();

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

            transaction.replace(R.id.main_content, fragment );
            transaction.addToBackStack(null);
            transaction.commit();
            }
        },500);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnBack){
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Thoát khỏi màn chơi !")
                    .setMessage("Bạn có chắc chắn muốn thoát khỏi màn đang chơi không ?")
                    .setPositiveButton("Vâng", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("Không", null)
                    .show();
        }
    }
}
