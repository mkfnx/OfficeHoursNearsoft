package com.mkfnx.officehoursnearsoft.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mkfnx.officehoursnearsoft.R;
import com.mkfnx.officehoursnearsoft.data.source.VenuesRepository;
import com.mkfnx.officehoursnearsoft.data.source.remote.VenuesRemoteDataSource;
import com.mkfnx.officehoursnearsoft.util.ActivityUtils;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if ( mainFragment == null ) {
            mainFragment = MainFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mainFragment, R.id.contentFrame);
        }
    }
}
