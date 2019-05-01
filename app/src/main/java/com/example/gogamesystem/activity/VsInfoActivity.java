package com.example.gogamesystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gogamesystem.R;
import com.example.gogamesystem.bean.Game;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

import static com.example.gogamesystem.fragment.RootInfoFragment.id_info;

public class VsInfoActivity extends AppCompatActivity {

    @BindView(R.id.tx_title)
    TextView txTitle;
    @BindView(R.id.black_name)
    TextView blackName;
    @BindView(R.id.whlite_name)
    TextView whliteName;
    @BindView(R.id.method_info)
    TextView methodInfo;
    @BindView(R.id.button6)
    Button button6;
    @BindView(R.id.Winer_info)
    TextView WinerInfo;

    public static String vsname;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_vs_dialog);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        BmobQuery<Game> query = new BmobQuery<Game>();
        query.getObject(id_info, new QueryListener<Game>() {
            @Override
            public void done(Game game, BmobException e) {
                if (e == null) {
                    txTitle.setText(game.getName());
                    blackName.setText(game.getBlack());
                    whliteName.setText(game.getWhite());
                    methodInfo.setText(game.getMethod());
                    WinerInfo.setText(game.getVictory());
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    @OnClick(R.id.button6)
    public void onClick() {
        finish();
    }

    @OnClick({R.id.black_name, R.id.whlite_name})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.black_name:
                vsname=blackName.getText().toString();
                info();
                break;
            case R.id.whlite_name:
                vsname=whliteName.getText().toString();
                info();
                break;
        }
    }

    public void info() {
        Intent intent = new Intent(VsInfoActivity.this, UserInfoActivity.class);
        startActivity(intent);
    }
}
