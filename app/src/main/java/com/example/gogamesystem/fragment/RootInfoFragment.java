package com.example.gogamesystem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.gogamesystem.GameAdapter;
import com.example.gogamesystem.R;
import com.example.gogamesystem.activity.VsDialog;
import com.example.gogamesystem.activity.VsInfoActivity;
import com.example.gogamesystem.bean.Game;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class RootInfoFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.rec_info)
    RecyclerView recInfo;
    private List<Game> gameList;
    private GameAdapter gameAdapter;
    public static String title_info;
    public static String id_info;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rootinfo, container, false);
        unbinder = ButterKnife.bind(this, view);
        query();
        return view;
    }

    //查询
    private void query() {
        BmobQuery<Game> bmobQuery = new BmobQuery<Game>();
        bmobQuery.order("-createdAt");
        bmobQuery.findObjects(new FindListener<Game>() {
            @Override
            public void done(List<Game> list, BmobException e) {
                if (e == null) {
                    gameList = new ArrayList<>();
                    gameList.addAll(list);
                    gameAdapter = new GameAdapter(R.layout.item_user, gameList);
                    recInfo.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recInfo.setAdapter(gameAdapter);
                    Log.d("UserFragment", "Game:" + list.toString());
                    gameAdapter.setOnItemClickListener(onItemClickListener);
                    gameAdapter.notifyDataSetChanged();
                } else {
                    Log.d("UserFragment", "Eorr:" + e);
                }
            }
        });
    }

    //点击事件
    BaseQuickAdapter.OnItemClickListener onItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            title_info=gameList.get(position).getName();
            id_info=gameList.get(position).getObjectId();
            Intent intent=new Intent(getActivity(), VsInfoActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
