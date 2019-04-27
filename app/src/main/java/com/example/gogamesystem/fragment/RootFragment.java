package com.example.gogamesystem.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.gogamesystem.GameAdapter;
import com.example.gogamesystem.R;
import com.example.gogamesystem.activity.VsDialog;
import com.example.gogamesystem.bean.Game;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class RootFragment extends Fragment {
    Unbinder unbinder;

    @BindView(R.id.rec_root)
    RecyclerView recyclerView;
    @BindView(R.id.fb)
    FloatingActionButton fb;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @BindView(R.id.constra)
    ConstraintLayout constra;
    private List<Game> gameList;
    private GameAdapter gameAdapter;

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_root, container, false);
        query();
        unbinder = ButterKnife.bind(this, view);
        //下拉刷新
        swiperefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorAccent);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swiperefresh.setRefreshing(false);
                        query();
                    }
                }, 1000);
            }
        });
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
                    gameAdapter.setOnItemClickListener(onItemClickListener);
                    gameAdapter.notifyDataSetChanged();
                } else {
                    Log.d("UserFragment", "Eorr:" + e);
                }
            }
        });

    }

    //长按点击事件
    BaseQuickAdapter.OnItemLongClickListener onItemLongClickListener = new BaseQuickAdapter.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
            return false;
        }
    };
    //点击事件
    BaseQuickAdapter.OnItemClickListener onItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            VsDialog vsDialog=new VsDialog(getActivity());
            vsDialog.show();
        }
    };

    @OnClick(R.id.fb)
    public void onClick() {
        final EditText et = new EditText(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("添加比赛")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        add(et.getText().toString().trim());
                        Snackbar.make(constra, "添加成功！", Snackbar.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", null).show();

    }

    //添加
    private void add(String name) {
        Game game = new Game();
        game.setName(name);
        game.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Log.d("RootFragment", "添加成功");
                } else {
                    Log.d("失败", "done: " + e.getMessage());
                }
            }
        });
        gameAdapter.notifyItemInserted(0);
        recyclerView.smoothScrollToPosition(0);
        gameAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
