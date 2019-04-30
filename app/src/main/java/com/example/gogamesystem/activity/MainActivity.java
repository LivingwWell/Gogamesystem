package com.example.gogamesystem.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;

import com.example.gogamesystem.fragment.HomeFragment;
import com.example.gogamesystem.R;
import com.example.gogamesystem.fragment.RootFragment;
import com.example.gogamesystem.fragment.UserFragment;
import com.example.gogamesystem.fragment.UserInfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {
    private UserFragment userfragment;
    private RootFragment rootfragment;
    private HomeFragment homefragment;
    private FragmentTransaction transaction;
    private UserInfoFragment userInfoFragment;;
    private FragmentManager manager;

    private int lastfragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showFragment(homefragment);
                    return true;
                case R.id.navigation_dashboard:
                    if (userfragment == null)
                        userfragment = new UserFragment();
                    showFragment(userfragment);
                    return true;
                case R.id.navigation_notifications:
                    if (userInfoFragment == null)
                        userInfoFragment = new UserInfoFragment();
                    showFragment(userInfoFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        homefragment = new HomeFragment();
        showFragment(homefragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_user);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Bmob.initialize(this, "2f3e726c9b378464155469629671756b");
    }

    private void showFragment(Fragment fragment) {
        transaction = manager.beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        /*判断当前显示的fragment 将它隐藏
         * 有这一步是为了不引起冲突
         * 但我使用replace方法显示 没有冲突的情况
         * */
        if (!homefragment.isHidden())
            transaction.hide(homefragment);
        else if (!userfragment.isHidden())
            transaction.hide(userfragment);
        else if (!rootfragment.isHidden())
            transaction.hide(rootfragment);
    }


}
