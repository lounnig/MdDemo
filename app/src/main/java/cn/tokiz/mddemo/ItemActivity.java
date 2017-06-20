package cn.tokiz.mddemo;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ItemActivity extends AppCompatActivity {

    public static final String ITEM_NAME = "item_name";
    public static final String ITEM_RES_ID = "item_res_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Intent intent = getIntent();
        String item_name = intent.getStringExtra(ITEM_NAME);
        int item_res = intent.getIntExtra(ITEM_RES_ID,0);

        CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.item_toolbar);
        ImageView item_image = (ImageView) findViewById(R.id.item_image);
        TextView item_tv_name = (TextView) findViewById(R.id.item_content_text);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbarLayout.setTitle(item_name);
        Glide.with(this).load(item_res).into(item_image);
        String item_text_context = generateItemName(item_name);
        item_tv_name.setText(item_text_context);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String generateItemName(String name) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i<500 ; i++){
            builder.append(name);
        }
        return builder.toString();
    }

}
