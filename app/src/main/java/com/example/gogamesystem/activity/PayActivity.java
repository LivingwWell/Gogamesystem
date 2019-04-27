package com.example.gogamesystem.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gogamesystem.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends AppCompatActivity {
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.button3)
    Button button3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button3)
    public void onClick() {
        Toast.makeText(PayActivity.this,"支付成功！",Toast.LENGTH_SHORT).show();
        finish();
    }
}
