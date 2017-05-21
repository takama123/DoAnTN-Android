package com.example.computer.doantn.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.computer.doantn.R;
import com.example.computer.doantn.bean.GameLevelBean;

import java.util.ArrayList;

/**
 * Created by Computer on 5/1/2017.
 */

public class GameLevelAdapter extends RecyclerView.Adapter<GameLevelAdapter.GameLevelViewHolder>{

    private ArrayList<GameLevelBean> mListData;
    public Context mContext;
    private LayoutInflater mLayoutInflater;
    private CallBack callBack;

    public GameLevelAdapter(Context context, ArrayList<GameLevelBean> listData){
        this.mContext = context;
        this.mListData = listData;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public GameLevelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list_level, parent, false);
        return new GameLevelViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GameLevelViewHolder holder, int position) {
        GameLevelBean gameLevel = mListData.get(position);

        holder.mButton.setText(gameLevel.getTitleLevel());

    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    class GameLevelViewHolder extends RecyclerView.ViewHolder{

        private Button mButton;

        public GameLevelViewHolder(View itemView) {
            super(itemView);
            mButton = (Button) itemView.findViewById(R.id.imgBtnItemListLevel);

            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onItemClick(getLayoutPosition());
                }
            });
        }
    }

    public interface CallBack{
        void onItemClick(int position);
    }
}
