package hu.zeletrik.daily.exercises.application.presenter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import hu.zeletrik.daily.exercises.R;
import hu.zeletrik.daily.exercises.application.DrawerInterface;
import hu.zeletrik.daily.exercises.application.presenter.fragments.MainFragment;

/**
 * Created by zeletrik on 2018-04-18.
 */
public class MainActivity extends AppCompatActivity implements DrawerInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        Fragment fragment = new MainFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }


    /**
     * ActionBarDrawerToggle szinkronizáció
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        // onRestoreInstanceState utáni szinkronizáció
        super.onPostCreate(savedInstanceState);
    }


    @Override
    public void lockDrawer(){
    }

    @Override
    public void unlockDrawer() {

    }


}