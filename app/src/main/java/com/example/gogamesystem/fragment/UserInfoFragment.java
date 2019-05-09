package com.example.gogamesystem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gogamesystem.R;
import com.example.gogamesystem.activity.LoginAcivity;
import com.example.gogamesystem.bean.Game;
import com.example.gogamesystem.bean.User;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.info_qifeng)
    TextView infoQifeng;
    @BindView(R.id.info_game)
    TextView infoGame;
    @BindView(R.id.infosex)
    TextView infosex;
    Unbinder unbinder;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @OnClick(R.id.button5)
    public void onClick() {
        Intent intent = new Intent(UserInfoFragment.this.getActivity(), LoginAcivity.class);
        startActivity(intent);
    }

    private void initView() {
        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereEqualTo("username", user_name);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (e == null) {
                    Log.i("bmob", "username：" + user_name + "list:" + list.get(0).toString());
                    infoUser.setText(list.get(0).getUsername());
                    infoName.setText(list.get(0).getName());
                    infoAge.setText(String.valueOf(list.get(0).getAge()));
                    infoClub.setText(list.get(0).getClub());
                    infoSex.setText(list.get(0).getSex());
                    getwin();
                    infoQifeng.setText(getMethod());
                    getGame();
                    getSex();
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    int winnum;
    int defeatnum;

    //胜率查询
    public void getwin() {
        BmobQuery<Game> victory = new BmobQuery<Game>();
        victory.addWhereEqualTo("victory", infoUser.getText().toString());
        victory.findObjects(new FindListener<Game>() {
            @Override
            public void done(List<Game> list, BmobException e) {
                winnum = list.size();
                BmobQuery<Game> defeat = new BmobQuery<Game>();
                defeat.addWhereEqualTo("defeat", infoUser.getText().toString());
                defeat.findObjects(new FindListener<Game>() {
                    @Override
                    public void done(List<Game> list, BmobException e) {
                        defeatnum = list.size();
                        NumberFormat numberFormat = NumberFormat.getInstance();
                        numberFormat.setMaximumFractionDigits(2);
                        String result = numberFormat.format((float) winnum / (float) (winnum + defeatnum) * 100);
                        Log.d("UserInfoActivity", "winnum: " + winnum + "defeatnum:" + defeatnum);
                        infoWin.setText(result + "%");
                    }
                });
            }
        });
        //这里你查询了Victory 后还要在查询词defeat 最后才得出结果这样才是正确的,这也是按照逻辑的方法走的,该怎么去包装就得要想一下了
    }

    //查询男女比例
    public void getSex() {
        BmobQuery<User> man = new BmobQuery<User>();
        man.addWhereEqualTo("sex", "男");
        man.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                int mannum = list.size();
                BmobQuery<User> woman = new BmobQuery<User>();
                woman.addWhereEqualTo("sex", "女");
                woman.findObjects(new FindListener<User>() {
                    @Override
                    public void done(List<User> list, BmobException e) {
                        int womanum = list.size();
                        NumberFormat numberFormat = NumberFormat.getInstance();
                        numberFormat.setMaximumFractionDigits(2);
                        String result = numberFormat.format((float) mannum / (float) (mannum + womanum) * 100);
                        Log.d("UserInfoActivity", "winnum: " + mannum + "defeatnum:" + womanum);
                        infosex.setText("男生为" + result + "%");
                    }
                });
            }
        });
    }

    int zhongnum;
    int guannum;
    String method;

    //棋风查询
    public String getMethod() {
        BmobQuery<Game> zhong = new BmobQuery<Game>();
        zhong.addWhereEqualTo("victory", infoUser.getText().toString());
        zhong.findObjects(new FindListener<Game>() {
            @Override
            public void done(List<Game> list, BmobException e) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getMethod().equals("中局胜利")) {
                        zhongnum = zhongnum + 1;
                    } else {
                        guannum = guannum + 1;
                    }
                }
            }
        });

        if (zhongnum > guannum) {
            method = "中局胜";
        } else {
            method = "关子胜";
        }
        return method;
    }


    //查询参加过的比赛
    public void getGame() {
        StringBuffer buf = new StringBuffer();
        BmobQuery<Game> eq1 = new BmobQuery<>();
        eq1.addWhereEqualTo("black", infoUser.getText());
        BmobQuery<Game> eq2 = new BmobQuery<>();
        eq2.addWhereEqualTo("white", infoUser.getText());
        List<BmobQuery<Game>> andQuerys = new ArrayList<BmobQuery<Game>>();
        andQuerys.add(eq1);
        andQuerys.add(eq2);
        BmobQuery<Game> query = new BmobQuery<>();
        query.or(andQuerys);
        query.findObjects(new FindListener<Game>() {
            @Override
            public void done(List<Game> list, BmobException e) {
                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {
                        Log.i("getGame", "参加过的比赛：" + list.get(i).getName());
                        if (list.get(i).getVictory().equals(user_name)) {
                            buf.append(list.get(i).getName() + "\n" + "胜" + "\n");
                        } else {
                            buf.append(list.get(i).getName() + "\n" + "负" + "\n");
                        }

                        infoGame.setText(buf.toString());
                    }
                    Log.i("getGame", "buf：" + buf.toString());
                } else {
                    Log.i("getGame", "失败：" + e.getMessage() + "," + e.getErrorCode());
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
