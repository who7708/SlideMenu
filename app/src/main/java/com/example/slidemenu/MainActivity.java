package com.example.slidemenu;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private DrawerLayout drawerLayout;
    private FrameLayout flContent;
    private ListView lv;
    private ActionBarDrawerToggle drawerToggle;

    private List<String> datas;
    private ArrayAdapter<String> adapter;
    private String title;

    private String[] cities = {"上海", "北京", "深圳", "广州", "杭州"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 开启 actionbar 显示菜单控制开关
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // 左上角的返回按钮是不显示，
            actionBar.setDisplayHomeAsUpEnabled(true);
            // 菜单控制开关点击事件能够响应
            actionBar.setHomeButtonEnabled(true);
            // 替换左上角的左返回箭头设置为 menu ? 替换不掉
            actionBar.setDisplayUseLogoEnabled(true);
            // actionBar.setDisplayShowTitleEnabled(false);
            // actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
            // actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
            // actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME);
            actionBar.setIcon(R.drawable.menu);
            actionBar.setLogo(R.drawable.menu);
            // 设置 actionBar 的 title 默认为 app_name
            actionBar.setTitle("请选择城市");
        }

        title = (String) getTitle();

        drawerLayout = findViewById(R.id.drawer_ly);
        flContent = findViewById(R.id.flContent);
        lv = findViewById(R.id.lv);

        datas = new ArrayList<>();
        datas.addAll(Arrays.asList(cities));

        // 绑定适配器
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

        // 创建菜单控制开关
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(title);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Objects.requireNonNull(getSupportActionBar()).
                setTitle("请选择城市");
                invalidateOptionsMenu();
            }
        };

        // setDrawerListener 已过期
        // 使用 addDrawerListener 来代替增加监听
        // 使用 removeDrawerListener 移除监听
        // drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.addDrawerListener(drawerToggle);
        // drawerLayout.removeDrawerListener(drawerToggle);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        String city = datas.get(position);
        setTitle(city);
        args.putString("text", city);
        fragment.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContent, fragment)
                .commit();
        // 关闭菜单
        drawerLayout.closeDrawer(lv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        // return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean isDrawOpen = drawerLayout.isDrawerOpen(lv);
        menu.findItem(R.id.action_websearch).setVisible(!isDrawOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 屏蔽 drawerToggle 点击事件
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() == R.id.action_websearch) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri uri = Uri.parse("https://www.baidu.com");
            intent.setData(uri);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

        // 将 drawerToggle 与 drawerLayout 状态同步
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}