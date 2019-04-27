package com.example.gogamesystem.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.gogamesystem.R;
import com.example.gogamesystem.bean.User;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegActvity extends AppCompatActivity {
    @BindView(R.id.reg_user)
    TextInputLayout regUser;
    @BindView(R.id.reg_pass)
    TextInputLayout regPass;
    @BindView(R.id.reg_name)
    TextInputLayout regName;
    @BindView(R.id.reg_idnum)
    TextInputLayout regIdnum;
    @BindView(R.id.reg_sex)
    RadioGroup regSex;
    @BindView(R.id.reg_age)
    TextInputLayout regAge;
    @BindView(R.id.reg_club)
    TextInputLayout regClub;
    @BindView(R.id.reg_problem)
    TextInputLayout regProblem;
    @BindView(R.id.reg_answer)
    TextInputLayout regAnswer;
    @BindView(R.id.scrollView2)
    ScrollView scrollView2;
    @BindView(R.id.button)
    Button button;
    private String sex = "男";

    @OnCheckedChanged({R.id.man, R.id.woman})
    public void onRadioCheck(CompoundButton view, boolean ischanged) {
        switch (view.getId()) {
            case R.id.man:
                if (ischanged) {
                    sex = "男";
                }
                break;
            case R.id.woman:
                if (ischanged) {
                    sex = "女";
                }
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("BMOB", regUser.getEditText().getText().toString().trim());
                Log.d("BMOB", sex);
                System.out.print(regUser.getEditText().getText().toString().trim());
                save();
            }
        });
    }

    private void save() {
        User user = new User();
        user.setUsername(regUser.getEditText().getText().toString().trim());//用户名
        user.setPassword(regPass.getEditText().getText().toString().trim());//密码
        user.setName(regName.getEditText().getText().toString().trim());//名字
        user.setAge(Integer.parseInt(regAge.getEditText().getText().toString()));//年龄
        user.setClub(regClub.getEditText().getText().toString());//俱乐部
        user.setProblem(regProblem.getEditText().getText().toString());//密保问题
        user.setAnswer(regAnswer.getEditText().getText().toString());//密保答案
        user.setSex(sex);//性别
        user.setID_num(regIdnum.getEditText().getText().toString());//身份证号码
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    Toast.makeText(RegActvity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(RegActvity.this,LoginAcivity.class);
                    startActivity(intent);
                    //  Snackbar.make(button, "新增成功：" +objectId, Snackbar.LENGTH_LONG).show();
                } else {
                    Log.e("BMOB", e.toString());
                    Toast.makeText(RegActvity.this, "注册失败！", Toast.LENGTH_SHORT).show();
                    //  Snackbar.make(button, e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}
