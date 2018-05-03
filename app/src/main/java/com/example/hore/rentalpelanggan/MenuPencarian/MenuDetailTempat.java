package com.example.hore.rentalpelanggan.MenuPencarian;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.hore.rentalpelanggan.R;

import java.util.ArrayList;
import java.util.List;

public class MenuDetailTempat extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView abc;
    private Bundle bundle;
    private TempatModel tempatModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Detail Lapangan");
        setContentView(R.layout.activity_menu_detail_kendaraan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager, bundle);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //tempatModel = (TempatModel) getIntent().getSerializableExtra(Constants.KENDARAAN);
    }

    public void setupViewPager(ViewPager viewPager, Bundle bundle) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Fragment one = new Tab1_DetailTempat();
        one.setArguments(bundle);
        adapter.addFragment(one, "Kebijakan Penyewaan");
        Fragment two = new Tab2_DetailTempat();
        two.setArguments(bundle);
        adapter.addFragment(two, "Detail Tempat");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

