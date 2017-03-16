package com.betterclever.aparoksha.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.betterclever.aparoksha.R;
import com.betterclever.aparoksha.fragments.TeamFragment;
import com.betterclever.aparoksha.fragments.HomeFragment;
import com.betterclever.aparoksha.fragments.UpdatesFragment;

public class HomeActivity extends AppCompatActivity {
    
    private static final String TAG = HomeActivity.class.getSimpleName();
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    
    Fragment homeFragment,developersFragment, updatesFragment;
    
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
        = new BottomNavigationView.OnNavigationItemSelectedListener() {
        
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_team:
                    setFragment(developersFragment);
                    return true;
                case R.id.navigation_home:
                    setFragment(homeFragment);
                    return true;
                case R.id.navigation_updates:
                    setFragment(updatesFragment);
                    return true;
            }
            return false;
        }
        
    };
    
    private void assignViews() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        assignViews();
        setSupportActionBar(toolbar);
        init();
    }
    
    private void init() {
        homeFragment = HomeFragment.newInstance();
        developersFragment = TeamFragment.newInstance();
        updatesFragment = UpdatesFragment.newInstance();
    
        setFragment(homeFragment);
        
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
    }
    
    private void setFragment(Fragment f){
        FragmentManager fm = getSupportFragmentManager();
        
        if(fm.getFragments()==null){
            fm.beginTransaction().add(R.id.frame,f).commit();
            return;
        }
        
        if(fm.getFragments().contains(f)){
            Log.d(TAG, "setFragment() called with: f = [" + f + "]");
            FragmentTransaction ft = fm.beginTransaction();
            for(Fragment fragment: fm.getFragments()){
                if(fragment!=null){
                    ft.hide(fragment);
                }
            }
            ft.show(f);
            ft.commit();
        }
        else {
            FragmentTransaction ft = fm.beginTransaction();
            for(Fragment fragment: fm.getFragments()){
                if(fragment!=null){
                    ft.hide(fragment);
                }
            }
            ft.add(R.id.frame,f);
            ft.commit();
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId()){
            case R.id.action_sponsers:
                startActivity(new Intent(this, SponsorsActivity.class));
                break;
            case R.id.action_about_us:
                break;
        }
        
        return super.onOptionsItemSelected(item);
    }
}
