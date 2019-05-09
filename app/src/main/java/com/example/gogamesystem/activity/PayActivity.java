package com.example.gogamesystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gogamesystem.R;
import com.example.gogamesystem.bean.Game;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

import static com.example.gogamesystem.activity.LoginAcivity.user_name;

public class PayActivity extends AppCompatActivity {
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.button3)
    Button button3;
    private  String gameName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        gameName= (String) intent.getSerializableExtra("GameName");
        Log.d("PayActivity", "GameName: "+gameName+user_name);
    }

    @OnClick(R.id.button3)
    public void onClick() {
        Toast.makeText(PayActivity.this,"支付成功！",Toast.LENGTH_SHORT).show();
        Signup();
        finish();
    }


    public void Signup(){
        BmobQuery<Game> query=new BmobQuery<Game>();
        query.addWhereEqualTo("name",gameName);
        query.findObjects(new FindListener<Game>() {
            @Override
            public void done(List<Game> list, BmobException e) {
                if (e==null){
                    Log.i("PayActivity", "成功：" +list.get(0).toString());
                    String id=list.get(0).getObjectId();
                    Game game=new Game();
                    game.setBlack(user_name);
                    game.update(id, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e==null){
                                Log.i("PayActivity", "成功：" +game.getBlack());
                            }else {
                                Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                            }
                        }
                    });
                    Log.i("PayActivity", "成功：" +list.get(0).toString());
                }else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }
}
