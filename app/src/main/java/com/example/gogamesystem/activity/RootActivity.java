package com.example.gogamesystem.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.gogamesystem.R;
import com.example.gogamesystem.fragment.HomeFragment;
import com.example.gogamesystem.fragment.RootFragment;
import com.example.gogamesystem.fragment.RootInfoFragment;
import com.example.gogamesystem.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import cn.bmob.v3.Bmob;

public class RootActivity extends AppCompatActivity {
    private RootFragment rootFragment;
    private RootInfoFragment rootInfoFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        fragmentManager = getSupportFragmentManager();
        rootFragment = new RootFragment();
        showFragment(rootFragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_root);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Bmob.initialize(this, "2f3e726c9b378464155469629671756b");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.root_add:
                    showFragment(rootFragment);
                    return true;
                case R.id.root_show:
                    if (rootInfoFragment == null)
                        rootInfoFragment = new RootInfoFragment();
                    showFragment(rootInfoFragment);
                    return true;
            }
            return false;
        }
    };

    private void showFragment(Fragment fragment) {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout_root, fragment);
        transaction.commit();
    }
}
