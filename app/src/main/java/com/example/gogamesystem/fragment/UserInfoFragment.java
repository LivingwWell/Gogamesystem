package com.example.gogamesystem.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gogamesystem.R;
import com.example.gogamesystem.bean.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.example.gogamesystem.activity.LoginAcivity.user_name;

public class UserInfoFragment extends Fragment {
    @BindView(R.id.info_user)
    TextView infoUser;
    @BindView(R.id.info_name)
    TextView infoName;
    @BindView(R.id.info_sex)
    TextView infoSex;
    @BindView(R.id.info_age)
    TextView infoAge;
    @BindView(R.id.info_club)
    TextView infoClub;
    @BindView(R.id.info_win)
    TextView infoWin;
  Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereEqualTo("username", user_name);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e == null) {
                   Log.i("bmob", "username：" + user_name+"list:"+list.get(0).toString());
                    Toast.makeText(getActivity(), "查询成功：共" + list.size() + "条数据。", Toast.LENGTH_SHORT).show();
                    infoUser.setText(list.get(0).getUsername());
                    infoName.setText(list.get(0).getName());
                    infoAge.setText(String.valueOf(list.get(0).getAge()));
                    infoClub.setText(list.get(0).getClub());
                    infoSex.setText(list.get(0).getSex());
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
