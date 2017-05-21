package com.example.computer.doantn.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.computer.doantn.R;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

public class GameLop1Fragment extends Fragment implements View.OnClickListener {
    private TextView mTvSo1,mTvSo2,mTvCal,mTvKq, mTvEqual;
    private TextView [] mTvPrefix = new TextView[4];
    private OnFragmentInteractionListener mListener;

    Random random  = new Random();
    int so1,so2,kq,cal;
    int location;
    int typeMath, maxNumber, idCauHoi ;
    String lop;
    String idLevel;
    boolean isRemember;

    public GameLop1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.chon_dap_an_dung, container, false);
        setWidget(view);
        getInfoLevel();
        randomValue(maxNumber,typeMath,isRemember);
        addListerner();
        return view;
    }

    private void setWidget(View view) {
        mTvSo1 = (TextView)view.findViewById(R.id.tvSo1);
        mTvSo2 = (TextView)view.findViewById(R.id.tvSo2);
        mTvCal = (TextView)view.findViewById(R.id.tvCal);
        mTvKq = (TextView)view.findViewById(R.id.tvKq);
        mTvEqual = (TextView)view.findViewById(R.id.tvEqual);
        mTvKq.setText("?");

        mTvPrefix[0] = (TextView)view.findViewById(R.id.tvPrefix1);
        mTvPrefix[1] = (TextView)view.findViewById(R.id.tvPrefix2);
        mTvPrefix[2] = (TextView)view.findViewById(R.id.tvPrefix3);
        mTvPrefix[3] = (TextView)view.findViewById(R.id.tvPrefix4);
    }

    private void getInfoLevel() {
        idLevel = getArguments().getString("idLevel");
        idCauHoi = Integer.parseInt(getArguments().getString("idCauHoi"));
        lop = (getArguments().getString("lop"));
        maxNumber = Integer.parseInt(getArguments().getString("maxNumber"));
        typeMath = Integer.parseInt(getArguments().getString("typeMath"));
        isRemember = Boolean.parseBoolean(getArguments().getString("isRemember"));
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
        // chon dap an dung
        if(i== location){
            mTvKq.setText(kq+"");
            final MediaPlayer mp2 = MediaPlayer.create(view.getContext(), R.raw.tiengvotay);
            mp2.start();
            try {
                mListener.onFragmentInteraction(idCauHoi);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int idCauHoi) throws ParserConfigurationException, SAXException, IOException;
    }

    public void randomValue(int maxNumber, int typeMath, boolean isRemember){
        setTypeMath(typeMath);

        // set so thu nhat va so thu hai
        boolean checkValid = false;
        do {
            so1 = random.nextInt(maxNumber);
            so2 = random.nextInt(maxNumber);
            if(!isRemember){ // phép toán không nhớ
                switch (typeMath){
                    case 0:
                        if((so1%10 + so2%10) > 10) checkValid = true;
                        else checkValid = false;
                        break;
                    case 1:
                        if((so1%10 < so2%10)||(so1 < so2)) checkValid = true;
                        else {
                            checkValid = false;
                        }
                        break;
                    case 2:
                        if("+".equals(mTvCal.getText().toString())){
                            if((so1%10 + so2%10) > 10) checkValid = true;
                            else checkValid = false;
                        }else{
                            if((so1%10 < so2%10)||(so1 < so2)) {
                                checkValid = true;
                            }
                            else checkValid = false;
                        }
                        break;
                    default:
                        checkValid = false;
                        break;
                }
            }
        }while (checkValid);

        // tim ket qua phép toán
        String phepToan = mTvCal.getText().toString();
        switch (phepToan){
            case "+":
                kq = so1 + so2;
                break;
            case "-":
                kq = so1 - so2;
                break;
            case "x":
                kq = so1*so2;
                break;
            case "/":
                kq = so1/so2;
                break;
            default:
                Toast.makeText(getContext(),"Exception!",Toast.LENGTH_LONG).show();
                break;
        }

        mTvSo1.setText(so1 +"");
        mTvSo2.setText(so2 + "");

        // random vi tri ket qua
        location = random.nextInt(4);
        mTvPrefix[location].setText(kq + "");
        //
        ArrayList<Integer> listKQ  = new ArrayList<>();;
        //random value cho cac prefix con lai
        boolean isExist = false;
        for(int i=0;i<4;++i){
            if(i==location) continue;
            int randomKQ = 0;
            do {
                isExist = false;
                randomKQ = random.nextInt(maxNumber);
                if((listKQ.size() == 0) && (randomKQ == kq)) isExist = true;
                for (int item : listKQ) {
                    if ((randomKQ == item) || (randomKQ == kq)) {
                        isExist = true;
                    }
                }
            }while (isExist);
            listKQ.add(randomKQ);
            mTvPrefix[i].setText(randomKQ+ "");
        }
        mTvKq.setText("?");
    }

    private void setTypeMath(int typeMath) {
        //typeMath = 0 : cong
        //typeMath = 1 : tru
        //typeMath = 2 : cong hoac tru
        //typeMath = 3 : nhan
        //typeMath = 4 : chia
        //typeMath = 5 : nhan hoac chia
        //typeMath = 6 : cong tru nhan chia
        //typeMath = 7 : so sanh
        //typeMath = 8 : so sanh + cong + tru
        switch(typeMath){
            case 0:// phép cộng
                mTvCal.setText("+");
                break;
            case 1:
                mTvCal.setText("-");
                break;
            case 2:
                int subOrAdd = (random.nextInt(2))% 2;
                if(subOrAdd == 1){
                    mTvCal.setText("+");
                }else{
                    mTvCal.setText("-");
                }
                break;
            case 3:
                mTvCal.setText("x");
                break;
            case 4:
                mTvCal.setText("%");
                break;
            case 5:
                if(random.nextInt(2)%2 ==1){
                    mTvCal.setText("x");
                }else{
                    mTvCal.setText("%");
                }
                break;
            case 6:
                int i = random.nextInt(4)%4;
                switch(i){
                    case 0:
                        mTvCal.setText("+");
                        break;
                    case 1:
                        mTvCal.setText("-");
                        break;
                    case 2:
                        mTvCal.setText("x");
                        break;
                    case 3:
                        mTvCal.setText("%");
                        break;
                }
                break;
            case 7:
                mTvEqual.setVisibility(View.GONE);
                mTvKq.setVisibility(View.GONE);
                mTvCal.setText("?");
                mTvCal.setTextColor(Color.RED);
                break;
            case 8:
                mTvEqual.setVisibility(View.GONE);
                mTvKq.setVisibility(View.GONE);
                mTvCal.setText("?");
                mTvCal.setTextColor(Color.RED);
                break;
            default:
                Toast.makeText(getContext(),"Exception!",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
