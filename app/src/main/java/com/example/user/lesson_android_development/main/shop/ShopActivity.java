package com.example.user.lesson_android_development.main.shop;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.lesson_android_development.R;
import com.example.user.lesson_android_development.data.Tag;
import com.example.user.lesson_android_development.databinding.ShopActBinding;
import com.example.user.lesson_android_development.main.description.DescriptionActivity;
import com.example.user.lesson_android_development.main.filter.FilterActivity;
import com.example.user.lesson_android_development.util.ActivityUtils;
import com.example.user.lesson_android_development.util.ViewModelFactory;

import static com.example.user.lesson_android_development.BR.product;


public class ShopActivity extends AppCompatActivity {

    private static final String TAG = ShopActivity.class.getSimpleName();
    private ShopViewModel mShopViewModel;
    private ShopActBinding mShopActBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_act);

        mShopViewModel = ViewModelFactory.obtainViewModel(this, ShopViewModel.class);
        mShopActBinding = DataBindingUtil.setContentView(this, R.layout.shop_act);


        //Setup
        setupToolbar();
        setupFragment();
        setupEvents();
    }

    /**
     * Setting up the toolbar, toolbar actions & title
     */
    private void setupToolbar() {
        setSupportActionBar(mShopActBinding.tbMain);

    }

    /**
     * OnClickListener for the toolbar back button
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * setting overflow menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_contacts:
                FilterActivity.startActivity(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     *recive data and add in getFilterItem
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FilterActivity.RC_FILTER && resultCode == RESULT_OK) {
            Log.e(TAG, String.valueOf(data.getExtras().get("filter")));

            mShopViewModel.getFilterItem(data.getExtras().getLong("filter"));

        }

    }

    /**
     * Fragment
     */
    private void setupFragment() {
        ShopFragment shopFragment = (ShopFragment) getSupportFragmentManager().findFragmentById(mShopActBinding.fragShop.getId());
        if (shopFragment == null){
            shopFragment = ShopFragment.newInstance(getIntent().getIntExtra("id",0));
            ActivityUtils.replaceFragmentInActivity(
                    getSupportFragmentManager(), shopFragment, R.id.frag_shop
            );
        }
    }

    /**
     * get clickEvent from MainViewModel
     */
    private void setupEvents() {

        mShopViewModel.getOpenShopEvent().observe(ShopActivity.this, product ->
                DescriptionActivity.startActivity(ShopActivity.this, product)
        );
    }

    /**
     * FloatActionButton
     */
    public void onClickFAB(View view) {
        Toast.makeText(ShopActivity.this, "Float button", Toast.LENGTH_SHORT).show();

    }

}