package hu.zeletrik.a5exercisesbodytransform;

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

/**
 * Created by zeletrik on 2018-04-18.
 */
public class MainActivity extends AppCompatActivity implements DrawerInterface {


    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Toolbar az ActionBar helyére
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // DrawerLayout beálíltása
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Toogle beállítása
        mDrawer.addDrawerListener(drawerToggle);

        // DrawerView beállítása
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);


        Fragment fragment = new MainFragment();
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

  private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            default:
                break;
        }

        // Kiválasztott kijelölése
        menuItem.setChecked(true);
        // Cím beállítás
        setTitle(menuItem.getTitle());
        // Drawer bezárása
        mDrawer.closeDrawers();
    }

    /**
     * ActionBarDrawerToggle szinkronizáció
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        // onRestoreInstanceState utáni szinkronizáció
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public void lockDrawer() {
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void unlockDrawer() {
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }


}