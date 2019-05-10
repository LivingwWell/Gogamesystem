package com.example.gogamesystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.gogamesystem.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginAcivity extends AppCompatActivity {
    @BindView(R.id.layoutname)
    TextInputLayout layoutname;
    @BindView(R.id.layoutpsw)
    TextInputLayout layoutpsw;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_registered)
    Button btRegistered;
    @BindView(R.id.textView)
    TextView textView;
    public static String user_name;
    @BindView(R.id.constraint)
    ConstraintLayout constraint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Bmob.initialize(this, "2f3e726c9b378464155469629671756b");
    }

    @OnClick({R.id.bt_login, R.id.bt_registered, R.id.textView})
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.bt_login) {
            if (layoutname.getEditText().getText().toString().trim().equals("root") &&
                    layoutpsw.getEditText().getText().toString().trim().equals("root")) {
                Intent intent = new Intent(LoginAcivity.this, RootActivity.class);
                startActivity(intent);
            }
            login();
        } else if (viewId == R.id.bt_registered) {
            Intent intent = new Intent(LoginAcivity.this, RegActvity.class);
            startActivity(intent);
        } else if (viewId == R.id.textView) {
            Intent intent2 = new Intent(LoginAcivity.this, UpdataActivity.class);
            startActivity(intent2);
        }
    }

    public void login() {
        BmobUser user = new BmobUser();
        user.setUsername(layoutname.getEditText().getText().toString());
        user.setPassword(layoutpsw.getEditText().getText().toString());
        user.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    user_name = layoutname.getEditText().getText().toString();
                    Intent intent = new Intent(LoginAcivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Snackbar.make(constraint, "密码或用户名不对", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }


}
