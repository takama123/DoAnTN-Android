package com.example.computer.doantn;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameLop1Fragment extends Fragment implements View.OnClickListener {
    private TextView mTvSo1,mTvSo2,mTvCal,mTvKq;
    private TextView [] mTvPrefix = new TextView[4];
    private OnFragmentInteractionListener mListener;

    Random random  = new Random();
    int so1,so2,kq,cal;
    int location;

    public GameLop1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.chon_dap_an_dung, container, false);
        mTvSo1 = (TextView)view.findViewById(R.id.tvSo1);
        mTvSo2 = (TextView)view.findViewById(R.id.tvSo2);
        mTvCal = (TextView)view.findViewById(R.id.tvCal);
        mTvKq = (TextView)view.findViewById(R.id.tvKq);

        mTvPrefix[0] = (TextView)view.findViewById(R.id.tvPrefix1);
        mTvPrefix[1] = (TextView)view.findViewById(R.id.tvPrefix2);
        mTvPrefix[2] = (TextView)view.findViewById(R.id.tvPrefix3);
        mTvPrefix[3] = (TextView)view.findViewById(R.id.tvPrefix4);

        randomValue();
        addListerner();
        return view;
    }

    private void addListerner() {
        mTvSo1.setOnClickListener(this);
        mTvSo2.setOnClickListener(this);
        mTvCal.setOnClickListener(this);
        mTvKq.setOnClickListener(this);

        for(int i=0;i<4;++i){
            mTvPrefix[i].setOnClickListener(this);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        int i=0;
        switch (view.getId()){
            case R.id.tvPrefix1:
                i=0;
                break;
            case R.id.tvPrefix2:
                i=1;
                break;
            case R.id.tvPrefix3:
                i=2;
                break;
            case R.id.tvPrefix4:
                i=3;
                break;
        }
        if(i== location){
            Toast.makeText(getContext(),"BAN CHON TRUNG' ROI DO",Toast.LENGTH_LONG).show();
            mTvKq.setText(kq+"");
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void randomValue(){
        so1 = random.nextInt(50);
        so2 = random.nextInt(50);

        if(random.nextInt(2) == 0 ){
           //phep cong
            mTvCal.setText("+");
            kq = so1 + so2;
       }
        else{
           //phep tru
            mTvCal.setText("-");
            kq = so1 - so2;
       }
        mTvSo1.setText(so1 +"");
        mTvSo2.setText(so2 + "");

        // random vi tri ket qua
        location = random.nextInt(4);
        mTvPrefix[location].setText(kq + "");
        //

        //random value cho cac prefix con lai
        for(int i=0;i<4;++i){
            if(i==location) continue;
            mTvPrefix[i].setText(random.nextInt(100)+ "");
        }
        mTvKq.setText("");
    }
}
