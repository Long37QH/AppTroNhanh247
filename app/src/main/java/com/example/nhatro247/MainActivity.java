package com.example.nhatro247;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.nhatro247.fragments.DoiPassFragment;
import com.example.nhatro247.fragments.HomeFragment;
import com.example.nhatro247.fragments.LapPhieuFragment;
import com.example.nhatro247.fragments.ThongKeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDraweLayout;
    NavigationView navigationView;

    // khai bao cac bien luu vi tri fragment

    private static int FRAGMENT_HOME = 0;
    private static int FRAGMENT_LAPPHIEU = 1;
    private static int FRAGMENT_THONGKE = 2;
    private static int FRAGMENT_DOIPASS = 3;

    // khai bao bien kiem tra vi tri fragment

    private int mCurentFragment = FRAGMENT_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // khai bao them toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDraweLayout = findViewById(R.id.drawer_layout);
        //tao action mo menu left
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDraweLayout,toolbar,R.string.open_navigation_drawer,R.string.close_navigation_drawer);
        mDraweLayout.addDrawerListener(toggle);
        toggle.syncState();

        //anh xa menu trai bat suj kiem click item trong menu
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        // xu ly mo menu tu fragment home
        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.nav_home){

            if (mCurentFragment != FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                mCurentFragment = FRAGMENT_HOME;
            }
            
        } else if (id == R.id.nav_lapphieu) {

            if (mCurentFragment != FRAGMENT_LAPPHIEU){
                replaceFragment(new LapPhieuFragment());
                mCurentFragment = FRAGMENT_LAPPHIEU;
            }
            
        }else if (id == R.id.nav_thongke) {

            if (mCurentFragment != FRAGMENT_THONGKE){
                replaceFragment(new ThongKeFragment());
                mCurentFragment = FRAGMENT_THONGKE;
            }

        }else if (id == R.id.nav_doipass) {

            if (mCurentFragment != FRAGMENT_DOIPASS){
                replaceFragment(new DoiPassFragment());
                mCurentFragment = FRAGMENT_DOIPASS;
            }

        }else if (id == R.id.nav_dangxuat) {

        }

        // sau khi mo fragment thi dong menu
        mDraweLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    // su li click nut back khi mo menu khong bi dong app
    @Override
    public void onBackPressed() {

        if (mDraweLayout.isDrawerOpen(GravityCompat.START)){
            mDraweLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    // xu ly chen fragment vao activity
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }
}