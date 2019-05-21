package com.w4675.bangumi;

import android.app.ActivityManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private TabLayout tl;
    private ViewPager vp;
    private SQLiteDatabase db=null;
    private MyOpenHelper helper=null;

    //当标签数目小于等于4个时，标签栏不可滑动
    public static final int MOVABLE_COUNT = 4;

    private int tabCount = 6;
    private List<String> tabs;
    private List<Fragment> fragments;
    private Intent intent = null;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    //   creatTable();   //创建数据库
    //    tl = findViewById(R.id.tably);
        vp = findViewById(R.id.viewpager);
        helper = new MyOpenHelper(this);
        db=helper.getReadableDatabase();
        displayView(R.id.shouye);     //初始页面
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

     //   initDatas();
     //   initViewPager();
     //   initTabLayout();

        /*    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Example of a call to a native method
       // TextView tv = (TextView) findViewById(R.id.sample_text);
    }
    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            //彻底关闭整个APP
            int currentVersion = android.os.Build.VERSION.SDK_INT;
            if (currentVersion > android.os.Build.VERSION_CODES.ECLAIR_MR1) {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
                System.exit(0);
            } else {// android2.1
                ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                am.restartPackage(getPackageName());
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displayView(int viewId) {

        String title = getString(R.string.app_name);   //初始化标题栏
        Shouye fragment1 = null;     //第一个界面
        Hujiao fragment3 = null;       //第三个界面
        Zixun fragment2 = null;     //第四个界面
        Search chazhao=null;        //查询页面

        //    fragments = new ArrayList<>();
        switch (viewId) {
            case R.id.shouye:
                fragment1 = new Shouye();
                title  = "首页";
                break;
            case R.id.jiankong:
            //    title = "监控";
                if(intent==null) {
                    intent = new Intent();
                }
                intent.setClass(MainActivity.this, Jiankong.class);
                startActivity(intent);
                break;
            case R.id.hujiao:
                fragment3 = new Hujiao();
                title = "呼叫";
                break;
            case R.id.zixun:
                fragment2 =new Zixun();
                title = "资讯";
                break;
            case R.id.search:
                chazhao = new Search();
                title  = "查询";
                break;

        }

        if (fragment1 != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.cont_frame, fragment1);
            ft.commit();
        }



        if (fragment3 != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.cont_frame, fragment3);
            ft.commit();
        }

        if (fragment2 != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.cont_frame, fragment2);
            ft.commit();
        }
        if (chazhao != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.cont_frame, chazhao);
            ft.commit();
        }

        // set the toolbar title  更改标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }
    @Override
   public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
      /*  int id = item.getItemId();

        if (id == R.id.shouye) {
            // Handle the camera action
        } else if (id == R.id.jiankong) {

        } else if (id == R.id.hujiao) {

        } else if (id == R.id.zixun) {

        }

       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        */
      displayView(item.getItemId());
      return true;
    }

    private void initTabLayout() {
        //MODE_FIXED标签栏不可滑动，各个标签会平分屏幕的宽度
        tl.setTabMode(tabCount <= MOVABLE_COUNT ? TabLayout.MODE_FIXED : TabLayout.MODE_SCROLLABLE);
        //指示条的颜色
        tl.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.holo_blue_dark));
        //关联tabLayout和ViewPager,两者的选择和滑动状态会相互影响
        tl.setupWithViewPager(vp);
        //自定义标签布局
        for (int i = 0; i < tabs.size(); i++) {
            TabLayout.Tab tab = tl.getTabAt(i);
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.tabview_main, tl, false);
            tv.setText(tabs.get(i));
            tab.setCustomView(tv);
        }
    }

    private void initViewPager() {
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }

    private void initDatas() {
        tabs = new ArrayList<>();
        tabs.add(0,"日");
        tabs.add(1,"月");
        tabs.add(2,"火");
        tabs.add(3,"水");
        tabs.add(4,"木");
        tabs.add(5,"金");
        tabs.add(6,"土");

        /*for (int i = 0; i < tabCount; i++) {
            tabs.add("标签" + i);
        }*/

        fragments = new ArrayList<>();
        for (int i = 0; i < tabs.size(); i++) {
            fragments.add(TabContentFragment.newInstance(tabs.get(i)));
        }

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    public void creatTable(){
        /*判断数据库文件是否存在
        不存在的话创建数据库文件
         */
        File file = new File("data/data/com.w4675.bangumi/databases/infor.db");
        if(!file.exists())
        {
            db.execSQL("create table information(" +
                    "id integer primary key autoincrement, " +
                    "name varchar(100)," +
                    "sex varchar(150)," +
                    "years varchar(100)," +
                    "address varchar(400)," +
                    "telephone varchar(100)," +
                    "feiyong varchar(100000))"
            );
            Log.v("tag", "create table succeed");
        }

    }
}
