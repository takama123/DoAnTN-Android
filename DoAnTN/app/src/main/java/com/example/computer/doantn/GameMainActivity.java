package com.example.computer.doantn;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class GameMainActivity extends AppCompatActivity
    implements GameLop1Fragment.OnFragmentInteractionListener {


    private GameLop1Fragment mGameLop1Fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);
        initViews();
        convertScene(mGameLop1Fragment);
    }

    private void initViews() {
       mGameLop1Fragment = new GameLop1Fragment();
    }

    private void convertScene(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.main_content,fragment,fragment.getTag())
            .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    public void clickTest(View view) {
        GameLop1Fragment fragment = new GameLop1Fragment();
        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.main_content,fragment,fragment.getTag())
//            .commit();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        transaction.replace(R.id.main_content, fragment );
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
