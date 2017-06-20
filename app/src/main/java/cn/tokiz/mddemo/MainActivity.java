package cn.tokiz.mddemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private Item[] items = {
            new Item(R.mipmap.item1,"Steins;Gate 1"),
            new Item(R.mipmap.item2,"Steins;Gate 2"),
            new Item(R.mipmap.item3,"Steins;Gate 3"),
            new Item(R.mipmap.item4,"Steins;Gate 4"),
            new Item(R.mipmap.item5,"Steins;Gate 5"),
            new Item(R.mipmap.item6,"Steins;Gate 6"),
            new Item(R.mipmap.item7,"Steins;Gate 7"),
            new Item(R.mipmap.item8,"Steins;Gate 8"),
            new Item(R.mipmap.item9,"Steins;Gate 9"),
            new Item(R.mipmap.item10,"Steins;Gate 10"),
            new Item(R.mipmap.item11,"Steins;Gate 11"),
            new Item(R.mipmap.item12,"Steins;Gate 12"),
    };
    private List<Item> itemList = new ArrayList<>();
    private ItemAdapter itemAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initItems();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }
        mNavigationView.setCheckedItem(R.id.home);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(layoutManager);
        itemAdapter = new ItemAdapter(itemList);
        mRecyclerView.setAdapter(itemAdapter);

        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.search :

                break;
            case R.id.info :

                break;
            case R.id.setting :

                break;
        }

        return true;
    }
    @OnClick(R.id.fab)void fab_click(){
        Snackbar.make(floatingActionButton,"删除数据成功",Snackbar.LENGTH_LONG)
                .setAction("撤销", new View.OnClickListener() {
                    @Override
                   public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "已撤销", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private void initItems(){
        itemList.clear();
        for (int i = 0 ; i <50 ; i++){
            Random random = new Random();
            int index = random.nextInt(items.length);
            itemList.add(items[index]);
        }

    }
    private void refreshItems(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initItems();
                        itemAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}
