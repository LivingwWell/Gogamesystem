package com.example.gogamesystem.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.gogamesystem.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class VsDialog extends Dialog {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view=View.inflate(getContext(), R.layout.dialog_vs,null);
        setContentView(view);
        Window win=getWindow();
        WindowManager.LayoutParams lp=win.getAttributes();
        lp.gravity= Gravity.CENTER;
        lp.height=1920;
        lp.width=1080;
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
}
