package com.example.computer.doantn;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class GameMainActivity extends AppCompatActivity
    implements GameLop1Fragment.OnFragmentInteractionListener {

    private String lop;
    private String maxNumber;
    private String typeMath;
    private String isRemember;
    private GameLop1Fragment mGameLop1Fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //end remove title
        setContentView(R.layout.activity_game_main);

        //get info level
        getInfoLevel();

        initViews();
        convertScene(mGameLop1Fragment);
    }

    private void getInfoLevel() {
        lop = getIntent().getStringExtra("lop");
        maxNumber = getIntent().getStringExtra("maxNumber");
        typeMath = getIntent().getStringExtra("typeMath");
        isRemember = getIntent().getStringExtra("isRemember");

    }

    private void initViews() {
       mGameLop1Fragment = new GameLop1Fragment();
        Bundle bundle = new Bundle();
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
    public void onFragmentInteraction() {
        doiManChoi();
    }

    public void clickTest(View view) {
        GameLop1Fragment fragment = new GameLop1Fragment();
        Bundle bundle = new Bundle();
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
        GameLop1Fragment fragment = new GameLop1Fragment();
        Bundle bundle = new Bundle();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
