package com.example.gogamesystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gogamesystem.R;
import com.example.gogamesystem.bean.User;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class UpdataActivity extends AppCompatActivity {

    @BindView(R.id.up_problem)
    EditText upProblem;
    @BindView(R.id.up_answer)
    EditText upAnswer;
    @BindView(R.id.up_newpws)
    EditText upNewpws;
    @BindView(R.id.up_oldpsw)
    EditText upOldpsw;
    @BindView(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatea);
        ButterKnife.bind(this);
    }
    boolean isproblem;
    boolean isanswer;
    @OnClick(R.id.button2)
    public void onClick() {
        BmobQuery<User> user1 = new BmobQuery<>();
        user1.addWhereEqualTo("problem",upProblem.getText().toString().trim());
        user1.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e==null){
                   if (list.size()>=1){
                       isproblem=true;
                   }else {
                       Toast.makeText(UpdataActivity.this, "密保问题和答案不对！", Toast.LENGTH_SHORT).show();
                       isproblem=false;
                   }
                    Log.d("is", "查询成功"+list.size());
                }else {
                }
            }
        });
        BmobQuery<User> user2 = new BmobQuery<>();
        user2.addWhereEqualTo("answer",upAnswer.getText().toString().trim());
        user2.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e==null){
                    if (list.size()>=1){
                        isanswer=true;
                    }else {
                        Toast.makeText(UpdataActivity.this, "密保问题和答案不对！", Toast.LENGTH_SHORT).show();
                        isanswer=false;
                    }
                }else {
                }
            }
        });
        if (isanswer==true&&isproblem==true){
            updata();
        }
        Log.d("is", "isanswer: "+isanswer+"isproblem:"+isproblem);
    }

    public void updata() {
        BmobUser.updateCurrentUserPassword(upOldpsw.getText().toString(), upNewpws.getText().toString(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(UpdataActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdataActivity.this, LoginAcivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(UpdataActivity.this, "旧密码不正确！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
