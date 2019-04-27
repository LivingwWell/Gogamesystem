package com.example.gogamesystem;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gogamesystem.bean.Game;

import java.util.List;

import androidx.annotation.Nullable;

public class GameAdapter extends BaseQuickAdapter<Game, BaseViewHolder> {
    public GameAdapter(int layoutResId, @Nullable List<Game> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Game item) {
                helper.setText(R.id.user_game,item.getName());
    }
}
