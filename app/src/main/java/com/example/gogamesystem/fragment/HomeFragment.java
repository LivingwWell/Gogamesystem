package com.example.gogamesystem.fragment;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.gogamesystem.NewsAdapter;
import com.example.gogamesystem.R;
import com.example.gogamesystem.bean.News;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private  List<News> newsList;
    private NewsAdapter newsAdapter;

    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rec_home);
        initData();
        newsAdapter =new NewsAdapter(R.layout.item_home,newsList);
//       LinearLayoutManager layoutManager=new LinearLayoutManager(this.getActivity());
//       layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//       recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();
        Log.d("HomeFragment", "onCreateView: "+newsList);
        return  view;
    }

    private void initData() {
        newsList=new ArrayList<>();
        News news1=new News();
        news1.setTitle("倡棋杯柯洁逆转连笑首次进四强杨鼎新完胜上届冠军");
        news1.setDate("2019-04-23 18:03:57");
        news1.setImg(R.drawable.one);
        News news2=new News();
        news2.setTitle("世界女子擂台赛第一阶段结束中国队4胜1负领先日韩");
        news2.setDate("2019-04-23 18:03:57");
        news2.setImg(R.drawable.two);
        News news3=new News();
        news3.setTitle("颜值在线世界女子擂台赛中国美女棋手高星胜韩选手");
        news3.setDate("2019-04-21 19:01:51");
        news3.setImg(R.drawable.three);
        News news4=new News();
        news4.setTitle("耿直boy柯洁讽刺流量明星:用户比全世界人民都多！");
        news4.setDate("2019-02-23 09:15:11");
        news4.setImg(R.drawable.zero);
        for (int i=0;i<=20;i++){
            newsList.add(news1);
            newsList.add(news2);
            newsList.add(news3);
            newsList.add(news4);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
