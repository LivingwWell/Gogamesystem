package com.example.gogamesystem.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.gogamesystem.R;
import com.example.gogamesystem.bean.Game;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTouch;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import static com.example.gogamesystem.fragment.RootFragment.getid;
import static com.example.gogamesystem.fragment.RootFragment.title1;

public class VsDialog extends Dialog {
    @BindView(R.id.black_info)
    EditText blackName;
    @BindView(R.id.whlite_info)
    EditText whliteName;
    @BindView(R.id.radioButton)
    RadioButton radioButton;
    @BindView(R.id.radioButton2)
    RadioButton radioButton2;
    @BindView(R.id.VroD)
    RadioGroup VroD;
    @BindView(R.id.method_info)
    EditText method;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.tx_title)
    TextView txTitle;
    @BindView(R.id.tx_date)
    TextView txDate;

    private String radio1;
    private String radio2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_vs, null);
        setContentView(view);
        ButterKnife.bind(this);
        // txTitle=findViewById(R.id.tx_title);
        txTitle.setText(title1);
        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.height = 1920;
        lp.width = 1080;

        Date date = new Date(System.currentTimeMillis());
        txDate.setText(getTime(date));
        win.setAttributes(lp);
    }


    public VsDialog(@NonNull Context context) {
        super(context);
    }


    public VsDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }


    protected VsDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    private void setGame() {
        Game game = new Game();
        game.setBlack(blackName.getText().toString());
        game.setWhite(whliteName.getText().toString());
        game.setDate(txDate.getText().toString());
        game.setVictory(radio1);
        game.setDefeat(radio2);
        game.setMethod(method.getText().toString());
        game.update(getid, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i("bmob", "更新成功");
                } else {
                    Log.i("bmob", "更新失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    @OnCheckedChanged({R.id.radioButton, R.id.radioButton2})
    public void RadioCheck(CompoundButton view, boolean ischanged) {
        switch (view.getId()) {
            case R.id.radioButton:
                radio1 = blackName.getText().toString();
                radio2 = whliteName.getText().toString();
                break;
            case R.id.radioButton2:
                radio1 = whliteName.getText().toString();
                radio2 = blackName.getText().toString();
                break;
        }
    }


    @OnClick({R.id.button4, R.id.tx_date})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button4:
                setGame();
                dismiss();
                break;
            case R.id.tx_date:
                TimePickerView pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        txDate.setText(getTime(date));
                    }
                })
                        .setType(new boolean[]{true, true, true, true, true, true})//分别对应年月日时分秒，默认全部显示
                        .isDialog(true)
                        .build();

                Dialog mDialog = pvTime.getDialog();
                if (mDialog != null) {
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            Gravity.BOTTOM);

                    params.leftMargin = 0;
                    params.rightMargin = 0;
                    pvTime.getDialogContainerLayout().setLayoutParams(params);

                    Window dialogWindow = mDialog.getWindow();
                    if (dialogWindow != null) {
                        dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                        dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                    }
                }
                pvTime.show();
                break;
        }
    }

    @OnTouch({R.id.black_info, R.id.whlite_info})
    public boolean onTouch(View view, MotionEvent event) {
        switch (view.getId()) {
            case R.id.black_info:
                     blackName.setText("小李");
                break;
            case R.id.whlite_info:
              whliteName.setText("小张");
                break;
        }
        return true;
    }
}
