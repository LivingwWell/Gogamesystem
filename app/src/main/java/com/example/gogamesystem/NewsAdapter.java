package com.example.gogamesystem;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.gogamesystem.bean.News;

import java.util.List;


public class NewsAdapter extends BaseQuickAdapter<News, BaseViewHolder> {
    public NewsAdapter(int layoutResId, List<News> data) {
        super(layoutResId,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, News item) {
         helper.setText(R.id.news_title,item.getTitle());
         helper.setText(R.id.news_date,item.getDate());
         helper.setImageResource(R.id.news_image,item.getImg());
    }


}
