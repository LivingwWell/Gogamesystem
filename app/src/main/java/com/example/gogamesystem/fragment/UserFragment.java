package com.example.gogamesystem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.gogamesystem.GameAdapter;
import com.example.gogamesystem.R;
import com.example.gogamesystem.activity.PayActivity;
import com.example.gogamesystem.bean.Game;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class UserFragment extends Fragment {
    private RecyclerView recyclerView;
    private GameAdapter gameAdapter;
    private List<Game> gameList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        recyclerView = view.findViewById(R.id.rec_user);
        query();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(gameAdapter);
                    Log.d("UserFragment", "Game:" + list.toString());
                    gameAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                            Intent intent = new Intent(getActivity(), PayActivity.class);
                            intent.putExtra("GameName",gameList.get(position).name);
                            startActivity(intent);
                        }
                    });
                }else {
                    Log.d("UserFragment", "Eorr:" +e);
                }
            }
        });

    }

}
